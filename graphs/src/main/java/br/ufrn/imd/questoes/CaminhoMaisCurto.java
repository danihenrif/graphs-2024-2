package br.ufrn.imd.questoes;

import br.ufrn.imd.algoritmos.BellmanFord;
import br.ufrn.imd.algoritmos.Dijkstra;
import br.ufrn.imd.algoritmos.FloydWarshall;
import br.ufrn.imd.questoes.util.GrafoUtils;
import java.util.List;
import java.util.Map;

public class CaminhoMaisCurto {

    public static void main(String[] args) {
        Map<Integer, List<Map<Integer, Integer>>> digrafo = GrafoUtils.getListaDigrafoComPesos();

        GrafoUtils.imprimirSecao("Caminho Mais Curto");

        GrafoUtils.imprimirSubcabecalho("Algoritmo de Dijkstra: ");
        Dijkstra.CaminhoMaisCurto(digrafo, 3);

        GrafoUtils.imprimirSubcabecalho("Algoritmo de Bellman-Ford: ");
        BellmanFord.CaminhoMaisCurto(digrafo, 3);

        GrafoUtils.imprimirSubcabecalho("Algoritmo de Floyd-Warshall: ");
        FloydWarshall.CaminhoMaisCurto(digrafo, 3);
    }
}
