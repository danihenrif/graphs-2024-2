package br.ufrn.imd.datastructures;

import java.util.ArrayList;
import java.util.List;

public class MatrizIncidencia {
    private List<List<Integer>> matriz;

    public MatrizIncidencia() {
        this.matriz = new ArrayList<>();
    }

    public MatrizIncidencia(Integer numeroDeVertices, Integer numeroDeArestas) {
        this();
        for (int i = 0; i < numeroDeArestas; i++) {
            matriz.add(new ArrayList<>());
            for (int j = 0; j < numeroDeVertices; j++) {
                matriz.get(i).add(0);
            }
        }
    }

    public List<List<Integer>> getMatriz() {
        return matriz;
    }

    public void setMatriz(List<List<Integer>> matriz) {
        this.matriz = matriz;
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();

        int numeroDeArestas = matriz.size();

        int numeroDeVertices = matriz.isEmpty() ? 0 : matriz.get(0).size();

        for (int i = 0; i < numeroDeArestas; i++) {
            for (int j = 0; j < numeroDeVertices; j++) {
                resultado.append(matriz.get(i).get(j)).append(" ");
            }
            resultado.append("\n");
        }

        return resultado.toString();
    }
}
