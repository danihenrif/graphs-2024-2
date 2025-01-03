package br.ufrn.imd.algoritmos;

import java.util.*;

public class ChuLiuEdmonds {

    public static class Aresta {
        private final int origem;
        private final int destino;
        private final int peso;

        public Aresta(int origem, int destino, int peso) {
            this.origem = origem;
            this.destino = destino;
            this.peso = peso;
        }

        public int getOrigem() {
            return origem;
        }

        public int getDestino() {
            return destino;
        }

        public int getPeso() {
            return peso;
        }

        @Override
        public String toString() {
            return "Aresta{" + "origem=" + origem + ", destino=" + destino + ", peso=" + peso + '}';
        }
    }

    public static List<Aresta> executarChuLiuEdmonds(int n, int raiz, List<Aresta> arestas) {

        // 1) Organizar arestas por destino
        // Para cada vértice, queremos achar a melhor aresta de entrada
        List<List<Aresta>> incomingEdges = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            incomingEdges.add(new ArrayList<>());
        }
        for (Aresta e : arestas) {
            incomingEdges.get(e.getDestino()).add(e);
        }
    
        // Vetores de ajuda
        int[] pai = new int[n];      // pai[v] = origem da melhor aresta de entrada de v
        int[] minPeso = new int[n];  // minPeso[v] = peso da melhor aresta de entrada de v
        int[] id = new int[n];       // id do componente/contração
        int[] visitado = new int[n]; // usado para detectar ciclo
        Arrays.fill(pai, -1);
        Arrays.fill(minPeso, 0);
        Arrays.fill(id, -1);
        Arrays.fill(visitado, -1);
    
        // 2) Para cada vértice exceto a raiz, selecionar a aresta de menor peso que entra nele
        for (int v = 0; v < n; v++) {
            if (v == raiz) continue;
            Aresta melhor = null;
            for (Aresta e : incomingEdges.get(v)) {
                if (melhor == null || e.getPeso() < melhor.getPeso()) {
                    melhor = e;
                }
            }
            if (melhor != null) {
                pai[v] = melhor.getOrigem();
                minPeso[v] = melhor.getPeso();
            } else {
                // Se não existe nenhuma aresta de entrada, grafo desconexo
                pai[v] = -1;
                minPeso[v] = 0;
            }
        }
    
        // 3) Detectar ciclos
        int idxAtual = 0; // serve para marcar componentes
        // Total de custo
        int custo = 0;
        for (int v = 0; v < n; v++) {
            // Acumula o custo das arestas escolhidas
            if (v == raiz) continue;
            custo += minPeso[v];
        }
    
        // Percorrer cada vértice para ver se existe ciclo
        for (int v = 0; v < n; v++) {
            // subimos a cadeia de pais até achar a raiz ou repetir um vértice
            if (v == raiz) continue;
            int u = v;
            while (u != raiz && u != -1 && visitado[u] == -1) {
                visitado[u] = v;
                u = pai[u];
            }
            // Se encontramos um vértice já visitado na mesma iteração, então há ciclo
            if (u != -1 && visitado[u] == v) {
                // Achamos um ciclo; vamos marcar esse ciclo com um ID
                while (id[u] == -1) {
                    id[u] = idxAtual;
                    u = pai[u];
                }
                idxAtual++;
            }
        }
    
        if (idxAtual == 0) {
            // Significa que não houve ciclo, basta reconstruir as arestas
            // pai[v] nos dá de onde vem a aresta de menor peso
            // (v != raiz) pois a raiz não tem pai
            List<Aresta> resultado = new ArrayList<>();
            for (int v = 0; v < n; v++) {
                if (v == raiz || pai[v] == -1) continue;
                resultado.add(new Aresta(pai[v], v, minPeso[v]));
            }
            return resultado;
        }
    
        // 4) Existe pelo menos 1 ciclo. Precisamos contrair e rodar o algoritmo novamente.
    
        // Para vértices sem ciclo, atribuir ID único
        for (int v = 0; v < n; v++) {
            if (id[v] == -1) {
                id[v] = idxAtual++;
            }
        }
    
        // 5) Construir nova lista de arestas para o grafo contraído
        List<Aresta> novaLista = new ArrayList<>();
        for (Aresta e : arestas) {
            int novaOrigem = id[e.getOrigem()];
            int novoDestino = id[e.getDestino()];
            if (novaOrigem != novoDestino) {
                int novoPeso = e.getPeso() - minPeso[e.getDestino()];
                novaLista.add(new Aresta(novaOrigem, novoDestino, novoPeso));
            }
        }
    
        // Raiz contraída
        int novaRaiz = id[raiz];
    
        // 6) Rodar novamente no grafo contraído
        List<Aresta> mstContraida = executarChuLiuEdmonds(idxAtual, novaRaiz, novaLista);
    
        // 7) "Descomprimir" (expandir) as arestas do MST contraído para o MST original
        // Precisamos adicionar, para cada vértice do ciclo, a aresta que ele havia escolhido (exceto para o que foi substituído)
        // Aqui é onde muita gente simplifica. Precisamos mapear de volta id[] para os vértices.
    
        List<Aresta> resultadoFinal = new ArrayList<>();
        // As arestas vindas de mstContraida já estão "corrigidas"
        for (Aresta e : mstContraida) {
            resultadoFinal.add(new Aresta(
                    encontrarVertice(id, e.getOrigem()),
                    encontrarVertice(id, e.getDestino()),
                    e.getPeso() + minPeso[encontrarVertice(id, e.getDestino())]
            ));
        }
    
        // Além disso, incluímos as arestas "pai[v]" para cada vértice que estava em ciclo,
        // exceto naqueles que foram efetivamente substituídos. Em implementações mais avançadas,
        // rastreia-se diretamente qual aresta do ciclo foi "removida" e quais ficam.
    
        // Exemplo simples: adiciona a aresta de entrada original de cada v != raiz
        for (int v = 0; v < n; v++) {
            if (v == raiz) continue;
            if (pai[v] != -1 && !formaCicloComContraido(id, v)) {
                // Se esse vértice não foi substituído por outra aresta ao expandir
                resultadoFinal.add(new Aresta(pai[v], v, minPeso[v]));
            }
        }
    
        return resultadoFinal;
    }
    
    // Função auxiliar para encontrar qual vértice original corresponde a um super-nó 'comp'
    // (em algumas implementações guardamos esses mapeamentos explicitamente)
    private static int encontrarVertice(int[] id, int comp) {
        // Exemplo bem simples, na prática a gente teria que saber qual vértice do "ciclo" mapeia para esse ID.
        // Aqui, poderíamos só retornar o primeiro que tiver id[v] == comp
        // (mas isso pode precisar de ajuste dependendo da lógica de contração)
        for (int v = 0; v < id.length; v++) {
            if (id[v] == comp) return v;
        }
        return -1;
    }
    
    // Exemplo de checagem se o vértice estava em ciclo (componente que apareceu mais de uma vez)
    private static boolean formaCicloComContraido(int[] id, int v) {
        int comp = id[v];
        // Se esse 'comp' aparece em vários vértices, possivelmente é ciclo
        // (Lógica simples, mas em implementações robustas guardamos explicitamente quem está em ciclo)
        int count = 0;
        for (int x : id) {
            if (x == comp) count++;
            if (count > 1) return true;
        }
        return false;
    }
    
}