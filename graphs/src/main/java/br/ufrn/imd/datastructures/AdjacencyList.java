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

    private void dfs(int vertex, Set<Integer> visited, Stack<Integer> stack) {
        visited.add(vertex);
        stack.push(vertex);

        for (int w : list.get(vertex)) {
            if (!visited.contains(w)) {
                System.out.println("Visitando aresta (" + vertex + ", " + w + ")");
                dfs(w, visited, stack);
            } else if (stack.contains(w) && !((stack.indexOf(vertex) - stack.indexOf(w)) == 1)) {
                System.out.println("Visitando aresta (" + vertex + ", " + w + ")");
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

    public AdjacencyList generateRandomAdjacencyList(int size, boolean isDirected) {
        Random random = new Random();

        // Inicializa a lista de adjacência para cada vértice
        for (int i = 0; i < size; i++) {
            list.putIfAbsent(i, new HashSet<>()); // Garante que cada vértice tenha um conjunto associado
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j && random.nextInt(2) == 1) { // Evitar laços e gerar aresta aleatória
                    list.get(i).add(j); // Adiciona aresta de i para j

                    if (!isDirected) {
                        list.get(j).add(i); // No grafo não direcionado, adicionar aresta de j para i
                    }
                }
            }
        }

        System.out.println("Generated adjacency list: ");
        System.out.println(this);
        return this;
    }

    public AdjacencyMatrix undirectedAdjacencyListToAdjacencyMatrix() {
        AdjacencyMatrix adjMatrix = new AdjacencyMatrix(numberOfVertix());
        for(int i = 0 ; i < numberOfVertix() ; i++){
            for(Integer adj : list.get(i)){
                adjMatrix.getMatrix().get(i).set(adj, 1);
                adjMatrix.getMatrix().get(adj).set(i, 1);
            }
        }
        return adjMatrix;
    }

    public IncidenceMatrix directedAdjacencyListToIncidenceMatrix() {
        Integer vertix = numberOfVertix();
        Integer arcs = numberOfArcs();

        IncidenceMatrix incMatrix = new IncidenceMatrix(vertix, arcs);
        int arcIndex = 0; // Para rastrear a posição do arco na matriz de incidência

        for (int i = 0; i < numberOfVertix(); i++) {
            for (Integer adj : list.get(i)) {
                // Para cada arco de i para adj
                incMatrix.getMatrix().get(arcIndex).set(i, -1); // -1 na linha do vértice de origem
                incMatrix.getMatrix().get(arcIndex).set(adj, 1); // +1 na linha do vértice de destino
                arcIndex++; // Avançar para a próxima coluna na matriz de incidência
            }
        }
        return incMatrix;
    }

    public Integer numberOfVertix() {
        return list.size();
    }

    /**
     * @return Quantidade de arcos em um grafo direcionado
     */
    public Integer numberOfArcs() {
        int count = 0; // Usar 'int' para contagem
        for (int i = 0; i < numberOfVertix(); i++) {
            Set<Integer> adjacents = list.get(i); // Obter os adjacentes para o vértice i
            if (adjacents != null) { // Verifica se não é nulo
                count += adjacents.size(); // Conta o número de arcos saindo do vértice i
            }
        }
        return isDirected ? count : count/2; // Se não for direcionado, divide por 2
    }

    public void degreeVertex(){
        if (isDirected){
            /* Para Grafos direcionados */
            Map<Integer, Integer> inDegreeMap = new HashMap<>();
            for (Integer vertex : list.keySet()) {
                inDegreeMap.put(vertex, 0);
            }
            for (Set<Integer> set : list.values()) {
                for (Integer vertex : set) {
                    inDegreeMap.put(vertex, inDegreeMap.get(vertex) + 1);
                }
            }
            for (Map.Entry<Integer, Set<Integer>> aux : list.entrySet()) {
                int vertex = aux.getKey();
                int outDegree = aux.getValue().size();
                int inDegree = inDegreeMap.get(vertex);
                System.out.println("O vértice "+ vertex +" tem grau de saída " + outDegree + " e grau de entrada " +inDegree);
            }

        }else {
            /* Para Grafos não direcionados */
            for (Map.Entry<Integer, Set<Integer>> aux : list.entrySet()) {
                int vertex = aux.getKey();
                int degree = aux.getValue().size();
                System.out.println("Vértice " + vertex + " tem grau " + degree);
            }
        }
    }

    public void adjacencyVertex(int vertice1, int vertice2){
        if(list.get(vertice1).contains(vertice2) || list.get(vertice2).contains(vertice1)){
            System.out.println("Os vertices são adjacentes.");
        }else{
            System.out.println("Os vertices não são adjacentes.");
        }
    }

    public boolean connectivity(){
        if (list.isEmpty()){
            return true;
        }

        Set<Integer> visited = new HashSet<>();
        Integer firstVertex = list.keySet().iterator().next();
        Stack<Integer> stack = new Stack<>();

        dfs(firstVertex, visited, stack);

        if (visited.size() == list.size()){
            return true;
        }else{
            return false;
        }
    }

    public boolean bipartite(){
        Map<Integer, Integer> colors = new HashMap<>();

        for (int vertex : list.keySet()) {
            if (!colors.containsKey(vertex)) {
                if (!bfsColor(vertex, colors)){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean bfsColor (int firstVertex, Map<Integer, Integer> colors){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(firstVertex);
        colors.put(firstVertex, 0);

        while (!queue.isEmpty()){
            int vertex = queue.poll();
            int color = colors.get(vertex);

            for (Integer adj : list.get(vertex)) {
                if (!colors.containsKey(adj)) {
                    colors.put(adj, 1 - color);
                    queue.add(adj);
                } else if (colors.get(adj) == color) {
                    return false;
                }
            }
        }
        return true;
    }
}

