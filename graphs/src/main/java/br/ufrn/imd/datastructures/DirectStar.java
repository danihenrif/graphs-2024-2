package br.ufrn.imd.datastructures;

import java.util.*;

public class DirectStar extends Star {
    public DirectStar(Integer numberOfArches, Integer numberOfVertex) {
        super(numberOfArches, numberOfVertex);
    }

    public Integer[] getPont() {
        return pont;
    }
    public Map<Integer, List<Integer>> getArches() {
        return arches;
    }
}
