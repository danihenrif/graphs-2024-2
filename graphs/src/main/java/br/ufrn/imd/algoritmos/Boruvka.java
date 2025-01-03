package br.ufrn.imd.algoritmos;

import br.ufrn.imd.estruturasdedados.ListaAdjacencia;

import java.util.*;

public class Boruvka {

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

    public static List<Aresta> executarBoruvka(ListaAdjacencia grafo) {
        int numVertices = grafo.numeroDeVertices();
        List<Aresta> mst = new ArrayList<>();
        int[] componente = new int[numVertices];

        // Inicializa cada vértice como uma componente separada
        for (int i = 0; i < numVertices; i++) {
            componente[i] = i;
        }

        int componentesRestantes = numVertices;

        while (componentesRestantes > 1) {
            Aresta[] menorAresta = new Aresta[numVertices];

            // Para cada aresta, encontra a menor que conecta componentes diferentes
            for (int origem = 0; origem < numVertices; origem++) {
                for (int destino : grafo.getLista().get(origem)) {
                    int peso = 1; // Ajuste se houver pesos reais
                    if (componente[origem] != componente[destino]) {
                        if (menorAresta[componente[origem]] == null || peso < menorAresta[componente[origem]].getPeso()) {
                            menorAresta[componente[origem]] = new Aresta(origem, destino, peso);
                        }
                    }
                }
            }

            // Adiciona as menores arestas encontradas à MST
            for (Aresta aresta : menorAresta) {
                if (aresta != null) {
                    int compOrigem = componente[aresta.getOrigem()];
                    int compDestino = componente[aresta.getDestino()];

                    if (compOrigem != compDestino) {
                        mst.add(aresta);

                        // Une as componentes
                        for (int i = 0; i < numVertices; i++) {
                            if (componente[i] == compDestino) {
                                componente[i] = compOrigem;
                            }
                        }

                        componentesRestantes--;
                    }
                }
            }
        }

        return mst;
    }
}
