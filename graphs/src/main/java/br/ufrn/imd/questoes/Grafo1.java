package br.ufrn.imd.questoes;

import br.ufrn.imd.Grafo;
import br.ufrn.imd.estruturasdedados.ListaAdjacencia;
import br.ufrn.imd.estruturasdedados.MatrizAdjacencia;
import br.ufrn.imd.questoes.util.GrafoUtils;

import static br.ufrn.imd.questoes.util.GrafoUtils.*;
import static br.ufrn.imd.questoes.util.GrafoUtils.imprimirMetrica;

public class Grafo1 {
    public static void main(String[] args) {
        System.out.println();
        Grafo grafo1 = new Grafo(GrafoUtils.obterCopiaListaGrafo1());

        imprimirCabecalho("ANÁLISE DE GRAFOS");

        imprimirSecao("Informações - Grafo 1");
        imprimirMetrica("Número de vértices", grafo1.getListaAdjacencia().numeroDeVertices());
        imprimirMetrica("Número de arestas", grafo1.getListaAdjacencia().numeroDeArestasOuArcos());
        imprimirMetrica("Número de pontos de articulação",
                grafo1.getListaAdjacencia().encontrarPontosArticulacao());
        imprimirSubcabecalho("Grau dos Vértices");
        grafo1.getListaAdjacencia().calcularGrauVertices();
        imprimirSubcabecalho("Verificar adjacência entre vértices 2 e 3");
        grafo1.getListaAdjacencia().verificarAdjacencia(2,3);

        imprimirSubcabecalho("Conexidade e bipartição");
        imprimirResultadoPropriedade("Conexidade", grafo1.getListaAdjacencia().ehConexo());

        imprimirResultadoPropriedade("Bipartição", grafo1.getListaAdjacencia().ehBipartido());


        imprimirSecao("Manipulação do Grafo");
        imprimirSubcabecalho("Estado Inicial do Grafo");
        System.out.println(formatarRepresentacaoGrafo(grafo1.toString()));

        imprimirSubcabecalho("Após Remover Vértice 1");
        grafo1.getListaAdjacencia().removerVertice(1);
        System.out.println(formatarRepresentacaoGrafo(grafo1.toString()));

        imprimirSubcabecalho("Após Adicionar Vértice 4");
        System.out.println(grafo1.getListaAdjacencia().adicionarVertice(4));

        imprimirSubcabecalho("Após Adicionar Vértice 8");
        System.out.println(grafo1.getListaAdjacencia().adicionarVertice(8));
        System.out.println(formatarRepresentacaoGrafo(grafo1.toString()));

        imprimirSubcabecalho("Voltando para o estado inicial do grafo para as outras operações...");
        grafo1 = new Grafo(GrafoUtils.obterCopiaListaGrafo1());
        imprimirSubcabecalho("Estado Inicial do Grafo");
        System.out.println(formatarRepresentacaoGrafo(grafo1.toString()));


        imprimirSecao("Conversões de Representação");

        imprimirSubcabecalho("Conversão de lista de adjacência para matriz de adjacência (Questão 1)(Questão 4):");
        ListaAdjacencia listaAdjacenciaConverter = grafo1.getListaAdjacencia();
        System.out.println(formatarRepresentacaoGrafo(listaAdjacenciaConverter.toString()));
        System.out.println("\nMatriz de Adjacência resultante:");
        MatrizAdjacencia matrizAdjacencia = listaAdjacenciaConverter.converterParaMatrizAdjacencia();
        grafo1.setMatrizAdjacencia(matrizAdjacencia);
        System.out.println(formatarMatriz(matrizAdjacencia.toString()));

        imprimirSubcabecalho("Conversão de matriz de adjacência para lista de adjacência (Questão 2)(Questão 4):");
        System.out.println(formatarRepresentacaoGrafo(grafo1.getMatrizAdjacencia().toString()));
        ListaAdjacencia listaConvertida = grafo1.converterParaListaAdjacencia();
        System.out.println("\nLista de Adjacência resultante:");
        System.out.println(formatarRepresentacaoGrafo(listaConvertida.toString()));

        imprimirSecao("Algoritmos de Busca - Grafo 1");
        imprimirSubcabecalho("Busca em Profundidade (DFS)");
        System.out.println("Iniciando do vértice 1:");
        grafo1.getListaAdjacencia().buscaEmProfundidade(1);

        imprimirSubcabecalho("Busca em Profundidade com Predecessor");
        System.out.println("Iniciando do vértice 1:");
        grafo1.getListaAdjacencia().buscaEmProfundidadeComPredecessor(1);

        imprimirSubcabecalho("Busca em Largura a partir de um vértice");
        System.out.println("Iniciando vértice 1:");
        grafo1.getListaAdjacencia().buscaEmLargura(1);

    }

}
