package br.ufrn.imd.datastructures;

import java.util.*;

public class DirectStar extends Star {
    public DirectStar(Integer numberOfArches, Integer numberOfVertex) {
        super(numberOfArches, numberOfVertex);
    }

    public void extractArches(List<List<Integer>> matrix, Integer numberOfVertex, Integer numberOfArches){
        int archIndex = 0;

        // Extrair arcos
        for (int i = 0; i < numberOfVertex; i++) {
            for (int j = 0; j < numberOfVertex; j++) {
                if(matrix.get(i).get(j) == 1 && archIndex < numberOfArches) {
                    arches.get(archIndex).add(i);
                    arches.get(archIndex).add(j);
                    archIndex++;
                }
            }
        }

    }

    public Integer[] getPont() {
        return pont;
    }
    public Map<Integer, List<Integer>> getArches() {
        return arches;
    }
}
