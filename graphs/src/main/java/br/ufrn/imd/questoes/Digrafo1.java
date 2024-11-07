package br.ufrn.imd.questoes;

import br.ufrn.imd.Grafo;
import br.ufrn.imd.estruturasdedados.CicloDetector;
import br.ufrn.imd.estruturasdedados.ListaAdjacencia;
import br.ufrn.imd.util.GrafoUtils;
import br.ufrn.imd.util.GrafoUtils.*;


public class Digrafo1 {
    public static void main(String[] args) {
        ListaAdjacencia lista = GrafoUtils.obterCopiaListaDigrafo1();
        Grafo digrafo1 = new Grafo(lista);

        GrafoUtils.imprimirSecao("Estruturas de dados");

        GrafoUtils.imprimirSubcabecalho("Lista de adjacência do digrafo 1: ");
        System.out.println(digrafo1.getListaAdjacencia());

        GrafoUtils.imprimirSubcabecalho("Matriz de adjacência do digrafo 1: ");
        digrafo1.setMatrizAdjacencia(digrafo1.getListaAdjacencia().converterParaMatrizAdjacencia());
        System.out.println(GrafoUtils.formatarRepresentacaoGrafo(digrafo1.getMatrizAdjacencia().toString()));

        GrafoUtils.imprimirSubcabecalho("Matriz de Incidência - digrafo 1");
        System.out.println(digrafo1.getListaAdjacencia().converterParaMatrizIncidencia());

        GrafoUtils.imprimirSubcabecalho("Grafo subjacente - digrafo 1 :");
        System.out.println(GrafoUtils.formatarRepresentacaoGrafo(digrafo1.getMatrizAdjacencia().obterGrafoSubjacente().toString()));

        GrafoUtils.imprimirSubcabecalho("Estrela direta - digrafo 1 :");
        System.out.println(GrafoUtils.formatarRepresentacaoGrafo(digrafo1.getMatrizAdjacencia().converterParaEstrelaDireta().toString()));

        GrafoUtils.imprimirSubcabecalho("Estrela reversa - digrafo 1 :");
        System.out.println(GrafoUtils.formatarRepresentacaoGrafo(digrafo1.getMatrizAdjacencia().converterParaEstrelaReversa().toString()));

        GrafoUtils.imprimirSecao("Análise de Ciclos (Questão 23)");
        CicloDetector detector = new CicloDetector(digrafo1.getListaAdjacencia());
        detector.detectarCiclos();

        GrafoUtils.imprimirMetrica("Número de ciclos", detector.numeroDeCiclos());
        GrafoUtils.imprimirResultadoPropriedade("Possui ciclos", detector.possuiCiclos());

        GrafoUtils.imprimirSubcabecalho("Detalhes dos Ciclos");
        detector.imprimirCiclos();

    }
}
