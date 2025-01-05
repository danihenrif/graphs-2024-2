package br.ufrn.imd.questoes;

import br.ufrn.imd.algoritmos.Prim;
import br.ufrn.imd.estruturasdedados.ListaAdjacencia;
import br.ufrn.imd.questoes.util.GrafoUtils;
import java.util.List;

public class ArvoreGeradoraMinimaPrim {
    public static void main(String[] args) {
        GrafoUtils.imprimirCabecalho("ÁRVORE GERADORA MÍNIMA - PRIM");

        // Chamar a função de GrafoUtils
        ListaAdjacencia grafo = GrafoUtils.converterMatrizParaListaEuler1();

        // Executar o algoritmo de Prim
        List<Prim.Aresta> mst = Prim.executarPrim(grafo, 0); // Começa do vértice 0

        // Imprimir as arestas da árvore geradora mínima
        GrafoUtils.imprimirSubcabecalho("Arestas da Árvore Geradora Mínima:");
        mst.forEach(System.out::println);

        // Calcular e imprimir o peso total da árvore
        int pesoTotal = mst.stream().mapToInt(Prim.Aresta::getPeso).sum();
        GrafoUtils.imprimirMetrica("Peso Total da Árvore Geradora Mínima", pesoTotal);
    }
}
