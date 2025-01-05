package br.ufrn.imd.algoritmos;

import br.ufrn.imd.estruturasdedados.ListaAdjacencia;
import java.util.*;

public class Prim {

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

    public static List<Aresta> executarPrim(ListaAdjacencia grafo, int inicio) {
    int numVertices = grafo.numeroDeVertices();
    boolean[] visitados = new boolean[numVertices];
    List<Aresta> mst = new ArrayList<>();
    PriorityQueue<Aresta> minHeap = new PriorityQueue<>(Comparator.comparingInt(Aresta::getPeso));

    // Adiciona todas as arestas do vértice inicial à fila de prioridade
    visitados[inicio] = true;
    for (int adj : grafo.obterAdjacentes(inicio)) {
        int peso = grafo.obterPeso(inicio, adj);
        minHeap.add(new Aresta(inicio, adj, peso));
    }

    while (!minHeap.isEmpty() && mst.size() < numVertices - 1) {
        Aresta aresta = minHeap.poll();

        if (!visitados[aresta.getDestino()]) {
            visitados[aresta.getDestino()] = true;
            mst.add(aresta);

            // Adiciona as arestas do destino recém-incluído na MST
            for (int adj : grafo.obterAdjacentes(aresta.getDestino())) {
                if (!visitados[adj]) {
                    int peso = grafo.obterPeso(aresta.getDestino(), adj);
                    minHeap.add(new Aresta(aresta.getDestino(), adj, peso));
                }
            }
        }
    }

    return mst;
}

}
