package br.ufrn.imd;

import br.ufrn.imd.datastructures.AdjacencyList;

public class Graph {
    private AdjacencyList adjacencyList;

    public Graph() {
        this.adjacencyList = new AdjacencyList();
    }

    public AdjacencyList getAdjacencyList() {
        return adjacencyList;
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
