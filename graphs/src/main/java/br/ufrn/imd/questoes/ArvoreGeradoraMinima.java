package br.ufrn.imd.questoes;

import br.ufrn.imd.estruturasdedados.ListaAdjacencia;
import br.ufrn.imd.util.GrafoUtils;
import br.ufrn.imd.algoritmos.Kruskal;

import java.util.List;

public class ArvoreGeradoraMinima {
    public static void main(String[] args) {
        GrafoUtils.imprimirCabecalho("ÁRVORE GERADORA MÍNIMA - KRUSKAL");

        // Obter uma cópia do grafo de exemplo
        ListaAdjacencia grafo = GrafoUtils.obterCopiaListaGrafo1();

        // Executar o algoritmo de Kruskal
        List<Kruskal.Aresta> mst = Kruskal.executarKruskal(grafo, grafo.numeroDeVertices());

        // Imprimir o resultado
        GrafoUtils.imprimirSubcabecalho("Árvore Geradora Mínima (Arestas):");
        mst.forEach(System.out::println);

        // Calcular e imprimir o peso total da árvore
        int pesoTotal = mst.stream().mapToInt(aresta -> aresta.peso).sum();
        GrafoUtils.imprimirMetrica("Peso total da árvore", pesoTotal);
    }
}
