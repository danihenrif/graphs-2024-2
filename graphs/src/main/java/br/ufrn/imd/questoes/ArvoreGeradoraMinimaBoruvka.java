package br.ufrn.imd.questoes;

import br.ufrn.imd.algoritmos.Boruvka;
import br.ufrn.imd.algoritmos.Boruvka.Aresta;
import br.ufrn.imd.estruturasdedados.ListaAdjacencia;
import br.ufrn.imd.util.GrafoUtils;

import java.util.List;

public class ArvoreGeradoraMinimaBoruvka {
    public static void main(String[] args) {
        GrafoUtils.imprimirCabecalho("ÁRVORE GERADORA MÍNIMA - BORUVKA");

        // Obter uma cópia do grafo de exemplo
        ListaAdjacencia grafo = GrafoUtils.obterCopiaListaGrafo1();
        GrafoUtils.imprimirSubcabecalho("Lista de Adjacência do Grafo:");
        System.out.println(grafo);

        // Executar o algoritmo de Boruvka
        List<Aresta> mst = Boruvka.executarBoruvka(grafo);

        // Imprimir as arestas da árvore geradora mínima
        GrafoUtils.imprimirSubcabecalho("Arestas da Árvore Geradora Mínima:");
        mst.forEach(System.out::println);

        // Calcular e imprimir o peso total da árvore
        int pesoTotal = mst.stream().mapToInt(Aresta::getPeso).sum();
        GrafoUtils.imprimirMetrica("Peso Total da Árvore Geradora Mínima", pesoTotal);
    }
}
