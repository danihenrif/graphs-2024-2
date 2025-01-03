package br.ufrn.imd.questoes;

import br.ufrn.imd.Grafo;
import br.ufrn.imd.estruturasdedados.ListaAdjacencia;
import br.ufrn.imd.util.GrafoUtils;
import static br.ufrn.imd.util.GrafoUtils.formatarRepresentacaoGrafo;

public class Digrafo3 {
    public static void main(String[] args) {
        ListaAdjacencia lista = GrafoUtils.obterCopiaListaDigrafo3();
        Grafo digrafo3 = new Grafo(lista);
        GrafoUtils.imprimirSecao("Busca em profundidade com determinação de profundidade de entrada e de saída de cada vértice: ");
        ListaAdjacencia listaAdjacenciaConverter = digrafo3.getListaAdjacencia();
        System.out.println(formatarRepresentacaoGrafo(listaAdjacenciaConverter.toString()));

        GrafoUtils.imprimirSubcabecalho("Busca em profundidade com determinação de profundidade do digrafo 3: ");
        digrafo3.getListaAdjacencia().buscaEmProfundidadeComDeterminação(0);
    }
}
