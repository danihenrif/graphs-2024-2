package br.ufrn.imd;

import br.ufrn.imd.util.GraphLoader;
import br.ufrn.imd.util.GraphUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        GraphLoader.load(graph, "src/grafo2" + ".txt");

        //Questão 1
        System.out.println(graph.toString());
        System.out.println(graph.removeVertex(1));
        System.out.println(graph.toString());

        System.out.println(graph.addVertex(4));
        System.out.println();
        System.out.println();

        System.out.println(GraphLoader.load(graph, "src/grafo" + ".txt"));

        //Questão 2
        AdjacencyMatrix adjacencyMatrixToConvert = new AdjacencyMatrix().generateRandomAdjacencyMatrix(4);
        System.out.println( "Adjacency List : \n" +
                adjacencyMatrixToConvert.undirectedAdjacencyMatrixToAdjacencyList(adjacencyMatrixToConvert)
        );
    }
}
