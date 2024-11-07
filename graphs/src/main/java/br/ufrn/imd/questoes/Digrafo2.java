package br.ufrn.imd.questoes;

import br.ufrn.imd.Grafo;
import br.ufrn.imd.estruturasdedados.CicloDetector;
import br.ufrn.imd.estruturasdedados.ListaAdjacencia;
import br.ufrn.imd.util.GrafoUtils;
import br.ufrn.imd.util.GrafoUtils.*;


public class Digrafo2 {
    public static void main(String[] args) {
        ListaAdjacencia lista = GrafoUtils.obterCopiaListaDigrafo2();
        Grafo digrafo2 = new Grafo(lista);

        GrafoUtils.imprimirSecao("Estruturas de dados");

        GrafoUtils.imprimirSubcabecalho("Lista de adjacência do digrafo 2: ");
        System.out.println(digrafo2.getListaAdjacencia());

        GrafoUtils.imprimirSubcabecalho("Matriz de adjacência do digrafo 2: ");
        digrafo2.setMatrizAdjacencia(digrafo2.getListaAdjacencia().converterParaMatrizAdjacencia());
        System.out.println(GrafoUtils.formatarRepresentacaoGrafo(digrafo2.getMatrizAdjacencia().toString()));

        GrafoUtils.imprimirSubcabecalho("Matriz de Incidência - digrafo 2");
        System.out.println(digrafo2.getListaAdjacencia().converterParaMatrizIncidencia());
    }
}
