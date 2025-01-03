package br.ufrn.imd.questoes;

import br.ufrn.imd.Grafo;
import br.ufrn.imd.estruturasdedados.ListaAdjacencia;
import br.ufrn.imd.estruturasdedados.MatrizAdjacencia;
import br.ufrn.imd.questoes.util.GrafoUtils;

import static br.ufrn.imd.questoes.util.GrafoUtils.*;
import static br.ufrn.imd.questoes.util.GrafoUtils.imprimirMetrica;

public class Grafo2 {
    public static void main(String[] args) {
        System.out.println();
        Grafo grafo2 = new Grafo(GrafoUtils.obterCopiaListaGrafo2());

        imprimirCabecalho("ANÁLISE DE GRAFOS");

        imprimirSecao("Informações - Grafo 2");
        imprimirMetrica("Número de vértices", grafo2.getListaAdjacencia().numeroDeVertices());
        imprimirMetrica("Número de arestas", grafo2.getListaAdjacencia().numeroDeArestasOuArcos());
        imprimirMetrica("Número de pontos de articulação",
                grafo2.getListaAdjacencia().encontrarPontosArticulacao());
        imprimirSubcabecalho("Grau dos Vértices");
        grafo2.getListaAdjacencia().calcularGrauVertices();
        imprimirSubcabecalho("Verificar adjacência entre vértices 2 e 3");
        grafo2.getListaAdjacencia().verificarAdjacencia(2,3);

        imprimirSubcabecalho("Conexidade e bipartição");
        imprimirResultadoPropriedade("Conexidade", grafo2.getListaAdjacencia().ehConexo());

        imprimirResultadoPropriedade("Bipartição", grafo2.getListaAdjacencia().ehBipartido());

        imprimirSecao("Conversões de Representação");

        imprimirSubcabecalho("Conversão de lista de adjacência para matriz de adjacência (Questão 1)(Questão 4):");
        ListaAdjacencia listaAdjacenciaConverter = grafo2.getListaAdjacencia();
        System.out.println(formatarRepresentacaoGrafo(listaAdjacenciaConverter.toString()));
        System.out.println("\nMatriz de Adjacência resultante:");
        MatrizAdjacencia matrizAdjacencia = listaAdjacenciaConverter.converterParaMatrizAdjacencia();
        grafo2.setMatrizAdjacencia(matrizAdjacencia);
        System.out.println(formatarMatriz(matrizAdjacencia.toString()));

        imprimirSubcabecalho("Conversão de matriz de adjacência para lista de adjacência (Questão 2)(Questão 4):");
        System.out.println("\nMatriz de Adjacência:");
        System.out.println(formatarRepresentacaoGrafo(grafo2.getMatrizAdjacencia().toString()));
        ListaAdjacencia listaConvertida = grafo2.converterParaListaAdjacencia();
        System.out.println("\nLista de Adjacência resultante:");
        System.out.println(formatarRepresentacaoGrafo(listaConvertida.toString()));


        imprimirSecao("Algoritmos de Busca - Grafo 2");
        imprimirSubcabecalho("Busca em Profundidade (DFS)");
        System.out.println("Iniciando do vértice 1:");
        grafo2.getListaAdjacencia().buscaEmProfundidade(1);

        imprimirSubcabecalho("Busca em Profundidade com Predecessor");
        System.out.println("Iniciando do vértice 1:");
        grafo2.getListaAdjacencia().buscaEmProfundidadeComPredecessor(1);

        imprimirSubcabecalho("Busca em Largura a partir de um vértice");
        System.out.println("Iniciando vértice 1:");
        grafo2.getListaAdjacencia().buscaEmLargura(1);
    }
    /*imprimirSubcabecalho("Matriz de Incidência (Grafo Original) (Questão 3)");
        System.out
                .println(formatarMatriz(grafo2.getListaAdjacencia().converterParaMatrizIncidencia().toString()));*/
    /*imprimirSubcabecalho("Grafo Subjacente (Lista de Adjacência)");
        ListaAdjacencia grafoSubjacenteLista = grafo2.getListaAdjacencia().obterGrafoSubjacente();
        System.out.println(formatarRepresentacaoGrafo(grafoSubjacenteLista.toString()));

        imprimirSubcabecalho("Grafo Subjacente (Matriz de Adjacência)");
        MatrizAdjacencia grafoSubjacenteMatriz = grafo2.getMatrizAdjacencia().obterGrafoSubjacente();
        System.out.println(formatarMatriz(grafoSubjacenteMatriz.toString()));*/
}
