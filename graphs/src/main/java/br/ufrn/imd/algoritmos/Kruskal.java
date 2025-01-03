package br.ufrn.imd.algoritmos;

import br.ufrn.imd.algoritmos.Kruskal.Aresta;
import br.ufrn.imd.estruturasdedados.ListaAdjacencia;
import java.util.*;

public class Kruskal {

    public static class Aresta implements Comparable<Aresta> {
        int origem, destino, peso;

        public Aresta(int origem, int destino, int peso) {
            this.origem = origem;
            this.destino = destino;
            this.peso = peso;
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
            return "Aresta{" + "origem=" + origem + ", destino=" + destino + ", peso=" + peso + '}';
        }
    }

    private static int encontrarPai(int[] pais, int vertice) {
        if (pais[vertice] == vertice) {
            return vertice;
        }
        return pais[vertice] = encontrarPai(pais, pais[vertice]); // Compressão de caminho
    }

    private static void unir(int[] pais, int[] rank, int vertice1, int vertice2) {
        int raiz1 = encontrarPai(pais, vertice1);
        int raiz2 = encontrarPai(pais, vertice2);

        if (raiz1 != raiz2) {
            if (rank[raiz1] > rank[raiz2]) {
                pais[raiz2] = raiz1;
            } else if (rank[raiz1] < rank[raiz2]) {
                pais[raiz1] = raiz2;
            } else {
                pais[raiz2] = raiz1;
                rank[raiz1]++;
            }
        }
    }

    public static List<Aresta> executarKruskal(ListaAdjacencia grafo, int numVertices) {
        List<Aresta> arestas = new ArrayList<>();

        // Constrói a lista de arestas
        for (Map.Entry<Integer, Set<Integer>> entrada : grafo.getLista().entrySet()) {
            int origem = entrada.getKey();
            for (Integer destino : entrada.getValue()) {
                int peso = 1; // Substitua por pesos reais se aplicável
                arestas.add(new Aresta(origem, destino, peso));
            }
        }

        // Ordena as arestas pelo peso
        Collections.sort(arestas);

        // Inicializa a estrutura de union-find
        int[] pais = new int[numVertices];
        int[] rank = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            pais[i] = i;
            rank[i] = 0;
        }

        // Algoritmo de Kruskal
        List<Aresta> mst = new ArrayList<>();
        for (Aresta aresta : arestas) {
            int origemPai = encontrarPai(pais, aresta.origem);
            int destinoPai = encontrarPai(pais, aresta.destino);

            if (origemPai != destinoPai) { // Não forma ciclo
                mst.add(aresta);
                unir(pais, rank, origemPai, destinoPai);
            }

            if (mst.size() == numVertices - 1) {
                break; // Quando MST está completo
            }
        }

        return mst;
    }
}
