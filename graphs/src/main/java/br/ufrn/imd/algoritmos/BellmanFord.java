package br.ufrn.imd.algoritmos;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BellmanFord {
    public static void CaminhoMaisCurto(Map<Integer, List<Map<Integer, Integer>>> digrafo, int origem){
        int n = digrafo.size();
        int[] distancia = new int[n];
        int[] predecessor = new int[n];

        Arrays.fill(distancia, Integer.MAX_VALUE);
        Arrays.fill(predecessor, -1);

        distancia[origem] = 0;

        for (int i = 1; i < n; i++){
            for (Map.Entry<Integer, List<Map<Integer, Integer>>> entry : digrafo.entrySet()){
                int vertice = entry.getKey();
                List<Map<Integer, Integer>> vizinhos = entry.getValue();

                for(Map<Integer, Integer> aresta : vizinhos){
                    for (Map.Entry<Integer, Integer> destinoEntry : aresta.entrySet()){
                        int destino = destinoEntry.getKey();
                        int peso = destinoEntry.getValue();

                        if (distancia[vertice] != Integer.MAX_VALUE && distancia[destino] > distancia[vertice] + peso){
                            distancia[destino] = distancia[vertice] + peso;
                            predecessor[destino] = vertice;
                        }
                    }
                }
            }
        }

        System.out.println("Distâncias mínimas a partir do vértice " + origem + ":");
        for (int i = 0; i < n; i++) {
            if (distancia[i] == Integer.MAX_VALUE) {
                System.out.println("Vértice " + i + ": INFINITY");
            } else {
                System.out.println("Vértice " + i + ": " + distancia[i]);
            }
        }

        System.out.println("\nCaminhos:");
        for (int i = 0; i < n; i++) {
            if (i != origem) {
                System.out.print("Caminho de " + origem + " para " + i + ": ");
                imprimirCaminho(predecessor, origem, i);
                System.out.println();
            }
        }
    }

    private static void imprimirCaminho(int[] predecessor, int origem, int destino) {
        if (destino == origem) {
            System.out.print(origem + " ");
        } else if (predecessor[destino] == -1) {
            System.out.print("Não há caminho.");
        } else {
            imprimirCaminho(predecessor, origem, predecessor[destino]);
            System.out.print(destino + " ");
        }
    }
}
