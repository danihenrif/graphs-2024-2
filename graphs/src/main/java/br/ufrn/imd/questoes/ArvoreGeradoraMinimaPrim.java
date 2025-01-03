package br.ufrn.imd.questoes;

import br.ufrn.imd.algoritmos.Prim;
import br.ufrn.imd.algoritmos.Prim.Aresta;
import br.ufrn.imd.estruturasdedados.ListaAdjacencia;
import br.ufrn.imd.util.GrafoUtils;

import java.util.List;

public class ArvoreGeradoraMinimaPrim {
    public static void main(String[] args) {
        GrafoUtils.imprimirCabecalho("ÁRVORE GERADORA MÍNIMA - PRIM");

        // Obter uma cópia do grafo de exemplo
        ListaAdjacencia grafo = GrafoUtils.obterCopiaListaGrafo1();
        GrafoUtils.imprimirSubcabecalho("Lista de Adjacência do Grafo:");
        System.out.println(grafo);

        // Executar o algoritmo de Prim
        List<Aresta> mst = Prim.executarPrim(grafo, 0); // Começando do vértice 0

        // Imprimir as arestas da árvore geradora mínima
        GrafoUtils.imprimirSubcabecalho("Arestas da Árvore Geradora Mínima:");
        mst.forEach(System.out::println);

        // Calcular e imprimir o peso total da árvore
        int pesoTotal = mst.stream().mapToInt(Aresta::getPeso).sum();
        GrafoUtils.imprimirMetrica("Peso Total da Árvore Geradora Mínima", pesoTotal);
    }
}
