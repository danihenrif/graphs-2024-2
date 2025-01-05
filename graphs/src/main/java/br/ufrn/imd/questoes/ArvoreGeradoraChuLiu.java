package br.ufrn.imd.questoes;

import br.ufrn.imd.algoritmos.ChuLiuEdmonds;
import br.ufrn.imd.algoritmos.ChuLiuEdmonds.Aresta;
import br.ufrn.imd.questoes.util.GrafoUtils;
import java.util.ArrayList;
import java.util.List;

public class ArvoreGeradoraChuLiu {
    public static void main(String[] args) {
        GrafoUtils.imprimirCabecalho("ÁRVORE GERADORA MÍNIMA - CHU-LIU/EDMONDS (Matriz)");

        int[][] matriz = {
            {0, 1, 10, 0,  0,  5},
            {0, 0,  8, 0,  0,  2},
            {0, 0,  0, 0,  6,  0},
            {0, 7,  4, 0,  0,  0},
            {0, 0,  0, 3,  0,  0},
            {0, 0,  0, 9, 11,  0}
        };

        int numVertices = matriz.length;
        int raiz = 0;

        GrafoUtils.imprimirSubcabecalho("Matriz de Custos:");
        imprimirMatriz(matriz);

        List<Aresta> arestas = converterMatrizParaArestas(matriz);
        GrafoUtils.imprimirSubcabecalho("Lista de Arestas Derivadas:");
        arestas.forEach(System.out::println);

        List<Aresta> mst = ChuLiuEdmonds.executarChuLiuEdmonds(numVertices, raiz, arestas);

        GrafoUtils.imprimirSubcabecalho("Arestas da Arborescência Geradora Mínima:");
        mst.forEach(System.out::println);

        int pesoTotal = mst.stream().mapToInt(Aresta::getPeso).sum();
        GrafoUtils.imprimirMetrica("Peso Total da Arborescência Geradora Mínima", pesoTotal);
    }

    private static List<Aresta> converterMatrizParaArestas(int[][] matriz) {
        List<Aresta> lista = new ArrayList<>();
        int n = matriz.length;
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                int w = matriz[u][v];
                if (w > 0 && u != v) {
                    lista.add(new Aresta(u, v, w));
                }
            }
        }
        return lista;
    }

    private static void imprimirMatriz(int[][] matriz) {
        for (int[] linha : matriz) {
            for (int valor : linha) {
                System.out.printf("%4d", valor);
            }
            System.out.println();
        }
    }
}
