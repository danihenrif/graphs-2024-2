package br.ufrn.imd.questoes;

import br.ufrn.imd.estruturasdedados.MatrizAdjacencia;
import br.ufrn.imd.util.GrafoUtils;

import java.util.List;

public class Pruffer {
    public static void main(String[] args) {
        GrafoUtils.imprimirSecao("Código de pruffer com o exemplo de sala (Questão 13) : ");

        MatrizAdjacencia matriz = new MatrizAdjacencia(GrafoUtils.arvoreExemploSala);
        System.out.println("Matriz de Adjacência da Árvore:");
        System.out.println(matriz.toString());

        List<Integer> prufferCode = matriz.gerarCodigoPruffer();
        System.out.println("Código de Prüffer:");
        System.out.println(prufferCode);
    }



}
