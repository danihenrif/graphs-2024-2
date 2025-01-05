package br.ufrn.imd.questoes;

import br.ufrn.imd.algoritmos.Boruvka;
import br.ufrn.imd.algoritmos.Boruvka.Aresta;
import br.ufrn.imd.estruturasdedados.ListaAdjacencia;
import br.ufrn.imd.questoes.util.GrafoUtils;
import java.util.List;

public class ArvoreGeradoraMinimaBoruvka {
    public static void main(String[] args) {
        GrafoUtils.imprimirCabecalho("ÁRVORE GERADORA MÍNIMA - BORŮVKA");

        // Chamar a função de GrafoUtils para obter a lista de adjacência com pesos
        ListaAdjacencia grafo = GrafoUtils.converterMatrizParaListaEuler1();

        // Executar o algoritmo de Borůvka
        List<Aresta> mst = Boruvka.executarBoruvka(grafo);

        // Imprimir as arestas da árvore geradora mínima
        GrafoUtils.imprimirSubcabecalho("Arestas da Árvore Geradora Mínima:");
        mst.forEach(System.out::println);

        // Calcular e imprimir o peso total da árvore
        int pesoTotal = mst.stream().mapToInt(Aresta::getPeso).sum();
        GrafoUtils.imprimirMetrica("Peso Total da Árvore Geradora Mínima", pesoTotal);
    }
}
