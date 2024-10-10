package br.ufrn.imd;

import java.util.*;

public class Graph {
    private AdjacencyList adjacencyList;


    public Graph() {
        this.adjacencyList = new AdjacencyList();
    }

    public void loadGraphFromFile(String filename) {
        this.adjacencyList.loadGraphFromFile(filename);
        //this.adjacencyMatrix.loadGraphFromFile(filename);
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
