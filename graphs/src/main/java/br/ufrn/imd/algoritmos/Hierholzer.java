package br.ufrn.imd.algoritmos;
import br.ufrn.imd.estruturasdedados.ListaAdjacencia;
import br.ufrn.imd.questoes.util.GrafoUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Hierholzer {

    // Representação do grafo como uma lista de adjacência
    static ListaAdjacencia graph = new ListaAdjacencia(GrafoUtils.listaEuler);

    // Remove uma aresta do grafo
    public static void removeAresta(int u, int v) {
        graph.removerAresta(u, v);
        graph.removerAresta(v, u);
    }

    // Encontra um ciclo euleriano usando o algoritmo de Hierholzer
    public static List<Integer> hierholzer(int inicio) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> ciclo = new ArrayList<>();

        stack.push(inicio);

        while (!stack.isEmpty()) {
            int current = stack.peek();

            // Se o vértice atual ainda tiver arestas
            if (!graph.obterAdjacentes(current).isEmpty()) {
                int vizinho = graph.obterPrimeiroVizinho(current); // Pega o primeiro vizinho
                stack.push(vizinho);
                removeAresta(current, vizinho); // Remove a aresta
            } else {
                // Adiciona o vértice ao ciclo euleriano e volta para o anterior
                ciclo.add(stack.pop());
            }
        }

        return ciclo;
    }

    public static void main(String[] args) {
        // Verificar se o grafo tem um ciclo euleriano
        int inicio = 0; // Começa com um vértice arbitrário
        boolean temCicloEuler = true;

        // Checar graus pares para todos os vértices
        for (int vertex : graph.obterVertices()) {
            if (graph.obterAdjacentes(vertex).size() % 2 != 0) {
                temCicloEuler = false;
                break;
            }
        }

        if (temCicloEuler) {
            List<Integer> ciclo = hierholzer(inicio);
            System.out.println("Ciclo Euleriano: " + ciclo);
        } else {
            System.out.println("O grafo não possui um ciclo euleriano.");
        }
    }

}
