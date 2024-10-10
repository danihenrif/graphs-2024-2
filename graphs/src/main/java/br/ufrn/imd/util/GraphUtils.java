package br.ufrn.imd.util ;

import br.ufrn.imd.AdjacencyMatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphUtils {
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

    public static Integer[] numberOfVertixAndEdges(List<List<Integer>> matrix){
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

        Integer[] vertixAndEdges =  new Integer[]{numberOfVertices,numberOfEdges};

        return vertixAndEdges;
    }
}
