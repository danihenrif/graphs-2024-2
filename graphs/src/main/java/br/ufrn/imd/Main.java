package br.ufrn.imd;

import br.ufrn.imd.datastructures.AdjacencyMatrix;
import br.ufrn.imd.datastructures.DirectStar;
import br.ufrn.imd.datastructures.IncidenceMatrix;
import br.ufrn.imd.datastructures.ReverseStar;
import br.ufrn.imd.util.GraphLoader;
import br.ufrn.imd.util.GraphUtils;

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
        AdjacencyMatrix adjacencyMatrixToConvert =
                new AdjacencyMatrix().generateRandomAdjacencyMatrix(4);
        System.out.println(
                "Adjacency List : \n"
                        + AdjacencyMatrix.undirectedAdjacencyMatrixToAdjacencyList(
                        adjacencyMatrixToConvert));

        // Questão 3 - Matriz de Adjacência para Matriz de Incidência
        // adjacencyMatrixToConvert.setDirected(true);
        System.out.println("Adjacency Matrix to Convert:");
        System.out.println(adjacencyMatrixToConvert.toString());

        System.out.println("Converted, result :");
        IncidenceMatrix incidenceMatrix = adjacencyMatrixToConvert.adjacencyMatrixToIncidenceMatrix();
        System.out.println(incidenceMatrix.toString());

        // Questão 4 - Matriz de adjacência para Estrela direta e Estrela reversa
        // Exemplo random
        System.out.println("Adjacency Matrix to Convert to Direct Star:");
        System.out.println(adjacencyMatrixToConvert.toString());

        System.out.println("Converted, result :");
        DirectStar directStar = adjacencyMatrixToConvert.adjacencyMatrixToDirectStar();
        System.out.println(directStar);

        System.out.println();

        // Exemplo do slide

        System.out.println("Adjacency Matrix to Convert to Direct Star:");
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(GraphUtils.matrix);
        System.out.println(adjacencyMatrix.toString());

        DirectStar directStar1 = adjacencyMatrix.adjacencyMatrixToDirectStar();

        System.out.println("Converted, result :");
        System.out.println(directStar1);

        System.out.println("Adjacency Matrix to Convert to Reverse Star:");
        System.out.println(adjacencyMatrix.toString());

        ReverseStar reverseStar = adjacencyMatrix.adjacencyMatrixToReverseStar();

        System.out.println("Converted, result :");
        System.out.println(reverseStar);

        // Questão 5 - Gerar Código de Prüffer

        AdjacencyMatrix treeAdjacencyMatrix = new AdjacencyMatrix(GraphUtils.treeMatrix);
        System.out.println("Matriz de Adjacência da Árvore:");
        System.out.println(treeAdjacencyMatrix.toString());

        List<Integer> prufferCode = treeAdjacencyMatrix.generatePrufferCode();
        System.out.println("Código de Prüffer:");
        System.out.println(prufferCode);

        // Exemplo do slide

        AdjacencyMatrix treeAdjacencyMatrix2 = new AdjacencyMatrix(GraphUtils.treeMatrix2);
        System.out.println("Matriz de Adjacência da Árvore:");
        System.out.println(treeAdjacencyMatrix2.toString());

        List<Integer> prufferCode2 = treeAdjacencyMatrix2.generatePrufferCode();
        System.out.println("Código de Prüffer:");
        System.out.println(prufferCode2);

        // Testando o método depthFirstSearch
        System.out.println("\nTeste da Busca em Profundidade (DFS):");
        adjacencyMatrixToConvert.depthFirstSearch(0);

        // Testando o método depthFirstSearch
        System.out.println("\nTeste da Busca em Profundidade com Recorrência:");
        graph.getAdjacencyList().BuscaProfundidade(1);
    }
}
