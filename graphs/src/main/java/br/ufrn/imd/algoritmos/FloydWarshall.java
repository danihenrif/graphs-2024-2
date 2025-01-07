package br.ufrn.imd.algoritmos;

import java.util.List;
import java.util.Map;

public class FloydWarshall {
    public static void CaminhoMaisCurto(Map<Integer, List<Map<Integer, Integer>>> digrafo, int origem) {
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
                imprimirCaminho(predecessor, origem, i);
                System.out.println();
            }
        }
    }

    private static void imprimirCaminho(int[][] pred, int i, int j) {
        if (i == j) {
            System.out.print(i + " ");
        } else if (pred[i][j] == -1) {
            System.out.print("não há caminho ");
        } else {
            imprimirCaminho(pred, i, pred[i][j]);
            System.out.print(j + " ");
        }
    }
}
