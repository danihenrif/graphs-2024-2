package br.ufrn.imd.datastructures;

import java.util.List;
import java.util.Map;

public class DirectStar extends Star {
    public DirectStar(Integer numberOfArcs, Integer numberOfVertex) {
        super(numberOfArcs, numberOfVertex);
    }

    public Integer[] getPont() {
        return pont;
    }

    public Map<Integer, List<Integer>> getArcs() {
        return arcs;
    }
}
