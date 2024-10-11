package br.ufrn.imd.datastructures;

import br.ufrn.imd.util.GraphUtils;

import java.util.*;


public class AdjacencyMatrix {
    private List<List<Integer>> matrix;
    private boolean isDirected;

    public AdjacencyMatrix() {
        this.matrix = new ArrayList<>();
    }

    public AdjacencyMatrix(List<List<Integer>> matrix) {
        this.matrix = matrix;
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

    public void setDirected(boolean directed) {
        isDirected = directed;
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

    public static AdjacencyList undirectedAdjacencyMatrixToAdjacencyList(AdjacencyMatrix adjMatrix) {
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

    public IncidenceMatrix adjacencyMatrixToIncidenceMatrix() {
        Integer[] numberOfVertexAndEdges = GraphUtils.numberOfVertexAndEdges(matrix);
        Integer numberOfVertex = numberOfVertexAndEdges[0];
        Integer numberOfEdges = numberOfVertexAndEdges[1];

        // Criar a matriz de incidência
        IncidenceMatrix incidenceMatrix = new IncidenceMatrix(numberOfVertex,numberOfEdges);
        int edgeIndex = 0;

        // Preencher a matriz de incidência
        for (int i = 0; i < numberOfVertex; i++) {
            for (int j = 0; j < numberOfVertex; j++) {
                if(edgeIndex < numberOfEdges) {
                    if (matrix.get(i).get(j) == 1) {
                        incidenceMatrix.getMatrix().get(edgeIndex).set(i, -1); // i é a origem da aresta
                        incidenceMatrix.getMatrix().get(edgeIndex).set(j, 1);  // j é o destino da aresta
                        edgeIndex++;
                    }
                }
            }
        }
    
        // Retornar a matriz de incidência
        return incidenceMatrix;
    }

    public DirectStar adjacencyMatrixToDirectStar() {
        Integer[] numberOfVertexAndEdges = GraphUtils.numberOfVertexAndEdges(matrix);
        Integer numberOfVertex = numberOfVertexAndEdges[0];
        Integer numberOfArches = numberOfVertexAndEdges[1];

        DirectStar directStar = new DirectStar(numberOfArches, numberOfVertex);
        //Extrair os arcos da matriz de adjacência
        directStar.extractArches(this.matrix, numberOfVertex, numberOfArches);

        //Preenche o miolo da pont
        Integer searchedVertex = 1;
        for (Integer key : directStar.getArches().keySet()) {
            //Se o vértice origem for maior que vértice buscado pula
            //o vértice pois ele não é origem de nenhum arco
            if(directStar.getArches().get(key).get(0) > searchedVertex){
                searchedVertex++;
            }
            if (directStar.getArches().get(key).get(0) == searchedVertex && searchedVertex < numberOfVertex) {
                //se achar o vértice como origem preenche a pont dele com o numero do arco
                directStar.getPont()[searchedVertex] = key;
                searchedVertex++;
            }
        }

        //Preencher as pont q não foram achadas com o valor dos vizinhos
        for(int i = 1 ; i < directStar.getPont().length; i++) {
            if(directStar.getPont()[i] == null){
                directStar.getPont()[i] = directStar.getPont()[i+1];
            }
        }

        return directStar;
    }

    public ReverseStar adjacencyMatrixToReverseStar() {
        Integer[] numberOfVertexAndEdges = GraphUtils.numberOfVertexAndEdges(matrix);
        Integer numberOfVertex = numberOfVertexAndEdges[0];
        Integer numberOfArches = numberOfVertexAndEdges[1];

        ReverseStar reverseStar = new ReverseStar(numberOfArches, numberOfVertex);
        //Extrair os arcos da matriz de adjacência
        reverseStar.extractArches(this.matrix, numberOfVertex, numberOfArches);

        //Preenche o miolo da pont
        Integer searchedVertex = 1;
        for (Integer key : reverseStar.getArches().keySet()) {
            //Se o vértice origem for maior que vértice buscado pula
            //o vértice pois ele não é origem de nenhum arco
            if(reverseStar.getArches().get(key).get(1) > searchedVertex){
                searchedVertex++;
            }
            if (reverseStar.getArches().get(key).get(1) == searchedVertex && searchedVertex < numberOfVertex) {
                //se achar o vértice como origem preenche a pont dele com o numero do arco
                reverseStar.getPont()[searchedVertex] = key;
                searchedVertex++;
            }
        }

        //Preencher as pont q não foram achadas com o valor dos vizinhos
        for(int i = 1 ; i < reverseStar.getPont().length; i++) {
            if(reverseStar.getPont()[i] == null){
                reverseStar.getPont()[i] = reverseStar.getPont()[i+1];
            }
        }

        return reverseStar;
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
