package br.ufrn.imd.datastructures;

import java.util.*;

public class AdjacencyList {
    private final Map<Integer, Set<Integer>> list; // Usando Integer para rótulos
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

    public void BuscaProfundidade(int vertice) {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        dfs(vertice, visited, stack);
    }

    private void dfs(int vertice, Set<Integer> visited, Stack<Integer> stack) {
        visited.add(vertice);
        stack.push(vertice);

        for (int w : list.get(vertice)) {
            if (!visited.contains(w)) {
                System.out.println("Visitando aresta (" + vertice + ", " + w + ")");
                dfs(w, visited, stack);
            } else if (stack.contains(w) && !((stack.indexOf(vertice) - stack.indexOf(w)) == 1)) {
                System.out.println("Visitando aresta (" + vertice + ", " + w + ")");
            }
        }

        stack.pop();
    }

    public void BuscaProfundidadeComPredecessor(int vertice) {
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> predecessor = new HashMap<>();

        predecessor.put(vertice, null);

        dfsComPredecessor(vertice, visited, predecessor);

        System.out.println("\nPredecessores:");
        for (Map.Entry<Integer, Integer> entry : predecessor.entrySet()) {
            System.out.println("Vértice: " + entry.getKey() + ", Predecessor: " + entry.getValue());
        }
    }

    private void dfsComPredecessor(int vertice, Set<Integer> visited, Map<Integer, Integer> predecessor) {
        visited.add(vertice);

        for (int w : list.get(vertice)) {
            if (!visited.contains(w)) {
                predecessor.put(w, vertice);
                System.out.println("Visitando aresta (" + vertice + ", " + w + ")");
                dfsComPredecessor(w, visited, predecessor);
            }
        }
    }
}
