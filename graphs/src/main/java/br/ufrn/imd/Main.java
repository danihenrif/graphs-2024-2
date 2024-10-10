package br.ufrn.imd;

public class Main {
    public static void main(String[] args) {
        AdjacencyList adjacencyList = new AdjacencyList();
        Graph graph = new Graph(adjacencyList);

        graph.loadGraphFromFile("src/grafo" + ".txt");

        graph.printGraph();
        System.out.println(graph.removeVertex(1));
        graph.printGraph();

    }
}
