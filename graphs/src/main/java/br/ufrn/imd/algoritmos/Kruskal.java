package br.ufrn.imd.algoritmos;

import br.ufrn.imd.estruturasdedados.ListaAdjacencia;
import java.util.*;

public class Kruskal {

    public static class Aresta implements Comparable<Aresta> {
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
        public int compareTo(Aresta outra) {
            return Integer.compare(this.peso, outra.peso);
        }

        @Override
        public String toString() {
            return "Aresta{" + 
                   "origem=" + origem + 
                   ", destino=" + destino + 
                   ", peso=" + peso + '}';
        }
    }

    private static int find(int[] pai, int x) {
        if (pai[x] != x) {
            pai[x] = find(pai, pai[x]);
        }
        return pai[x];
    }

    public static List<Aresta> executarKruskal(ListaAdjacencia grafo) {
        int n = grafo.numeroDeVertices();
        List<Aresta> arestas = new ArrayList<>();

        // Monta a lista de todas as arestas (u < v, para não duplicar no caso de grafo não dirigido)
        for (int u = 0; u < n; u++) {
            for (int v : grafo.obterAdjacentes(u)) {
                if (u < v) {
                    int peso = grafo.obterPeso(u, v);
                    arestas.add(new Aresta(u, v, peso));
                }
            }
        }

        // Ordena as arestas pelo peso (crescente)
        Collections.sort(arestas);

        // Estrutura Union-Find: cada vértice começa como pai de si mesmo
        int[] pai = new int[n];
        for (int i = 0; i < n; i++) {
            pai[i] = i;
        }

        List<Aresta> mst = new ArrayList<>();

        // Percorre as arestas em ordem
        for (Aresta a : arestas) {
            int raizU = find(pai, a.getOrigem());
            int raizV = find(pai, a.getDestino());

            // Se forem componentes diferentes, une e adiciona a aresta na MST
            if (raizU != raizV) {
                mst.add(a);
                pai[raizV] = raizU; // Poderia usar union-by-rank, mas aqui simplificamos
                if (mst.size() == n - 1) {
                    break;
                }
            }
        }

        return mst;
    }
}
