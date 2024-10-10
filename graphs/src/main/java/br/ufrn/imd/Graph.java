package br.ufrn.imd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Graph {
    private AdjacencyList adjacencyList;

    Graph(AdjacencyList adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public String loadGraphFromFile(String filename) {
        return this.adjacencyList.loadGraphFromFile(filename);
    }

    public void printGraph(){
        this.adjacencyList.printGraph();
    }

    public String addVertex(Integer vertex) {
        return adjacencyList.addVertex(vertex);
    }

    public String removeVertex(Integer vertex) {
        return adjacencyList.removeVertex(vertex);
    }

}
