package br.ufrn.imd.estruturasdedados;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CaminhoMaisCurto {
    public static void Dijkstra(Map<Integer, List<Map<Integer, Integer>>> digrafo, int origem){
        int[] distancia = new int[digrafo.size()];
        int[] predecessor = new int[digrafo.size()];
        boolean[] visitado = new boolean[digrafo.size()];

        Arrays.fill(visitado, false);
        Arrays.fill(predecessor, -1);
        Arrays.fill(distancia, Integer.MAX_VALUE);
        distancia[origem] = 0;

        while (true){
            int vertice = -1;
            int menorDitancia = Integer.MAX_VALUE;

            for(int i = 0; i < digrafo.size(); i++){
                if(!visitado[i] && distancia[i] < menorDitancia){
                    vertice = i;
                    menorDitancia = distancia[i];
                }
            }

            if(vertice == -1) break;

            visitado[vertice] = true;

            List<Map<Integer, Integer>> vizinhos = digrafo.get(vertice);
            for(Map<Integer, Integer> aresta : vizinhos){
                for(Map.Entry<Integer, Integer> entry : aresta.entrySet()){
                    int destino = entry.getKey();
                    int peso = entry.getValue();

                    if(!visitado[destino] && distancia[vertice] + peso < distancia[destino]){
                        distancia[destino] = distancia[vertice] + peso;
                        predecessor[destino] = vertice;
                    }
                }
            }
        }

        System.out.println("Distâncias mínimas a partir do vértice " + origem + ":");
        for (int i = 0; i < digrafo.size(); i++) {
            System.out.println("Vértice " + i + ": " + distancia[i]);
        }

        System.out.println("\nCaminhos:");
        for (int i = 0; i < digrafo.size(); i++) {
            if (i != origem) {
                System.out.print("Caminho para " + i + ": ");
                imprimirCaminhoDij(predecessor, i);
                System.out.println();
            }
        }
    }

    private static void imprimirCaminhoDij(int[] predecessor, int vertice) {
        if (vertice == -1) return;
        imprimirCaminhoDij(predecessor, predecessor[vertice]);
        System.out.print(vertice + " ");
    }

    public static void FloydWarshall(Map<Integer, List<Map<Integer, Integer>>> digrafo, int origem) {
        int n = digrafo.size();
        int INF = 1000000000;
        int[][] distancia = new int[n][n];
        int[][] predecessor = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    distancia[i][j] = 0;
                    predecessor[i][j] = i;
                } else {
                    distancia[i][j] = INF;
                    predecessor[i][j] = -1;
                }
            }
        }

        for (Map.Entry<Integer, List<Map<Integer, Integer>>> entry : digrafo.entrySet()) {
            int vertice = entry.getKey();
            List<Map<Integer, Integer>> vizinhos = entry.getValue();
            for (Map<Integer, Integer> aresta : vizinhos) {
                for (Map.Entry<Integer, Integer> entry2 : aresta.entrySet()) {
                    int destino = entry2.getKey();
                    int peso = entry2.getValue();
                    distancia[vertice][destino] = peso;
                    predecessor[vertice][destino] = vertice;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distancia[i][j] > distancia[i][k] + distancia[k][j]) {
                        distancia[i][j] = distancia[i][k] + distancia[k][j];
                        predecessor[i][j] = predecessor[k][j];
                    }
                }
            }
        }

        System.out.println("Matriz de distâncias mínimas:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (distancia[i][j] == INF) {
                    System.out.print("∞ ");
                } else {
                    System.out.print(distancia[i][j] + " ");
                }
            }
            System.out.println();
        }

        System.out.println("\nCaminhos:");
        for (int i = 0; i < n; i++) {
            if (origem != i) {
                System.out.print("Caminho de " + origem + " para " + i + ": ");
                imprimirCaminhoFW(predecessor, origem, i);
                System.out.println();
            }
        }
    }

    private static void imprimirCaminhoFW(int[][] pred, int i, int j) {
        if (i == j) {
            System.out.print(i + " ");
        } else if (pred[i][j] == -1) {
            System.out.print("não há caminho ");
        } else {
            imprimirCaminhoFW(pred, i, pred[i][j]);
            System.out.print(j + " ");
        }
    }
}
