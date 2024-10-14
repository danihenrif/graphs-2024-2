package br.ufrn.imd.util ;

import br.ufrn.imd.datastructures.AdjacencyMatrix;
import br.ufrn.imd.datastructures.DirectStar;
import br.ufrn.imd.datastructures.ReverseStar;
import br.ufrn.imd.datastructures.Star;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphUtils {
    public static List<List<Integer>> treeMatrix = new ArrayList<>();
    public static List<List<Integer>> treeMatrix2 = new ArrayList<>();
    public static List<List<Integer>> matrix = new ArrayList<>();
    public static List<List<Integer>> matrix2 = new ArrayList<>();
    public static List<List<Integer>> matrix3 = new ArrayList<>();

    static {
        matrix.add(Arrays.asList(0, 1, 1, 0, 0));
        matrix.add(Arrays.asList(0, 0, 1, 0, 1));
        matrix.add(Arrays.asList(0, 0, 0, 0, 0));
        matrix.add(Arrays.asList(0, 0, 1, 0, 1));
        matrix.add(Arrays.asList(0, 0, 0, 1, 0));

        matrix2.add(Arrays.asList(0, 1, 1, 0, 0));
        matrix2.add(Arrays.asList(0, 0, 1, 0, 1));
        matrix2.add(Arrays.asList(0, 0, 0, 0, 0));
        matrix2.add(Arrays.asList(0, 0, 0, 0, 0));
        matrix2.add(Arrays.asList(0, 0, 0, 1, 0));

        matrix3.add(Arrays.asList(0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0)); // 1 -> 2, 3
        matrix3.add(Arrays.asList(1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1)); // 2 -> 1, 3, 4, 8, 11
        matrix3.add(Arrays.asList(1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0)); // 3 -> 1, 2, 4, 5
        matrix3.add(Arrays.asList(0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0)); // 4 -> 2, 3, 4, 8, 9
        matrix3.add(Arrays.asList(0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0)); // 5 -> 3, 6
        matrix3.add(Arrays.asList(0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0)); // 6 -> 5
        matrix3.add(Arrays.asList(0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1)); // 7 -> 4, 10, 11
        matrix3.add(Arrays.asList(0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0)); // 8 -> 2, 4
        matrix3.add(Arrays.asList(0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1)); // 9 -> 4, 11
        matrix3.add(Arrays.asList(0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1)); // 10 -> 7, 11
        matrix3.add(Arrays.asList(0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0)); // 11 -> 2, 7, 9, 10


        treeMatrix.add(Arrays.asList(0,1,1,0,0));
        treeMatrix.add(Arrays.asList(1,0,0,1,1));
        treeMatrix.add(Arrays.asList(1,0,0,0,0));
        treeMatrix.add(Arrays.asList(0,1,0,0,0));
        treeMatrix.add(Arrays.asList(0,1,0,0,0));

        treeMatrix2.add(Arrays.asList(0, 0, 1, 0, 0, 0, 0, 0, 0, 0)); // Vértice 0
        treeMatrix2.add(Arrays.asList(0, 0, 1, 0, 0, 0, 0, 0, 0, 0)); // Vértice 1
        treeMatrix2.add(Arrays.asList(1, 1, 0, 1, 0, 1, 0, 0, 0, 0)); // Vértice 2
        treeMatrix2.add(Arrays.asList(0, 0, 1, 0, 0, 0, 0, 0, 0, 0)); // Vértice 3
        treeMatrix2.add(Arrays.asList(0, 0, 0, 0, 0, 1, 0, 0, 0, 0)); // Vértice 4
        treeMatrix2.add(Arrays.asList(0, 0, 1, 0, 1, 0, 1, 0, 1, 0)); // Vértice 5
        treeMatrix2.add(Arrays.asList(0, 0, 0, 0, 0, 1, 0, 0, 0, 0)); // Vértice 6
        treeMatrix2.add(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 1, 0)); // Vértice 7
        treeMatrix2.add(Arrays.asList(0, 0, 0, 0, 0, 1, 0, 1, 0, 1)); // Vértice 8
        treeMatrix2.add(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 1, 0)); // Vértice 9
    }


    public static void validateAdjacencyMatrix(AdjacencyMatrix adjMatrix) {
        // Verificar se a matriz é nula
        if (adjMatrix.getMatrix() == null) {
            throw new IllegalArgumentException("A matriz não pode ser nula.");
        }

        Integer numberOfVertex = adjMatrix.getMatrix().size();

        // Verificar se a matriz não está vazia
        if (numberOfVertex == 0) {
            throw new IllegalArgumentException("A matriz não pode ser vazia.");
        }

        // Verificar se a matriz é quadrada
        for (List<Integer> row : adjMatrix.getMatrix()) {
            if (row.size() != numberOfVertex) {
                throw new IllegalArgumentException("A matriz deve ser quadrada (n x n).");
            }
        }

        // Verificar se a matriz contém apenas 0s e 1s
        for (int i = 0; i < numberOfVertex; i++) {
            for (int j = 0; j < numberOfVertex; j++) {
                int value = adjMatrix.getMatrix().get(i).get(j);
                if (value != 0 && value != 1) {
                    throw new IllegalArgumentException("A matriz de adjacência deve conter apenas valores 0 ou 1.");
                }
            }
        }
    }

    public static Integer[] numberOfVertexAndEdges(List<List<Integer>> matrix){
        int numberOfVertices = matrix.size();
        int numberOfEdges = 0;

        // Contar o número total de arestas
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < numberOfVertices; j++) {
                if (matrix.get(i).get(j) == 1 && i != j) {
                    numberOfEdges++;
                }
            }
        }

        Integer[] vertexAndEdges =  new Integer[]{numberOfVertices,numberOfEdges};

        return vertexAndEdges;
    }

}