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

    public void BuscaProfundidade(int vertice){
        int n = list.size();
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        visited[vertice] = true;

        stack.push(vertice);

        for(int w: list.get(vertice)){
            if (!visited[w]){
                System.out.println("Visitando aresta (" + vertice + ", " + w + ")");
                BuscaProfundidade(w);
            }else if(stack.contains(w) && !((stack.indexOf(vertice) - stack.indexOf(w)) == 1)){
                System.out.println("Visitando aresta (" + vertice + ", " + w + ")");
            }
        }

        stack.pop();
    }
}
