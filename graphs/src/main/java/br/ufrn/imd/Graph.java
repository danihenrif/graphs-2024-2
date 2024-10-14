package br.ufrn.imd;

import br.ufrn.imd.datastructures.AdjacencyList;
import br.ufrn.imd.datastructures.AdjacencyMatrix;

public class Graph {
    private final AdjacencyList adjacencyList;
    private final AdjacencyMatrix adjacencyMatrix;

    public Graph() {
        this.adjacencyList = new AdjacencyList();
        this.adjacencyMatrix = new AdjacencyMatrix();
    }

    public AdjacencyList getAdjacencyList() {
        return adjacencyList;
    }

    public AdjacencyMatrix getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    @Override
    public String toString() {
        return adjacencyList.toString();
    }

    public String addVertex(Integer vertex) {
        return adjacencyList.addVertex(vertex);
    }

    public String removeVertex(Integer vertex) {
        return adjacencyList.removeVertex(vertex);
    }
}
