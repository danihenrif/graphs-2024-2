package br.ufrn.imd.datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ReverseStar extends Star {
    public ReverseStar(Integer numberOfArches, Integer numberOfVertex) {
        super(numberOfArches, numberOfVertex);
    }

    public Integer[] getPont() {
        return pont;
    }
    public Map<Integer, List<Integer>> getArches() {
        return arches;
    }
}
