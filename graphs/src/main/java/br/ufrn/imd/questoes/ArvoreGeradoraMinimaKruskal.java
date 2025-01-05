package br.ufrn.imd.questoes;

import br.ufrn.imd.algoritmos.Kruskal;
import br.ufrn.imd.estruturasdedados.ListaAdjacencia;
import br.ufrn.imd.questoes.util.GrafoUtils;
import java.util.List;

public class ArvoreGeradoraMinimaKruskal {
    public static void main(String[] args) {
        GrafoUtils.imprimirCabecalho("ÁRVORE GERADORA MÍNIMA - KRUSKAL");

        // Chamar a função de GrafoUtils
        ListaAdjacencia grafo = GrafoUtils.converterMatrizParaListaEuler1();

        // Executar o algoritmo de Kruskal
        List<Kruskal.Aresta> mst = Kruskal.executarKruskal(grafo);

        // Imprimir as arestas da árvore geradora mínima
        GrafoUtils.imprimirSubcabecalho("Arestas da Árvore Geradora Mínima:");
        mst.forEach(System.out::println);

        // Calcular e imprimir o peso total da árvore
        int pesoTotal = mst.stream().mapToInt(Kruskal.Aresta::getPeso).sum();
        GrafoUtils.imprimirMetrica("Peso Total da Árvore Geradora Mínima", pesoTotal);
    }
}
