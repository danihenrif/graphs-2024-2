package br.ufrn.imd.datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class Star {
  protected Map<Integer, List<Integer>> arches;
  protected Integer[] pont;

  public abstract void extractArches(
      List<List<Integer>> matrix, Integer numberOfVertex, Integer numberOfArches);

  public Star() {
    this.arches = new TreeMap<>();
  }

  public Star(Integer numberOfArches, Integer numberOfVertex) {
    this();
    for (int i = 0; i < numberOfArches; i++) {
      arches.put(i, new ArrayList<>());
    }

    this.pont = new Integer[numberOfVertex + 1];

    pont[0] = 0;
    pont[numberOfVertex] = numberOfArches;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<Integer, List<Integer>> entry : arches.entrySet()) {
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
