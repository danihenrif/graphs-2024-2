package br.ufrn.imd.algoritmos;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Dijkstra {

    public static void CaminhoMaisCurto(Map<Integer, List<Map<Integer, Integer>>> digrafo, int origem){
        int[] distancia = new int[digrafo.size()];
        int[] predecessor = new int[digrafo.size()];
        boolean[] visitado = new boolean[digrafo.size()];

        Arrays.fill(visitado, false);
        Arrays.fill(predecessor, -1);
        Arrays.fill(distancia, Integer.MAX_VALUE);
        distancia[origem] = 0;

        while (true){
            int vertice = -1;
            int menorDistancia = Integer.MAX_VALUE;

            for(int i = 0; i < digrafo.size(); i++){
                if(!visitado[i] && distancia[i] < menorDistancia){
                    vertice = i;
                    menorDistancia = distancia[i];
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
                imprimirCaminho(predecessor, i);
                System.out.println();
            }
        }
    }

    private static void imprimirCaminho(int[] predecessor, int vertice) {
        if (vertice == -1) return;
        imprimirCaminho(predecessor, predecessor[vertice]);
        System.out.print(vertice + " ");
    }
}
