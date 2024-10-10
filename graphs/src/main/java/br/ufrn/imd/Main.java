package br.ufrn.imd;

import br.ufrn.imd.util.GraphLoader;

import java.util.ArrayList;
import java.util.Arrays;
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
        AdjacencyMatrix adjacencyMatrixToConvert = new AdjacencyMatrix().generateRandomAdjacencyMatrix(3);
        System.out.println("Adjacency List : \n" +
                adjacencyMatrixToConvert.undirectedAdjacencyMatrixToAdjacencyList(adjacencyMatrixToConvert)
        );

        // Questão 3 - Matriz de Adjacência para Matriz de Incidência
        //adjacencyMatrixToConvert.setDirected(true);
        System.out.println("Adjacency Matrix to Convert:");
        System.out.println(adjacencyMatrixToConvert.toString());

        System.out.println("Converted, result :");
        IncidenceMatrix incidenceMatrix = adjacencyMatrixToConvert.adjacencyMatrixToIncidenceMatrix();
        System.out.println(incidenceMatrix.toString());

        //Questão 4 - Matriz de adjacência para Estrela direta e Estrela reversa
        //Exemplo random
        System.out.println("Adjacency Matrix to Convert to Direct Star:");
        System.out.println(adjacencyMatrixToConvert.toString());

        System.out.println("Converted, result :");
        DirectStar directStar = adjacencyMatrixToConvert.adjacencyMatrixToDirectStar();
        System.out.println(directStar);

        System.out.println();

        //Exemplo do slide
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(0, 1, 1, 0, 0));
        matrix.add(Arrays.asList(0, 0, 1, 0, 1));
        matrix.add(Arrays.asList(0, 0, 0, 0, 0));
        matrix.add(Arrays.asList(0, 0, 1, 0, 1));
        matrix.add(Arrays.asList(0, 0, 0, 1, 0));

        System.out.println("Adjacency Matrix to Convert to Direct Star:");
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(matrix);
        System.out.println(adjacencyMatrix.toString());

        DirectStar directStar1 = adjacencyMatrix.adjacencyMatrixToDirectStar();

        System.out.println("Converted, result :");
        System.out.println(directStar1);
    }
}
