package br.ufrn.imd.questoes;
import br.ufrn.imd.estruturasdedados.ListaAdjacencia;
import br.ufrn.imd.questoes.util.GrafoUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Hierholzer {

    // Representação do grafo como uma lista de adjacência
    static ListaAdjacencia graph = new ListaAdjacencia(GrafoUtils.listaEuler2);

    // Remove uma aresta do grafo
    public static void removeEdge(int u, int v) {
        graph.removerAresta(u, v);
        graph.removerAresta(v, u);
    }

    // Encontra um ciclo euleriano usando o algoritmo de Hierholzer
    public static List<Integer> hierholzer(int start) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> eulerCycle = new ArrayList<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            int current = stack.peek();

            // Se o vértice atual ainda tiver arestas
            if (!graph.obterAdjacentes(current).isEmpty()) {
                int neighbor = graph.obterPrimeiroVizinho(current); // Pega o primeiro vizinho
                stack.push(neighbor);
                removeEdge(current, neighbor); // Remove a aresta
            } else {
                // Adiciona o vértice ao ciclo euleriano e volta para o anterior
                eulerCycle.add(stack.pop());
            }
        }

        return eulerCycle;
    }

    public static void main(String[] args) {
        // Verificar se o grafo tem um ciclo euleriano
        int start = 0; // Começa com um vértice arbitrário
        boolean hasEulerianCycle = true;

        // Checar graus pares para todos os vértices
        for (int vertex : graph.obterVertices()) {
            if (graph.obterAdjacentes(vertex).size() % 2 != 0) {
                hasEulerianCycle = false;
                break;
            }
        }

        if (hasEulerianCycle) {
            List<Integer> eulerCycle = hierholzer(start);
            System.out.println("Ciclo Euleriano: " + eulerCycle);
        } else {
            System.out.println("O grafo não possui um ciclo euleriano.");
        }
    }

}
