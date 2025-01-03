package br.ufrn.imd.questoes;

import br.ufrn.imd.algoritmos.ChuLiuEdmonds;
import br.ufrn.imd.algoritmos.ChuLiuEdmonds.Aresta;
import br.ufrn.imd.util.GrafoUtils;

import java.util.*;

public class ArvoreGeradoraChuLiu {
    public static void main(String[] args) {
        GrafoUtils.imprimirCabecalho("ÁRVORE GERADORA MÍNIMA - CHU-LIU/EDMONDS");

        int numVertices = 6;
        int raiz = 0;

        GrafoUtils.imprimirSubcabecalho("Lista de Arestas do Grafo:");
        List<Aresta> arestas = Arrays.asList(
            new Aresta(0, 1, 1),  // 1 -> 2
            new Aresta(0, 2, 10), // 1 -> 3
            new Aresta(0, 5, 5),  // 1 -> 6
            new Aresta(1, 2, 8),  // 2 -> 3
            new Aresta(1, 5, 2),  // 2 -> 6
            new Aresta(2, 4, 6),  // 3 -> 5
            new Aresta(3, 1, 7),  // 4 -> 2
            new Aresta(3, 2, 4),  // 4 -> 3
            new Aresta(4, 3, 3),  // 5 -> 4
            new Aresta(5, 3, 9),  // 6 -> 4
            new Aresta(5, 4, 11)  // 6 -> 5
        );

        arestas.forEach(System.out::println);

        List<Aresta> mst = ChuLiuEdmonds.executarChuLiuEdmonds(numVertices, raiz, arestas);

        GrafoUtils.imprimirSubcabecalho("Arestas da Arborescência Geradora Mínima:");
        mst.forEach(System.out::println);

        int pesoTotal = mst.stream().mapToInt(Aresta::getPeso).sum();
        GrafoUtils.imprimirMetrica("Peso Total da Arborescência Geradora Mínima", pesoTotal);
    }
}
