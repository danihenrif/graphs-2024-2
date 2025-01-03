package br.ufrn.imd.algoritmos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Implementação do algoritmo de Chu-Liu/Edmonds para encontrar
 * a Arborescência Geradora Mínima (dirigida) de um grafo.
 *
 * Referências:
 * - https://en.wikipedia.org/wiki/Edmonds%27_algorithm
 * - https://courses.engr.illinois.edu/cs374/fa2018/Alectures/lec23.pdf
 *
 * @author 
 */
public class ChuLiuEdmonds {

    /**
     * Representa uma aresta direcionada de um grafo, contendo
     * origem, destino e peso (custo).
     */
    public static class Aresta {
        private int origem;
        private int destino;
        private int peso;

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

        public void setOrigem(int origem) {
            this.origem = origem;
        }

        public void setDestino(int destino) {
            this.destino = destino;
        }

        public void setPeso(int peso) {
            this.peso = peso;
        }

        @Override
        public String toString() {
            return String.format("Aresta(%d -> %d, peso=%d)", origem, destino, peso);
        }
    }

    /**
     * Executa o algoritmo de Chu-Liu/Edmonds para encontrar a Arborescência
     * Geradora Mínima enraizada em 'raiz' em um grafo de 'numVertices' vértices,
     * com a lista de arestas fornecida.
     *
     * @param numVertices número total de vértices
     * @param raiz índice do vértice que será considerado raiz
     * @param arestas lista de arestas do grafo
     * @return lista de arestas que compõem a Arborescência Geradora Mínima
     */
    public static List<Aresta> executarChuLiuEdmonds(int numVertices, int raiz, List<Aresta> arestas) {
        /*
         * Concepção geral do algoritmo:
         * 1) Para cada nó (exceto a raiz), seleciona a aresta de menor custo que entra nele.
         * 2) Se a seleção forma um ciclo, contrai o ciclo em um único nó (pseudo-nó).
         * 3) Ajusta os custos das arestas e repete até não haver mais ciclos.
         * 4) Reconstrói as arestas selecionadas, obtendo a Arborescência Geradora Mínima.
         */

        // Ajuste de índice: se a raiz não é 0, ainda funciona do mesmo jeito;
        // basta manter a raiz como valor fixo a ser ignorado na seleção de aresta de entrada.
        
        // Passo 1: Selecionar a aresta de menor custo que entra em cada nó (exceto raiz).
        // inEdge[v] guardará a origem da melhor aresta que entra em v (para v != raiz).
        // inWeight[v] guardará o peso da melhor aresta que entra em v (para v != raiz).
        int[] inEdge = new int[numVertices];
        int[] inWeight = new int[numVertices];

        // Inicialmente, coloca um "infinito" para todos, exceto a raiz
        for (int i = 0; i < numVertices; i++) {
            inEdge[i] = -1;
            inWeight[i] = Integer.MAX_VALUE;
        }
        inEdge[raiz] = raiz;    // A raiz aponta para ela mesma (ou -1), pois não escolhemos aresta de entrada nela
        inWeight[raiz] = 0;

        // Para cada aresta (u -> v), tenta melhorar a entrada de v
        for (Aresta e : arestas) {
            int u = e.getOrigem();
            int v = e.getDestino();
            int w = e.getPeso();
            if (u != v && v != raiz && w < inWeight[v]) {
                inEdge[v] = u;
                inWeight[v] = w;
            }
        }

        // Detecta ciclos no grafo resultante e cria pseudo-nós
        // idComp[v] será o 'representante' do componente a que v pertence
        int[] idComp = new int[numVertices];
        // Marca de visita (para detectar ciclos), -1 = não visitado
        int[] visitado = new int[numVertices];
        // Array para armazenar o resultado da contração
        int[] newId = new int[numVertices];

        // Preenche idComp de 0 a numVertices-1
        for (int i = 0; i < numVertices; i++) {
            idComp[i] = -1;
            visitado[i] = -1;
            newId[i] = -1;
        }

        int idxComp = 0; // Contador para pseudo-nós gerados (componentes)

        // Percorre cada vértice para achar ciclos
        for (int v = 0; v < numVertices; v++) {
            // Se ainda não foi atribuído a um componente, inicia verificação de ciclo
            if (v == raiz) {
                // A raiz por convenção é seu próprio componente
                idComp[v] = v;
                continue;
            }
            // Caminha nos ancestrais até achar ciclo ou chegar em um nó já resolvido
            int cur = v;
            List<Integer> caminho = new ArrayList<>();
            while (cur != raiz && idComp[cur] == -1 && cur != -1) {
                caminho.add(cur);
                visitado[cur] = v; // marca com a "cor" da busca atual
                cur = inEdge[cur];
                if (cur == -1) {
                    // Não há aresta que entra em 'cur', algo que não deveria acontecer
                    break;
                }
                // Se detectar que o 'cur' já foi visitado nesta mesma busca, formou ciclo
                if (visitado[cur] == v) {
                    // Detectado ciclo
                    // Contrair esse ciclo em um componente
                    int cicloInicio = cur;
                    while (true) {
                        idComp[cur] = idxComp;
                        cur = inEdge[cur];
                        if (cur == cicloInicio) {
                            break;
                        }
                    }
                    idxComp++;
                    break;
                }
                // Se esse nó já pertence a algum componente anterior, encerra
                if (idComp[cur] != -1) {
                    break;
                }
            }
            // Para todos os nós no caminho que não foram contraídos, atribui o mesmo componente do 'cur'
            cur = v;
            while (cur != raiz && idComp[cur] == -1 && cur != -1) {
                idComp[cur] = idComp[inEdge[cur]];
                cur = inEdge[cur];
            }
        }

        // Se não houve ciclo algum, idxComp será 0 ou no máximo numVertices (caso tudo sem ciclos).
        // Caso idxComp == numVertices, não há "contração" real.
        // Caso idxComp < numVertices, haverá contrações a tratar.
        if (idxComp == 0) {
            // Não há ciclos, já temos a arborescência
            return reconstrucaoArvore(numVertices, raiz, inEdge, inWeight);
        }

        // Ajustar vértices que não foram definidos em nenhum ciclo
        for (int i = 0; i < numVertices; i++) {
            if (idComp[i] == -1) {
                idComp[i] = idxComp++;
            }
        }

        // Agora, para cada aresta (u -> v), ajusta para (idComp[u] -> idComp[v]),
        // pois alguns nós foram contraídos em pseudo-nós.
        // Também ajusta o peso considerando a fórmula de correção
        List<Aresta> arestasContraidas = new ArrayList<>();
        for (Aresta e : arestas) {
            int u = e.getOrigem();
            int v = e.getDestino();
            int w = e.getPeso();
            int ru = idComp[u];
            int rv = idComp[v];
            if (ru != rv) {
                // Reduz o peso usando a compensação do arco de entrada
                // (inWeight[v] é o custo do arco que chegou em v e gerou a contração)
                // Precisamos subtrair esse valor e somar o mínimo do componente.
                w = w - inWeight[v];
                arestasContraidas.add(new Aresta(ru, rv, w));
            }
        }

        // Chamada recursiva no grafo contraído
        List<Aresta> mstContraida = executarChuLiuEdmonds(idxComp, idComp[raiz], arestasContraidas);

        // "Descontrair" as arestas para reconstruir a MST original
        return reconstruirOriginal(mstContraida, numVertices, idComp, inEdge, inWeight);
    }

    /**
     * Reconstrói diretamente a MST se não houve contrações (ciclos).
     * Retorna as arestas selecionadas conforme inEdge[].
     */
    private static List<Aresta> reconstrucaoArvore(int n, int root, int[] inEdge, int[] inWeight) {
        List<Aresta> mst = new ArrayList<>();
        for (int v = 0; v < n; v++) {
            if (v == root) continue;
            int u = inEdge[v];
            if (u != -1) {
                mst.add(new Aresta(u, v, inWeight[v]));
            }
        }
        return mst;
    }

    /**
     * Reconstrói a MST no grafo original após termos a MST do grafo contraído.
     * A MST do grafo contraído está em mstContraida. Precisamos "expandir" cada pseudo-nó
     * e adicionar as arestas de entrada que originaram sua contração, conforme
     * inEdge[] e inWeight[].
     */
    private static List<Aresta> reconstruirOriginal(
            List<Aresta> mstContraida,
            int numVertices,
            int[] idComp,
            int[] inEdge,
            int[] inWeight
    ) {
        // Para facilitar a indexação: pseudoNo -> lista de vértices que formaram esse pseudo-nó
        HashMap<Integer, List<Integer>> componenteParaVertices = new HashMap<>();
        for (int v = 0; v < numVertices; v++) {
            componenteParaVertices.putIfAbsent(idComp[v], new ArrayList<>());
            componenteParaVertices.get(idComp[v]).add(v);
        }

        // As arestas da MST final
        List<Aresta> mstFinal = new ArrayList<>();

        // 1) Adiciona as arestas da MST contraída, pois elas ligam componentes diferentes
        mstFinal.addAll(mstContraida);

        // 2) Para cada componente (pseudo-nó), precisamos adicionar a aresta de entrada
        //    que de fato gerou a contração, que é inEdge[v], para cada v que está no ciclo,
        //    pois esse arco é o que entra nesse componente.
        //    Na prática, basta pegar um representante do componente e adicionar a aresta
        //    (inEdge[v], v) com peso inWeight[v] se v != raiz do componente.
        //    Mas cuidado para não duplicar ou conflitar arestas já usadas.
        for (int comp : componenteParaVertices.keySet()) {
            // Percorre vértices do componente
            for (int v : componenteParaVertices.get(comp)) {
                // Se for o único vértice no componente e for "raiz" do seu subgrafo, ignora
                if (inEdge[v] != -1 && idComp[v] == comp && inWeight[v] < Integer.MAX_VALUE) {
                    // Verifica se já não existe aresta (inEdge[v] -> v) na MSTFinal
                    boolean jaAdicionada = false;
                    for (Aresta a : mstFinal) {
                        if (a.getOrigem() == inEdge[v] && a.getDestino() == v) {
                            jaAdicionada = true;
                            break;
                        }
                    }
                    if (!jaAdicionada) {
                        mstFinal.add(new Aresta(inEdge[v], v, inWeight[v]));
                    }
                }
            }
        }

        // Pode haver duplicações ou ciclos com a junção (principalmente se havia mais de um ciclo).
        // Precisamos remover arestas redundantes. Uma estratégia simples é:
        // - Construir um grafo com as arestas e fazer uma busca que gere uma árvore.
        return filtrarArvoreFinal(mstFinal, numVertices);
    }

    /**
     * Dado um conjunto de arestas possivelmente redundantes, retorna apenas as
     * que formam uma arborescência sem ciclos (cada nó, exceto a raiz, tem exatamente 1 pai).
     */
    private static List<Aresta> filtrarArvoreFinal(List<Aresta> possiveisArestas, int n) {
        // Para garantir que cada nó tenha no máximo uma aresta de entrada, faremos:
        // - ordernar arestas por peso (opcional, mas útil para consistência)
        // - percorrer em ordem crescente, adicionando uma aresta se ela não criar um ciclo
        //   e se o nó destino ainda não tiver pai definido
        Collections.sort(possiveisArestas, (a, b) -> Integer.compare(a.getPeso(), b.getPeso()));

        // Disjoint Set (Union-Find) para detectar ciclos
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        List<Aresta> result = new ArrayList<>();
        // Marca se um nó já possui pai
        boolean[] temPai = new boolean[n];

        for (Aresta e : possiveisArestas) {
            int u = e.getOrigem();
            int v = e.getDestino();
            if (!temPai[v] && find(u, parent) != find(v, parent)) {
                // Não cria ciclo e o nó 'v' ainda não tem pai
                result.add(e);
                temPai[v] = true;
                union(u, v, parent);
            }
        }
        return result;
    }

    /**
     * Funções auxiliares de Union-Find
     */
    private static int find(int v, int[] parent) {
        if (parent[v] == v) {
            return v;
        }
        parent[v] = find(parent[v], parent);
        return parent[v];
    }

    private static void union(int u, int v, int[] parent) {
        int ru = find(u, parent);
        int rv = find(v, parent);
        if (ru != rv) {
            parent[rv] = ru;
        }
    }
}
