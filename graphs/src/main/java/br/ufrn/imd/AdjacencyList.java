package br.ufrn.imd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AdjacencyList {
    private Map<Integer, Set<Integer>> list; // Usando Integer para rótulos
    private boolean isDirected;

    public AdjacencyList() {
       this.list = new HashMap<>();
    }

    public AdjacencyList(Map<Integer, Set<Integer>> adjacencyList) {
        this.list = adjacencyList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Integer, Set<Integer>> entry : list.entrySet()) {
            sb.append(entry.getKey()).append(": ");

            Set<Integer> adjacent = entry.getValue();
            if (adjacent.isEmpty()) {
                sb.append("Nenhum vizinho");
            } else {
                for (Integer a : adjacent) {
                    sb.append(a).append(" ");
                }
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    public boolean isDirected() {
        return isDirected;
    }

    public void setDirected(boolean directed) {
        isDirected = directed;
    }

    public Map<Integer, Set<Integer>> getList() {
        return list;
    }

    public Map<Integer, Set<Integer>> getAdjacencyList() {
        return list;
    }

    public String addVertex(Integer vertex) {
        if (list.containsKey(vertex)) {
            return "Vértice já existe!";
        }
        list.put(vertex, new HashSet<>());
        return "Vértice " + vertex + " adicionado com sucesso!";
    }

    public String removeVertex(Integer vertex) {
        if (!list.containsKey(vertex)) {
            return "Vértice não encontrado!";
        }
        // Remover todas as arestas associadas ao vértice
        list.remove(vertex);
        for (Set<Integer> adjacent : list.values()) {
            adjacent.remove(vertex);
        }
        return "Vértice " + vertex + " removido com sucesso!";
    }


}