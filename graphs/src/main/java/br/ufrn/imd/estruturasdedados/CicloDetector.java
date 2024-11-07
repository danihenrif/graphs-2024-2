package br.ufrn.imd.estruturasdedados;

import java.util.*;

public class CicloDetector {
    private final ListaAdjacencia grafo;
    private Set<Integer> visitados;
    private Set<Integer> pilhaRecursao;
    private List<List<Integer>> ciclosEncontrados;
    private Map<Integer, Integer> predecessor;

    public CicloDetector(ListaAdjacencia grafo) {
        this.grafo = grafo;
        this.visitados = new HashSet<>();
        this.pilhaRecursao = new HashSet<>();
        this.ciclosEncontrados = new ArrayList<>();
        this.predecessor = new HashMap<>();
    }

    public List<List<Integer>> detectarCiclos() {
        visitados.clear();
        pilhaRecursao.clear();
        ciclosEncontrados.clear();
        predecessor.clear();

        // Executa DFS para cada vértice não visitado
        for (Integer vertice : grafo.getLista().keySet()) {
            if (!visitados.contains(vertice)) {
                dfsDetectarCiclo(vertice, new ArrayList<>());
            }
        }

        return ciclosEncontrados;
    }

    private void dfsDetectarCiclo(Integer vertice, List<Integer> caminhoAtual) {
        visitados.add(vertice);
        pilhaRecursao.add(vertice);
        caminhoAtual.add(vertice);

        // Explora os vizinhos do vértice atual
        for (Integer vizinho : grafo.getLista().get(vertice)) {
            predecessor.put(vizinho, vertice);

            if (!visitados.contains(vizinho)) {
                dfsDetectarCiclo(vizinho, new ArrayList<>(caminhoAtual));
            }
            // Se o vértice já está na pilha de recursão, encontramos um ciclo
            else if (pilhaRecursao.contains(vizinho)) {
                List<Integer> ciclo = extrairCiclo(caminhoAtual, vizinho);
                if (!cicloJaEncontrado(ciclo)) {
                    ciclosEncontrados.add(ciclo);
                }
            }
        }

        pilhaRecursao.remove(vertice);
    }

    private List<Integer> extrairCiclo(List<Integer> caminhoAtual, Integer verticeInicial) {
        List<Integer> ciclo = new ArrayList<>();
        int indiceInicio = caminhoAtual.lastIndexOf(verticeInicial);

        for (int i = indiceInicio; i < caminhoAtual.size(); i++) {
            ciclo.add(caminhoAtual.get(i));
        }

        return ciclo;
    }

    private boolean cicloJaEncontrado(List<Integer> novoCiclo) {
        for (List<Integer> cicloExistente : ciclosEncontrados) {
            if (representamMesmoCiclo(cicloExistente, novoCiclo)) {
                return true;
            }
        }
        return false;
    }

    private boolean representamMesmoCiclo(List<Integer> ciclo1, List<Integer> ciclo2) {
        if (ciclo1.size() != ciclo2.size()) {
            return false;
        }

        // Concatena o ciclo1 com ele mesmo para verificar rotações
        List<Integer> cicloEstendido = new ArrayList<>(ciclo1);
        cicloEstendido.addAll(ciclo1);

        // Verifica se ciclo2 é uma subsequência de cicloEstendido
        return Collections.indexOfSubList(cicloEstendido, ciclo2) != -1;
    }

    public void imprimirCiclos() {
        if (ciclosEncontrados.isEmpty()) {
            System.out.println("Nenhum ciclo encontrado no grafo.");
            return;
        }

        System.out.println("Ciclos encontrados:");
        for (int i = 0; i < ciclosEncontrados.size(); i++) {
            System.out.println("Ciclo " + (i + 1) + ": " + ciclosEncontrados.get(i));
        }
    }

    public boolean possuiCiclos() {
        return !ciclosEncontrados.isEmpty();
    }

    public int numeroDeCiclos() {
        return ciclosEncontrados.size();
    }
}