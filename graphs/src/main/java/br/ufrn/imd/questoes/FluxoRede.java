package br.ufrn.imd.questoes;

import java.util.*;

public class FluxoRede {
    private final Map<String, Map<String, Integer>> grafo;
    private final Map<String, Map<String, Integer>> capacidades;
    private final String fonte;
    private final String sumidouro;

    public FluxoRede(String fonte, String sumidouro) {
        this.grafo = new HashMap<>();
        this.capacidades = new HashMap<>();
        this.fonte = fonte;
        this.sumidouro = sumidouro;
    }

    public void adicionarAresta(String u, String v, int capacidade) {
        // Inicializa os mapas se necessário
        grafo.putIfAbsent(u, new HashMap<>());
        grafo.putIfAbsent(v, new HashMap<>());
        capacidades.putIfAbsent(u, new HashMap<>());
        capacidades.putIfAbsent(v, new HashMap<>());

        // Adiciona a aresta e sua capacidade
        grafo.get(u).put(v, 0); // Fluxo inicial é 0
        grafo.get(v).put(u, 0); // Aresta residual
        capacidades.get(u).put(v, capacidade);
        capacidades.get(v).put(u, 0); // Capacidade da aresta residual
    }

    private Map<String, String> bfs() {
        Map<String, String> pais = new HashMap<>();
        Queue<String> fila = new LinkedList<>();

        fila.offer(fonte);
        pais.put(fonte, null);

        while (!fila.isEmpty() && !pais.containsKey(sumidouro)) {
            String atual = fila.poll();

            for (String vizinho : grafo.get(atual).keySet()) {
                if (!pais.containsKey(vizinho) &&
                        capacidades.get(atual).get(vizinho) > grafo.get(atual).get(vizinho)) {
                    pais.put(vizinho, atual);
                    fila.offer(vizinho);
                }
            }
        }

        return pais;
    }

    public int edmondsKarp() {
        int fluxoMaximo = 0;

        while (true) {
            // Encontra um caminho aumentante usando BFS
            Map<String, String> pais = bfs();

            // Se não existe caminho até o sumidouro, terminamos
            if (!pais.containsKey(sumidouro)) {
                break;
            }

            // Encontra o fluxo máximo que pode passar por este caminho
            int fluxoCaminho = Integer.MAX_VALUE;
            String atual = sumidouro;

            while (!atual.equals(fonte)) {
                String pai = pais.get(atual);
                int capacidadeRestante = capacidades.get(pai).get(atual) - grafo.get(pai).get(atual);
                fluxoCaminho = Math.min(fluxoCaminho, capacidadeRestante);
                atual = pai;
            }

            // Atualiza o fluxo ao longo do caminho
            atual = sumidouro;
            while (!atual.equals(fonte)) {
                String pai = pais.get(atual);
                grafo.get(pai).put(atual, grafo.get(pai).get(atual) + fluxoCaminho);
                grafo.get(atual).put(pai, grafo.get(atual).get(pai) - fluxoCaminho);
                atual = pai;
            }

            fluxoMaximo += fluxoCaminho;
        }

        return fluxoMaximo;
    }

    private boolean dfs(String atual, Set<String> visitados, Map<String, String> pais) {
        if (atual.equals(sumidouro)) {
            return true;
        }

        visitados.add(atual);

        for (String vizinho : grafo.get(atual).keySet()) {
            if (!visitados.contains(vizinho) &&
                    capacidades.get(atual).get(vizinho) > grafo.get(atual).get(vizinho)) {
                pais.put(vizinho, atual);
                if (dfs(vizinho, visitados, pais)) {
                    return true;
                }
            }
        }

        return false;
    }

    public int fordFulkerson() {
        int fluxoMaximo = 0;

        while (true) {
            // Encontra um caminho aumentante usando DFS
            Map<String, String> pais = new HashMap<>();
            Set<String> visitados = new HashSet<>();

            if (!dfs(fonte, visitados, pais)) {
                break;
            }

            // Encontra o fluxo máximo que pode passar por este caminho
            int fluxoCaminho = Integer.MAX_VALUE;
            String atual = sumidouro;

            while (!atual.equals(fonte)) {
                String pai = pais.get(atual);
                int capacidadeRestante = capacidades.get(pai).get(atual) - grafo.get(pai).get(atual);
                fluxoCaminho = Math.min(fluxoCaminho, capacidadeRestante);
                atual = pai;
            }

            // Atualiza o fluxo ao longo do caminho
            atual = sumidouro;
            while (!atual.equals(fonte)) {
                String pai = pais.get(atual);
                grafo.get(pai).put(atual, grafo.get(pai).get(atual) + fluxoCaminho);
                grafo.get(atual).put(pai, grafo.get(atual).get(pai) - fluxoCaminho);
                atual = pai;
            }

            fluxoMaximo += fluxoCaminho;
        }

        return fluxoMaximo;
    }

    public static void main(String[] args) {
        // Cria o grafo
        FluxoRede rede = new FluxoRede("s", "t");

        // Adiciona todas as arestas com suas capacidades
        rede.adicionarAresta("s", "a", 5);
        rede.adicionarAresta("s", "b", 10);
        rede.adicionarAresta("s", "c", 5);
        rede.adicionarAresta("a", "b", 15);
        rede.adicionarAresta("a", "d", 10);
        rede.adicionarAresta("b", "e", 20);
        rede.adicionarAresta("c", "e", 5);
        rede.adicionarAresta("c", "f", 10);
        rede.adicionarAresta("d", "e", 25);
        rede.adicionarAresta("d", "g", 10);
        rede.adicionarAresta("d", "h", 15);
        rede.adicionarAresta("e", "h", 30);
        rede.adicionarAresta("f", "h", 5);
        rede.adicionarAresta("f", "i", 10);
        rede.adicionarAresta("g", "t", 5);
        rede.adicionarAresta("h", "i", 5);
        rede.adicionarAresta("h", "t", 15);
        rede.adicionarAresta("i", "t", 10);

        // Testa ambos os algoritmos
        FluxoRede redeFF = new FluxoRede("s", "t");
        FluxoRede redeEK = new FluxoRede("s", "t");

        // Adiciona as mesmas arestas para ambas as redes
        for (Map.Entry<String, Map<String, Integer>> entry : rede.capacidades.entrySet()) {
            String u = entry.getKey();
            for (Map.Entry<String, Integer> aresta : entry.getValue().entrySet()) {
                String v = aresta.getKey();
                int cap = aresta.getValue();
                if (cap > 0) { // Só adiciona arestas com capacidade positiva
                    redeFF.adicionarAresta(u, v, cap);
                    redeEK.adicionarAresta(u, v, cap);
                }
            }
        }

        System.out.println("Fluxo máximo (Ford-Fulkerson): " + redeFF.fordFulkerson());
        System.out.println("Fluxo máximo (Edmonds-Karp): " + redeEK.edmondsKarp());
    }
}
