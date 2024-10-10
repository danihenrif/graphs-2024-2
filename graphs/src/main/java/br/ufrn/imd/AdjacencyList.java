package br.ufrn.imd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AdjacencyList {
    private Map<Integer, Set<Integer>> adjacencyList; // Usando Integer para rótulos
    private boolean isDirected;

    public AdjacencyList() {
       this.adjacencyList = new HashMap<>();
    }

    public AdjacencyList(Map<Integer, Set<Integer>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Integer, Set<Integer>> entry : adjacencyList.entrySet()) {
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

    public String addVertex(Integer vertex) {
        if (adjacencyList.containsKey(vertex)) {
            return "Vértice já existe!";
        }
        adjacencyList.put(vertex, new HashSet<>());
        return "Vértice " + vertex + " adicionado com sucesso!";
    }

    public String removeVertex(Integer vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            return "Vértice não encontrado!";
        }
        // Remover todas as arestas associadas ao vértice
        adjacencyList.remove(vertex);
        for (Set<Integer> adjacent : adjacencyList.values()) {
            adjacent.remove(vertex);
        }
        return "Vértice " + vertex + " removido com sucesso!";
    }



    public String loadGraphFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Ler se é direcionado ou não
            isDirected = readGraphType(br);
            System.out.println("Grafo direcionado: " + isDirected);

            // Ler os rótulos dos vértices
            readVertices(br);

            // Ler as arestas
            readEdges(br);

            return "Grafo carregado com sucesso!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Falha ao carregar o grafo: " + e.getMessage();
        } catch (NumberFormatException e) {
            return "Erro ao converter um vértice para Integer: " + e.getMessage();
        }
    }

    private boolean readGraphType(BufferedReader br) throws IOException {
        String line = br.readLine();
        return line.trim().equalsIgnoreCase("d");
    }

    private void readVertices(BufferedReader br) throws IOException {
        String line = br.readLine();
        if (line != null) {
            System.out.println("Vértices lidos: " + line);
            String[] vertices = line.split(",");
            for (String vertex : vertices) {
                int vertexId = Integer.parseInt(vertex.trim());
                adjacencyList.put(vertexId, new HashSet<>());
            }
        }
    }

    private void readEdges(BufferedReader br) throws IOException {
        String line = br.readLine(); // Ler a linha das arestas
        if (line != null) {
            System.out.println("Arestas lidas: " + line);
            String[] edges = line.split(" "); // Dividir arestas por espaço
            for (String edge : edges) {
                addEdge(edge.trim());
            }
        }
    }

    private void addEdge(String edge) {
        if (edge.startsWith("(") && edge.endsWith(")")) {
            String[] endpoints = edge.substring(1, edge.length() - 1).split(",");
            if (endpoints.length == 2) {
                try {
                    int from = Integer.parseInt(endpoints[0].trim());
                    int to = Integer.parseInt(endpoints[1].trim());

                    if (adjacencyList.containsKey(from) && adjacencyList.containsKey(to)) {
                        adjacencyList.get(from).add(to);
                        if (!isDirected) {
                            adjacencyList.get(to).add(from); // Se o grafo for não direcionado
                        }
                    } else {
                        System.err.println("Um dos vértices não existe: " + from + " ou " + to);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao converter vértice para Integer: " + e.getMessage());
                }
            }
        } else {
            System.err.println("Formato de aresta inválido: " + edge);
        }
    }

}
