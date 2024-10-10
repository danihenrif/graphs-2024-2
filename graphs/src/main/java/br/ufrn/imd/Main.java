package br.ufrn.imd;

import br.ufrn.imd.util.GraphLoader;
import br.ufrn.imd.util.GraphUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        GraphLoader.load(graph, "src/grafo2" + ".txt");

        // Questão 1 - Manipulação de Grafo Simples
        System.out.println(graph.toString());
        System.out.println(graph.removeVertex(1));
        System.out.println(graph.toString());

        System.out.println(graph.addVertex(4));
        System.out.println();

        System.out.println(GraphLoader.load(graph, "src/grafo" + ".txt"));

        // Questão 2 - Matriz de Adjacência para Lista de Adjacência
        AdjacencyMatrix adjacencyMatrixToConvert = new AdjacencyMatrix().generateRandomAdjacencyMatrix(4);
        System.out.println("Adjacency List : \n" +
                adjacencyMatrixToConvert.undirectedAdjacencyMatrixToAdjacencyList(adjacencyMatrixToConvert)
        );

        // Questão 3 - Matriz de Adjacência para Matriz de Incidência
        int[][] incidenceMatrix = adjacencyMatrixToConvert.adjacencyMatrixToIncidenceMatrix();
        System.out.println("Incidence Matrix:");
        for (int i = 0; i < incidenceMatrix.length; i++) {
            for (int j = 0; j < incidenceMatrix[i].length; j++) {
                System.out.print(incidenceMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
