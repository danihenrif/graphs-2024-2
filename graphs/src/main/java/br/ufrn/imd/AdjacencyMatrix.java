package br.ufrn.imd;

import br.ufrn.imd.util.GraphUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class AdjacencyMatrix {
    private List<List<Integer>> matrix;
    private boolean isDirected;

    public AdjacencyMatrix() {
        this.matrix = new ArrayList<>();
    }

    public List<List<Integer>> getMatrix() {
        return matrix;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int size = matrix.size();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result.append(matrix.get(i).get(j)).append(" ");
            }
            result.append("\n");
        }

        return result.toString();
    }

    public AdjacencyMatrix generateRandomAdjacencyMatrix(Integer size) {
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                //Evitar laço
                if (i == j) {
                    row.add(0);
                } else {
                    row.add(random.nextInt(2));
                }
            }
            matrix.add((ArrayList<Integer>) row);
        }
        System.out.println("Generated adjacency matrix: ");
        System.out.println(toString());
        return this;
    }

    public AdjacencyList undirectedAdjacencyMatrixToAdjacencyList(AdjacencyMatrix adjMatrix) {
        GraphUtils.validateAdjacencyMatrix(adjMatrix);

        Integer numberOfVertex = adjMatrix.getMatrix().size();

        // Inicializar a lista de adjacência
        Map<Integer, Set<Integer>> adjlist = new HashMap<>();
        for (int i = 0; i < numberOfVertex; i++) {
            adjlist.put(i, new HashSet<>());
        }

        // Percorrer a matriz de adjacência
        for (int i = 0; i < numberOfVertex; i++) {
            for (int j = 0; j < numberOfVertex; j++) {
                // Se há uma aresta entre os vértices i e j
                if (adjMatrix.getMatrix().get(i).get(j) == 1) {
                    adjlist.get(i).add(j); // Adiciona j à lista de adjacência de i
                    adjlist.get(j).add(i); // Adiciona i à lista de adjacência de j (grafo não direcionado)
                }
            }
        }

        return new AdjacencyList(adjlist); // Retorna a lista de adjacência construída
    }

    /*public void loadGraphFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Ler se é direcionado ou não
            isDirected = readGraphType(br);
            System.out.println("Directed graph: " + isDirected);

            // Ler o número de vértices
            int numberOfVertices = readNumberOfVertices(br);

            // Inicializar a matriz de adjacência com o número de vértices
            initializeMatrix(numberOfVertices);

            // Ler as arestas e preencher a matriz
            readEdges(br, isDirected);

            System.out.println("Graph loaded successfully!");
            System.out.println(this);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the graph: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error converting a vertex to Integer: " + e.getMessage());
        }
    }

    private boolean readGraphType(BufferedReader br) throws IOException {
        String line = br.readLine();
        return line.trim().equalsIgnoreCase("d");
    }

    private int readNumberOfVertices(BufferedReader br) throws IOException {
        String line = br.readLine();
        return Integer.parseInt(line.trim());
    }

    private void initializeMatrix(int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            matrix.add(new ArrayList<>());
            for (int j = 0; j < numberOfVertices; j++) {
                matrix.get(i).add(0); // Inicializa com 0 (sem conexões)
            }
        }
    }

    private void readEdges(BufferedReader br, boolean isDirected) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            String[] edge = line.split(",");
            int from = Integer.parseInt(edge[0].trim());
            int to = Integer.parseInt(edge[1].trim());

            // Adiciona a aresta à matriz de adjacência
            matrix.get(from).set(to, 1);
            if (!isDirected) {
                matrix.get(to).set(from, 1); // Se for não direcionado, adicione a aresta reversa
            }
        }
    }*/
}
