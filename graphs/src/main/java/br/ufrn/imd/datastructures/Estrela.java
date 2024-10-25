package br.ufrn.imd.datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class Estrela {
  protected Map<Integer, List<Integer>> arcos;
  protected Integer[] pont;

  public Estrela() {
    this.arcos = new TreeMap<>();
  }

  public Estrela(Integer numeroDeArcos, Integer numeroDeVertices) {
    this();
    for (int i = 0; i < numeroDeArcos; i++) {
      arcos.put(i, new ArrayList<>());
    }

    this.pont = new Integer[numeroDeVertices + 1];

    pont[0] = 0;
    pont[numeroDeVertices] = numeroDeArcos;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<Integer, List<Integer>> entrada : arcos.entrySet()) {
      sb.append("Arco ").append(entrada.getKey()).append(": ");
      List<Integer> vertices = entrada.getValue();
      sb.append(vertices.get(0)).append(" -> ").append(vertices.get(1)).append("\n");
    }
    sb.append("Pont: ").append("\n[");
    for (int i = 0; i < pont.length; i++) {
      if (i == pont.length - 1) {
        sb.append(pont[i]).append("]");
      } else
        sb.append(pont[i]).append(",");
    }
    return sb.toString();
  }
}
