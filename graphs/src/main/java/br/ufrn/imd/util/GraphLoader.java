package br.ufrn.imd.util;

import br.ufrn.imd.Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class GraphLoader {

    public static String load(Graph g, String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Ler se é direcionado ou não
            g.getAdjacencyList().setDirected(readGraphType(br));
            System.out.println("Grafo direcionado: " + g.getAdjacencyList().isDirected());

            // Ler os rótulos dos vértices
            readVertices(g, br);

            // Ler as arestas
            readEdges(g, br);

            return "Grafo carregado com sucesso!\n";
        } catch (IOException e) {
            e.printStackTrace();
            return "Falha ao carregar o grafo: " + e.getMessage();
        } catch (NumberFormatException e) {
            return "Erro ao converter um vértice para Integer: " + e.getMessage();
        }
    }

    private static boolean readGraphType(BufferedReader br) throws IOException {
        String line = br.readLine();
        return line.trim().equalsIgnoreCase("d");
    }

    private static void readVertices(Graph g, BufferedReader br) throws IOException {
        String line = br.readLine();
        if (line != null) {
            System.out.println("Vértices lidos: " + line);
            String[] vertices = line.split(",");
            for (String vertex : vertices) {
                int vertexId = Integer.parseInt(vertex.trim());
                g.getAdjacencyList().getList().put(vertexId, new HashSet<>());
            }
        }
    }

    private static void readEdges(Graph g, BufferedReader br) throws IOException {
        String line = br.readLine(); // Ler a linha das arestas
        if (line != null) {
            System.out.println("Arestas lidas: " + line);
            String[] edges = line.split(" "); // Dividir arestas por espaço
            for (String edge : edges) {
                addEdge(g, edge.trim());
            }
        }
    }

    private static void addEdge(Graph g, String edge) {
        if (edge.startsWith("(") && edge.endsWith(")")) {
            String[] endpoints = edge.substring(1, edge.length() - 1).split(",");
            if (endpoints.length == 2) {
                try {
                    int from = Integer.parseInt(endpoints[0].trim());
                    int to = Integer.parseInt(endpoints[1].trim());

                    if (g.getAdjacencyList().getList().containsKey(from) && g.getAdjacencyList().getList().containsKey(to)) {
                        g.getAdjacencyList().getList().get(from).add(to);
                        if (!g.getAdjacencyList().isDirected()) {
                            g.getAdjacencyList().getList().get(to).add(from); // Se o grafo for não direcionado
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
