package br.ufrn.imd.datastructures;

import java.util.ArrayList;
import java.util.List;

public class IncidenceMatrix {
    private List<List<Integer>> matrix;

    public IncidenceMatrix() {
        this.matrix = new ArrayList<>();
    }

    public IncidenceMatrix(Integer numberOfVertex, Integer numberOfEdges) {
        this();
        for (int i = 0; i < numberOfEdges; i++) {
            matrix.add(new ArrayList<>());
            for (int j = 0; j < numberOfVertex; j++) {
                matrix.get(i).add(0);
            }
        }
    }

    public List<List<Integer>> getMatrix() {
        return matrix;
    }

    public void setMatrix(List<List<Integer>> matrix) {
        this.matrix = matrix;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        int numberOfEdges = matrix.size();

        int numberOfVertices = matrix.isEmpty() ? 0 : matrix.getFirst().size();

        for (int i = 0; i < numberOfEdges; i++) {
            for (int j = 0; j < numberOfVertices; j++) {
                result.append(matrix.get(i).get(j)).append(" ");
            }
            result.append("\n");
        }

        return result.toString();
    }
}
