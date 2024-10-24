package br.ufrn.imd.datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class Star {
  protected Map<Integer, List<Integer>> arcs;
  protected Integer[] pont;

  public Star() {
    this.arcs = new TreeMap<>();
  }

  public Star(Integer numberOfArcs, Integer numberOfVertex) {
    this();
    for (int i = 0; i < numberOfArcs; i++) {
      arcs.put(i, new ArrayList<>());
    }

    this.pont = new Integer[numberOfVertex + 1];

    pont[0] = 0;
    pont[numberOfVertex] = numberOfArcs;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<Integer, List<Integer>> entry : arcs.entrySet()) {
      sb.append("Arch ").append(entry.getKey()).append(": ");
      List<Integer> vertices = entry.getValue();
      sb.append(vertices.get(0)).append(" -> ").append(vertices.get(1)).append("\n");
    }
    sb.append("Pont: ").append("\n[");
    for (int i = 0; i < pont.length; i++) {
      if (i == pont.length - 1) {
        sb.append(pont[i]).append("]");
      } else sb.append(pont[i]).append(",");
    }
    return sb.toString();
  }
}
