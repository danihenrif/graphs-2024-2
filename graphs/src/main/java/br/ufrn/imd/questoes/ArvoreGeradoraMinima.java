package br.ufrn.imd.questoes;

import br.ufrn.imd.algoritmos.Kruskal;
import br.ufrn.imd.algoritmos.Kruskal.Aresta;
import br.ufrn.imd.estruturasdedados.ListaAdjacencia;
import br.ufrn.imd.util.GrafoUtils;
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
        mst.forEach(aresta -> System.out.println(aresta.toString()));

        // Calcular e imprimir o peso total da árvore
        int pesoTotal = mst.stream().mapToInt(Aresta::getPeso).sum();

        GrafoUtils.imprimirMetrica("Peso total da árvore", pesoTotal);
    }
}
