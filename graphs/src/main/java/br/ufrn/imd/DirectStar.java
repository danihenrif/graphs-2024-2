package br.ufrn.imd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectStar {
    private Map<Integer, List<Integer>> arches;
    private Integer[] pont;

    public DirectStar() {
        this.arches = new HashMap<>();
    }

    public DirectStar(Integer numberOfArchs, Integer numberOfVertex) {
        this();
        for (int i = 0; i < numberOfArchs; i++) {
            arches.put(i, new ArrayList<>());
        }

        this.pont = new Integer[numberOfVertex + 1];

        pont[0] = 0;
        pont[numberOfVertex] = numberOfArchs + 1;
    }

    public Map<Integer, List<Integer>> getArches() {
        return arches;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, List<Integer>> entry : arches.entrySet()) {
            sb.append("Arch ").append(entry.getKey()).append(": ");
            List<Integer> vertices = entry.getValue();
            sb.append(vertices.get(0)).append(" -> ").append(vertices.get(1)).append("\n");
        }
        sb.append("Pont: ").append("\n");
        for (int i = 0; i < pont.length; i++) {
            sb.append(pont[i]).append(",");
        }
        return sb.toString();
    }

}
