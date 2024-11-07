package br.ufrn.imd.util;

import br.ufrn.imd.estruturasdedados.ListaAdjacencia;

import java.util.*;

public class GrafoUtils {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_AZUL = "\u001B[34m";
    private static final String ANSI_VERDE = "\u001B[32m";
    private static final String ANSI_AMARELO = "\u001B[33m";

    public static Map<Integer, Set<Integer>> listaGrafo1 = new HashMap<>();
    public static Map<Integer, Set<Integer>>  listaGrafo2 = new HashMap<>();
    public static Map<Integer, Set<Integer>>  listaGrafo3 = new HashMap<>();
    public static Map<Integer, Set<Integer>>  listaDigrafo1 = new HashMap<>();
    public static Map<Integer, Set<Integer>>  listaDigrafo2 = new HashMap<>();
    public static Map<Integer, Set<Integer>>  listaDigrafo3 = new HashMap<>();
    public static List<List<Integer>> arvoreExemploSala = new ArrayList<>();



    static {
        listaGrafo1.put(0, new HashSet<>(Arrays.asList(1)));
        listaGrafo1.put(1, new HashSet<>(Arrays.asList(0,2,3,4,5)));
        listaGrafo1.put(2, new HashSet<>(Arrays.asList(1,3,4,7)));
        listaGrafo1.put(3, new HashSet<>(Arrays.asList(1,2,6)));
        listaGrafo1.put(4, new HashSet<>(Arrays.asList(1,2)));
        listaGrafo1.put(5, new HashSet<>(Arrays.asList(1)));
        listaGrafo1.put(6, new HashSet<>(Arrays.asList(3)));
        listaGrafo1.put(7, new HashSet<>(Arrays.asList(2)));

        listaGrafo2.put(0, new HashSet<>(Arrays.asList(1, 2, 5)));
        listaGrafo2.put(1, new HashSet<>(Arrays.asList(0, 2, 4, 5)));
        listaGrafo2.put(2, new HashSet<>(Arrays.asList(0, 1, 3)));
        listaGrafo2.put(3, new HashSet<>(Arrays.asList(2, 4)));
        listaGrafo2.put(4, new HashSet<>(Arrays.asList(1, 3)));
        listaGrafo2.put(5, new HashSet<>(Arrays.asList(1, 6, 0)));
        listaGrafo2.put(6, new HashSet<>(Arrays.asList(5)));
        listaGrafo2.put(7, new HashSet<>(Arrays.asList(8, 9, 10)));
        listaGrafo2.put(8, new HashSet<>(Arrays.asList(7, 9)));
        listaGrafo2.put(9, new HashSet<>(Arrays.asList(7, 8)));
        listaGrafo2.put(10, new HashSet<>(Arrays.asList(7)));

        listaGrafo3.put(0, new HashSet<>(Arrays.asList(1, 2, 4, 5)));
        listaGrafo3.put(1, new HashSet<>(Arrays.asList(0, 2, 5)));
        listaGrafo3.put(2, new HashSet<>(Arrays.asList(0, 1, 3, 5)));
        listaGrafo3.put(3, new HashSet<>(Arrays.asList(2, 5, 6, 16)));
        listaGrafo3.put(4, new HashSet<>(Arrays.asList(0, 5)));
        listaGrafo3.put(5, new HashSet<>(Arrays.asList(0, 1, 2, 4, 6)));
        listaGrafo3.put(6, new HashSet<>(Arrays.asList(3, 5, 9)));
        listaGrafo3.put(7, new HashSet<>(Arrays.asList(8, 11, 12)));
        listaGrafo3.put(8, new HashSet<>(Arrays.asList(7, 9, 10)));
        listaGrafo3.put(9, new HashSet<>(Arrays.asList(6, 8, 10)));
        listaGrafo3.put(10, new HashSet<>(Arrays.asList(8, 9, 11)));
        listaGrafo3.put(11, new HashSet<>(Arrays.asList(7, 10, 12)));
        listaGrafo3.put(12, new HashSet<>(Arrays.asList(7, 11, 14)));
        listaGrafo3.put(13, new HashSet<>(Arrays.asList(14, 15, 16)));
        listaGrafo3.put(14, new HashSet<>(Arrays.asList(12, 13)));
        listaGrafo3.put(15, new HashSet<>(Arrays.asList(13, 16)));
        listaGrafo3.put(16, new HashSet<>(Arrays.asList(3, 13, 15)));

        listaDigrafo1.put(0, new HashSet<>(Arrays.asList(1)));
        listaDigrafo1.put(1, new HashSet<>(Arrays.asList(2)));
        listaDigrafo1.put(2, new HashSet<>(Arrays.asList(0, 3)));
        listaDigrafo1.put(3, new HashSet<>(Arrays.asList(4)));
        listaDigrafo1.put(4, new HashSet<>(Arrays.asList(5, 7)));
        listaDigrafo1.put(5, new HashSet<>(Arrays.asList(6)));
        listaDigrafo1.put(6, new HashSet<>(Arrays.asList(5, 8)));
        listaDigrafo1.put(7, new HashSet<>(Arrays.asList(3,9)));
        listaDigrafo1.put(8, new HashSet<>(Arrays.asList(7)));
        listaDigrafo1.put(9, new HashSet<>(Arrays.asList()));
        listaDigrafo1.put(10, new HashSet<>(Arrays.asList(11)));
        listaDigrafo1.put(11, new HashSet<>(Arrays.asList(12)));
        listaDigrafo1.put(12, new HashSet<>(Arrays.asList(11)));

        listaDigrafo2.put(0, new HashSet<>(Arrays.asList(1)));
        listaDigrafo2.put(1, new HashSet<>(Arrays.asList(2)));
        listaDigrafo2.put(2, new HashSet<>(Arrays.asList(0, 3)));
        listaDigrafo2.put(3, new HashSet<>(Arrays.asList(4)));
        listaDigrafo2.put(4, new HashSet<>(Arrays.asList(5, 7)));
        listaDigrafo2.put(5, new HashSet<>(Arrays.asList(6)));
        listaDigrafo2.put(6, new HashSet<>(Arrays.asList(5, 8)));
        listaDigrafo2.put(7, new HashSet<>(Arrays.asList(3,9)));
        listaDigrafo2.put(8, new HashSet<>(Arrays.asList(7)));
        listaDigrafo2.put(9, new HashSet<>(Arrays.asList(11)));
        listaDigrafo2.put(10, new HashSet<>(Arrays.asList(11)));
        listaDigrafo2.put(11, new HashSet<>(Arrays.asList(12)));
        listaDigrafo2.put(12, new HashSet<>(Arrays.asList(11)));

        listaDigrafo3.put(0, new HashSet<>(Arrays.asList(1, 4, 5)));     // a -> b, e, f
        listaDigrafo3.put(1, new HashSet<>(Arrays.asList(2)));           // b -> c
        listaDigrafo3.put(2, new HashSet<>(Arrays.asList(0)));           // c -> a
        listaDigrafo3.put(3, new HashSet<>(Arrays.asList(2)));           // d -> c
        listaDigrafo3.put(4, new HashSet<>(Arrays.asList(5)));           // e -> f
        listaDigrafo3.put(5, new HashSet<>(Arrays.asList(4, 6)));        // f -> e, g
        listaDigrafo3.put(6, new HashSet<>(Arrays.asList(3)));           // g -> d
        listaDigrafo3.put(7, new HashSet<>(Arrays.asList(8, 11)));       // h -> i, l
        listaDigrafo3.put(8, new HashSet<>(Arrays.asList(9)));           // i -> j
        listaDigrafo3.put(9, new HashSet<>(Arrays.asList(6, 10)));       // j -> g, k
        listaDigrafo3.put(10, new HashSet<>(Arrays.asList(8)));          // k -> i
        listaDigrafo3.put(11, new HashSet<>(Arrays.asList(10, 12)));     // l -> k, m
        listaDigrafo3.put(12, new HashSet<>(Arrays.asList(7)));          // m -> h
        listaDigrafo3.put(13, new HashSet<>(Arrays.asList(14, 15, 16))); // n -> o, p, q
        listaDigrafo3.put(14, new HashSet<>(Arrays.asList(12)));         // o -> m
        listaDigrafo3.put(15, new HashSet<>(Arrays.asList(16)));         // p -> q
        listaDigrafo3.put(16, new HashSet<>(Arrays.asList(13)));         // q -> n

        arvoreExemploSala.add(Arrays.asList(0, 0, 1, 0, 0, 0, 0, 0, 0, 0)); // Vértice 0
        arvoreExemploSala.add(Arrays.asList(0, 0, 1, 0, 0, 0, 0, 0, 0, 0)); // Vértice 1
        arvoreExemploSala.add(Arrays.asList(1, 1, 0, 1, 0, 1, 0, 0, 0, 0)); // Vértice 2
        arvoreExemploSala.add(Arrays.asList(0, 0, 1, 0, 0, 0, 0, 0, 0, 0)); // Vértice 3
        arvoreExemploSala.add(Arrays.asList(0, 0, 0, 0, 0, 1, 0, 0, 0, 0)); // Vértice 4
        arvoreExemploSala.add(Arrays.asList(0, 0, 1, 0, 1, 0, 1, 0, 1, 0)); // Vértice 5
        arvoreExemploSala.add(Arrays.asList(0, 0, 0, 0, 0, 1, 0, 0, 0, 0)); // Vértice 6
        arvoreExemploSala.add(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 1, 0)); // Vértice 7
        arvoreExemploSala.add(Arrays.asList(0, 0, 0, 0, 0, 1, 0, 1, 0, 1)); // Vértice 8
        arvoreExemploSala.add(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 1, 0)); // Vértice 9
    }


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

    public static void imprimirCabecalho(String texto) {
        String borda = "=".repeat(50);
        System.out.println("\n" + ANSI_AZUL + borda);
        System.out.println(" ".repeat((50 - texto.length()) / 2) + texto);
        System.out.println(borda + ANSI_RESET + "\n");
    }

    public static ListaAdjacencia obterCopiaListaGrafo1() {
        Map<Integer, Set<Integer>> copia = new HashMap<>();
        for (Map.Entry<Integer, Set<Integer>> entry : listaGrafo1.entrySet()) {
            copia.put(entry.getKey(), new HashSet<>(entry.getValue()));
        }
        return new ListaAdjacencia(copia, false);
    }
    public static ListaAdjacencia obterCopiaListaGrafo2() {
        Map<Integer, Set<Integer>> copia = new HashMap<>();
        for (Map.Entry<Integer, Set<Integer>> entry : listaGrafo2.entrySet()) {
            copia.put(entry.getKey(), new HashSet<>(entry.getValue()));
        }
        return new ListaAdjacencia(copia, false);
    }
    public static ListaAdjacencia obterCopiaListaGrafo3() {
        Map<Integer, Set<Integer>> copia = new HashMap<>();
        for (Map.Entry<Integer, Set<Integer>> entry : listaGrafo3.entrySet()) {
            copia.put(entry.getKey(), new HashSet<>(entry.getValue()));
        }
        return new ListaAdjacencia(copia, false);
    }
    public static ListaAdjacencia obterCopiaListaDigrafo1() {
        Map<Integer, Set<Integer>> copia = new HashMap<>();
        for (Map.Entry<Integer, Set<Integer>> entry : listaDigrafo1.entrySet()) {
            copia.put(entry.getKey(), new HashSet<>(entry.getValue()));
        }
        return new ListaAdjacencia(copia, true);
    }
    public static ListaAdjacencia obterCopiaListaDigrafo2() {
        Map<Integer, Set<Integer>> copia = new HashMap<>();
        for (Map.Entry<Integer, Set<Integer>> entry : listaDigrafo2.entrySet()) {
            copia.put(entry.getKey(), new HashSet<>(entry.getValue()));
        }
        return new ListaAdjacencia(copia, true);
    }
    public static ListaAdjacencia obterCopiaListaDigrafo3() {
        Map<Integer, Set<Integer>> copia = new HashMap<>();
        for (Map.Entry<Integer, Set<Integer>> entry : listaDigrafo3.entrySet()) {
            copia.put(entry.getKey(), new HashSet<>(entry.getValue()));
        }
        return new ListaAdjacencia(copia, true);
    }

    public static void imprimirSecao(String nomeSecao) {
        System.out.println("\n" + ANSI_VERDE + ">>> " + nomeSecao);
        System.out.println("-".repeat(50) + ANSI_RESET);
    }

    public static void imprimirSubcabecalho(String texto) {
        System.out.println("\n" + ANSI_AMARELO + "--- " + texto + " ---" + ANSI_RESET);
    }

    public static void imprimirMetrica(String nome, int valor) {
        System.out.printf("%-25s: %d%n", nome, valor);
    }

    public static void imprimirResultadoPropriedade(String nomePropriedade, boolean resultado) {
        System.out.printf("%-25s: %s%n", nomePropriedade, resultado ? "Verdadeiro" : "Falso");
    }

    public static void imprimirTesteAdjacencia(int v1, int v2) {
        System.out.printf("Teste de adjacência entre vértices %d e %d:%n", v1, v2);
    }

    public static String formatarRepresentacaoGrafo(String grafo) {
        return "  " + grafo.replace("\n", "\n  ");
    }

    public static String formatarMatriz(String matriz) {
        return "  " + matriz.replace("\n", "\n  ");
    }
}
