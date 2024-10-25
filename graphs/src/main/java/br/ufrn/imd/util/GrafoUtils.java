package br.ufrn.imd.util;

import java.util.List;

public class GrafoUtils {

    public static void validarMatrizAdjacencia(List<List<Integer>> matriz) {
        if (matriz == null) {
            throw new IllegalArgumentException("A matriz não pode ser nula.");
        }

        Integer numeroDeVertices = matriz.size();

        if (numeroDeVertices == 0) {
            throw new IllegalArgumentException("A matriz não pode ser vazia.");
        }

        for (List<Integer> linha : matriz) {
            if (linha.size() != numeroDeVertices) {
                throw new IllegalArgumentException("A matriz deve ser quadrada (n x n).");
            }
        }

        for (int i = 0; i < numeroDeVertices; i++) {
            for (int j = 0; j < numeroDeVertices; j++) {
                int valor = matriz.get(i).get(j);
                if (valor != 0 && valor != 1) {
                    throw new IllegalArgumentException("A matriz de adjacência deve conter apenas valores 0 ou 1.");
                }
            }
        }
    }

    public static Integer[] obterNumeroVerticesEArestas(List<List<Integer>> matriz) {
        int numeroDeVertices = matriz.size();
        int numeroDeArestas = 0;

        for (int i = 0; i < numeroDeVertices; i++) {
            for (int j = 0; j < numeroDeVertices; j++) {
                if (matriz.get(i).get(j) == 1 && i != j) {
                    numeroDeArestas++;
                }
            }
        }

        return new Integer[] { numeroDeVertices, numeroDeArestas };
    }
}
