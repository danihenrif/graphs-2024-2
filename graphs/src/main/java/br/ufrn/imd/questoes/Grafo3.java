package br.ufrn.imd.questoes;

import br.ufrn.imd.Grafo;
import br.ufrn.imd.questoes.util.GrafoUtils;

import static br.ufrn.imd.questoes.util.GrafoUtils.*;

public class Grafo3 {
    public static void main(String[] args) {
        Grafo grafo3 = new Grafo(GrafoUtils.obterCopiaListaGrafo3());

        imprimirCabecalho("ANÁLISE DE GRAFOS");
        imprimirMetrica("Número de pontos de articulação",
                grafo3.getListaAdjacencia().encontrarPontosArticulacao());


        imprimirSecao("Algoritmos de Busca - Grafo 1");
        imprimirSubcabecalho("Busca em Profundidade (DFS)");
        System.out.println("Iniciando do vértice 1:");
        grafo3.getListaAdjacencia().buscaEmProfundidade(1);

        imprimirSubcabecalho("Busca em Profundidade com Predecessor");
        System.out.println("Iniciando do vértice 1:");
        grafo3.getListaAdjacencia().buscaEmProfundidadeComPredecessor(1);

        imprimirSubcabecalho("Busca em Largura a partir de um vértice");
        System.out.println("Iniciando vértice 1:");
        grafo3.getListaAdjacencia().buscaEmLargura(1);
    }




}
