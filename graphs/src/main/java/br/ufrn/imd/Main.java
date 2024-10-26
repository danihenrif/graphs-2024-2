package br.ufrn.imd;

import br.ufrn.imd.datastructures.*;
import br.ufrn.imd.util.CarregadorGrafo;

public class Main {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_AZUL = "\u001B[34m";
    private static final String ANSI_VERDE = "\u001B[32m";
    private static final String ANSI_AMARELO = "\u001B[33m";

    public static void main(String[] args) {

        System.out.println();
        Grafo grafoOriginal = new Grafo();
        CarregadorGrafo.carregar(grafoOriginal, "graphs/src/grafo2.txt");

        imprimirCabecalho("ANÁLISE DE GRAFOS");

        imprimirSecao("Informações Básicas");
        imprimirMetrica("Número de arcos", grafoOriginal.getListaAdjacencia().numeroDeArcos());
        imprimirMetrica("Número de vértices", grafoOriginal.getListaAdjacencia().numeroDeVertices());
        imprimirMetrica("Número de pontos de articulação",
                grafoOriginal.getListaAdjacencia().encontrarPontosArticulacao());

        Grafo grafoManipulacao = new Grafo();
        CarregadorGrafo.carregar(grafoManipulacao, "graphs/src/grafo2.txt");

        imprimirSecao("Manipulação do Grafo");
        imprimirSubcabecalho("Estado Inicial do Grafo");
        System.out.println(formatarRepresentacaoGrafo(grafoManipulacao.toString()));

        imprimirSubcabecalho("Após Remover Vértice 1");
        grafoManipulacao.getListaAdjacencia().removerVertice(1);
        System.out.println(formatarRepresentacaoGrafo(grafoManipulacao.toString()));

        imprimirSubcabecalho("Após Adicionar Vértice 4");
        grafoManipulacao.getListaAdjacencia().adicionarVertice(4);
        System.out.println(formatarRepresentacaoGrafo(grafoManipulacao.toString()));

        imprimirSecao("Conversões de Representação");

        ListaAdjacencia listaAdjacenciaConverter = new ListaAdjacencia().gerarListaAdjacenciaAleatoria(4, false);
        imprimirSubcabecalho("Lista de Adjacência (Grafo Aleatório 4x4)");
        System.out.println("Lista de Adjacência gerada:");
        System.out.println(formatarRepresentacaoGrafo(listaAdjacenciaConverter.toString()));
        System.out.println("\nMatriz de Adjacência resultante:");
        System.out.println(formatarMatriz(listaAdjacenciaConverter.converterParaMatrizAdjacencia().toString()));

        imprimirSubcabecalho("Matriz de Incidência (Grafo Original)");
        System.out
                .println(formatarMatriz(grafoOriginal.getListaAdjacencia().converterParaMatrizIncidencia().toString()));

        imprimirSubcabecalho("Grafo Subjacente (Lista de Adjacência)");
        ListaAdjacencia grafoSubjacenteLista = grafoOriginal.getListaAdjacencia().obterGrafoSubjacente();
        System.out.println(formatarRepresentacaoGrafo(grafoSubjacenteLista.toString()));

        imprimirSubcabecalho("Grafo Subjacente (Matriz de Adjacência)");
        MatrizAdjacencia grafoSubjacenteMatriz = grafoOriginal.getMatrizAdjacencia().obterGrafoSubjacente();
        System.out.println(formatarMatriz(grafoSubjacenteMatriz.toString()));

        imprimirSecao("Análise de Propriedades do Grafo");

        Grafo grafoPropriedade = new Grafo();
        CarregadorGrafo.carregar(grafoPropriedade, "graphs/src/grafo2.txt");

        imprimirResultadoPropriedade("Conexidade", grafoPropriedade.getListaAdjacencia().ehConexo());

        imprimirResultadoPropriedade("Bipartição", grafoPropriedade.getListaAdjacencia().ehBipartido());

        imprimirSecao("Análise de Vértices");

        imprimirSubcabecalho("Grau dos Vértices");
        grafoPropriedade.getListaAdjacencia().calcularGrauVertices();

        imprimirSubcabecalho("Testes de Adjacência");
        imprimirTesteAdjacencia(1, 2);
        System.out.println("Resultado:");
        grafoPropriedade.getListaAdjacencia().verificarAdjacencia(1, 2);

        imprimirTesteAdjacencia(1, 3);
        System.out.println("Resultado:");
        grafoPropriedade.getListaAdjacencia().verificarAdjacencia(1, 3);

        imprimirSecao("Algoritmos de Busca");

        Grafo grafoBusca = new Grafo();
        CarregadorGrafo.carregar(grafoBusca, "graphs/src/grafo2.txt");

        imprimirSubcabecalho("Busca em Profundidade (DFS)");
        System.out.println("Iniciando do vértice 1:");
        grafoBusca.getListaAdjacencia().buscaEmProfundidade(1);

        imprimirSubcabecalho("Busca em Profundidade com Predecessor");
        System.out.println("Iniciando do vértice 1:");
        grafoBusca.getListaAdjacencia().buscaEmProfundidadeComPredecessor(1);

        imprimirSubcabecalho("Busca em Largura a partir de um vértice");
        System.out.println("Iniciando vértice 1:");
        grafoBusca.getListaAdjacencia().buscaEmLargura(1);
    }

    private static void imprimirCabecalho(String texto) {
        String borda = "=".repeat(50);
        System.out.println("\n" + ANSI_AZUL + borda);
        System.out.println(" ".repeat((50 - texto.length()) / 2) + texto);
        System.out.println(borda + ANSI_RESET + "\n");
    }

    private static void imprimirSecao(String nomeSecao) {
        System.out.println("\n" + ANSI_VERDE + ">>> " + nomeSecao);
        System.out.println("-".repeat(50) + ANSI_RESET);
    }

    private static void imprimirSubcabecalho(String texto) {
        System.out.println("\n" + ANSI_AMARELO + "--- " + texto + " ---" + ANSI_RESET);
    }

    private static void imprimirMetrica(String nome, int valor) {
        System.out.printf("%-25s: %d%n", nome, valor);
    }

    private static void imprimirResultadoPropriedade(String nomePropriedade, boolean resultado) {
        System.out.printf("%-25s: %s%n", nomePropriedade, resultado ? "Verdadeiro" : "Falso");
    }

    private static void imprimirTesteAdjacencia(int v1, int v2) {
        System.out.printf("Teste de adjacência entre vértices %d e %d:%n", v1, v2);
    }

    private static String formatarRepresentacaoGrafo(String grafo) {
        return "  " + grafo.replace("\n", "\n  ");
    }

    private static String formatarMatriz(String matriz) {
        return "  " + matriz.replace("\n", "\n  ");
    }
}
