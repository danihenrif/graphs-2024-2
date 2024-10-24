package br.ufrn.imd;

import br.ufrn.imd.datastructures.*;
import br.ufrn.imd.util.GraphLoader;
import br.ufrn.imd.util.GraphUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        GraphLoader.load(graph, "src/grafo2" + ".txt");
        System.out.println();

        //Questões 7 e 8
        System.out.println("Número de arcos \n" + graph.getAdjacencyList().numberOfArcs());
        System.out.println("Número de vértices \n" + graph.getAdjacencyList().numberOfVertix());
        System.out.println();

        // Questões  1 9 e 10 - Manipulação de Grafo Simples (REPRESENTAÇÃO DO GRAFO EM LISTA DE ADJACÊNCIA)
        System.out.println(graph.toString());
        System.out.println(graph.removeVertex(1));
        System.out.println(graph.toString());

        System.out.println(graph.addVertex(4));
        System.out.println();


        // Questão 2 - Matriz de adjacência
        AdjacencyList adjacencyListToConvert = new AdjacencyList().generateRandomAdjacencyList(4, false);
        System.out.println("Adjacency Matrix : \n" +
                adjacencyListToConvert.undirectedAdjacencyListToAdjacencyMatrix()
        );

        // Questão 3 - Matriz de incidência
        GraphLoader.load(graph, "src/grafo2" + ".txt");
        AdjacencyList adjacencyListToConvert2 = graph.getAdjacencyList();
        System.out.println("Incidence Matrix : \n" +
                adjacencyListToConvert2.directedAdjacencyListToIncidenceMatrix()
        );
        // Questão 4 - Matriz de Adjacência para Lista de Adjacência vice-versa
        AdjacencyMatrix adjacencyMatrixToConvert = new AdjacencyMatrix().generateRandomAdjacencyMatrix(4);
        System.out.println("Adjacency List : \n" +
                adjacencyMatrixToConvert.undirectedAdjacencyMatrixToAdjacencyList()
        );

        // Questão bônus - Matriz de Adjacência para Matriz de Incidência (estava na antiga versão do trabalho)
        //adjacencyMatrixToConvert.setDirected(true);
        System.out.println("Adjacency Matrix to Convert:");
        System.out.println(adjacencyMatrixToConvert.toString());

        System.out.println("Converted, result :");
        IncidenceMatrix incidenceMatrix = adjacencyMatrixToConvert.adjacencyMatrixToIncidenceMatrix();
        System.out.println(incidenceMatrix.toString());


        ///////////////////////////////Questões 20 e 21//////////////////////////////////////
        //Questão 4 - Matriz de adjacência para Estrela direta e Estrela reversa
        //Exemplo random
        System.out.println("Adjacency Matrix to Convert to Direct Star:");
        System.out.println(adjacencyMatrixToConvert.toString());

        System.out.println("Converted, result :");
        DirectStar directStar = adjacencyMatrixToConvert.adjacencyMatrixToDirectStar();
        System.out.println(directStar);

        System.out.println();

        //Exemplo do slide
        //Matriz para estrela direta
        System.out.println("Adjacency Matrix to Convert to Direct Star:");
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(GraphUtils.matrix);
        System.out.println(adjacencyMatrix.toString());

        DirectStar directStar1 = adjacencyMatrix.adjacencyMatrixToDirectStar();

        System.out.println("Converted, result :");
        System.out.println(directStar1);

        //Matriz para estrela direta duas linhas em branco consecultivas
        System.out.println("Adjacency Matrix to Convert to Direct Star (with two consecutive vertices that are not the origin):");
        AdjacencyMatrix adjacencyMatrix2 = new AdjacencyMatrix(GraphUtils.matrix2);
        System.out.println(adjacencyMatrix2.toString());

        DirectStar directStar2 = adjacencyMatrix2.adjacencyMatrixToDirectStar();

        System.out.println("Converted, result :");
        System.out.println(directStar2);

        //Estrela reversa (Exemplo do livro)
        System.out.println("Adjacency Matrix to Convert to Reverse Star:");
        System.out.println(adjacencyMatrix.toString());

        ReverseStar reverseStar = adjacencyMatrix.adjacencyMatrixToReverseStar();

        System.out.println("Converted, result :");
        System.out.println(reverseStar);
        ///////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////Questão 13 /////////////////////////////////////////
        // Gerar Código de Prüffer

        AdjacencyMatrix treeAdjacencyMatrix = new AdjacencyMatrix(GraphUtils.treeMatrix);
        System.out.println("Matriz de Adjacência da Árvore:");
        System.out.println(treeAdjacencyMatrix.toString());

        List<Integer> prufferCode = treeAdjacencyMatrix.generatePrufferCode();
        System.out.println("Código de Prüffer:");
        System.out.println(prufferCode);

        //Exemplo do slide

        AdjacencyMatrix treeAdjacencyMatrix2 = new AdjacencyMatrix(GraphUtils.treeMatrix2);
        System.out.println("Matriz de Adjacência da Árvore:");
        System.out.println(treeAdjacencyMatrix2.toString());

        List<Integer> prufferCode2 = treeAdjacencyMatrix2.generatePrufferCode();
        System.out.println("Código de Prüffer:");
        System.out.println(prufferCode2);
        ///////////////////////////////////////////////////////////////////////////////////////////


        ///////////////////////////////Questão 22////////////////////////////////////////////////
        // Questão de busca em profundidade com matriz de adjacência
        System.out.println("\nTeste da Busca em Profundidade (DFS):");
        adjacencyMatrixToConvert.depthFirstSearch(0);
        AdjacencyMatrix slideExample = new AdjacencyMatrix(GraphUtils.matrix3);
        slideExample.depthFirstSearch(6);

        // Questão de busca em profundidade com lista de adjacência
        System.out.println("\nTeste da Busca em Profundidade com Recorrência:");
        graph.getAdjacencyList().BuscaProfundidade(1);

        // Questão de busca em profundidade com lista de adjacência salvando o predecessor
        System.out.println("\nTeste da Busca em Profundidade com Predecessor:");
        graph.getAdjacencyList().BuscaProfundidadeComPredecessor(1);
        ///////////////////////////////////////////////////////////////////////////////////////////
    }
}