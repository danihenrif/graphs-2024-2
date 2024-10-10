package br.ufrn.imd;

import java.util.*;

public class Graph {
    private AdjacencyList adjacencyList;
    private AdjacencyMatrix adjacencyMatrix;


    public Graph() {
        this.adjacencyList = new AdjacencyList();
        this.adjacencyMatrix = new AdjacencyMatrix();
    }

    public void loadGraphFromFile(String filename) {
        this.adjacencyList.loadGraphFromFile(filename);
        //this.adjacencyMatrix.loadGraphFromFile(filename);
    }

    @Override
    public String toString() {
        return adjacencyList.toString() + "\n" + adjacencyMatrix.toString();
    }

    public String addVertex(Integer vertex) {
        return adjacencyList.addVertex(vertex);
    }

    public String removeVertex(Integer vertex) {
        return adjacencyList.removeVertex(vertex);
    }

    public String undirectedAdjacencyMatrixToAdjacencyList(AdjacencyMatrix adjMatrix) {
        AdjacencyList aux = adjacencyMatrix.undirectedAdjacencyMatrixToAdjacencyList(adjMatrix);
        if(aux != null) {
            this.adjacencyList = aux;
            this.adjacencyMatrix = adjMatrix;
            return "Conversion successful. Adjacency list:\n" + this.adjacencyList.toString();
        }
        return "Conversion failed";
    }

}
