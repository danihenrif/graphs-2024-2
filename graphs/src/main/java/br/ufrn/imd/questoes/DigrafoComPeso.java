package br.ufrn.imd.questoes;

import br.ufrn.imd.estruturasdedados.CaminhoMaisCurto;
import br.ufrn.imd.questoes.util.GrafoUtils;

import java.util.List;
import java.util.Map;

public class DigrafoComPeso {

    public static void main(String[] args) {
        Map<Integer, List<Map<Integer, Integer>>> digrafo = GrafoUtils.getListaDigrafoComPesos();

        GrafoUtils.imprimirSecao("Caminho Mais Curto");

        GrafoUtils.imprimirSubcabecalho("Algoritmo de Dijkstra: ");
        CaminhoMaisCurto.Dijkstra(digrafo, 3);

        GrafoUtils.imprimirSubcabecalho("Algoritmo de Floyd-Warshall: ");
        CaminhoMaisCurto.FloydWarshall(digrafo, 14);
    }
}
