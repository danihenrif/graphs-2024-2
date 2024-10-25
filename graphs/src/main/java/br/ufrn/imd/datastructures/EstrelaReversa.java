package br.ufrn.imd.datastructures;

import java.util.List;
import java.util.Map;

public class EstrelaReversa extends Estrela {
    public EstrelaReversa(Integer numeroDeArcos, Integer numeroDeVertices) {
        super(numeroDeArcos, numeroDeVertices);
    }

    public Integer[] getPont() {
        return pont;
    }

    public Map<Integer, List<Integer>> getArcos() {
        return arcos;
    }
}
