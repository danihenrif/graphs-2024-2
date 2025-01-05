package br.ufrn.imd.estruturasdedados;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ListaAdjacencia {
    private final Map<Integer, Set<Integer>> lista;
    private boolean direcionado;

    public ListaAdjacencia() {
        this.lista = new HashMap<>();
        this.direcionado = false;
    }

    public ListaAdjacencia(Map<Integer, Set<Integer>> listaAdjacencia, Boolean direcionado) {
        this.lista = listaAdjacencia;
        this.direcionado = direcionado;
    }

    public ListaAdjacencia(Map<Integer, Set<Integer>> copiaLista) {
        this.lista = copiaLista;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Integer, Set<Integer>> entrada : lista.entrySet()) {
            sb.append(entrada.getKey()).append(": ");

            Set<Integer> adjacentes = entrada.getValue();
            if (adjacentes.isEmpty()) {
                sb.append("Nenhum vizinho");
            } else {
                for (Integer a : adjacentes) {
                    sb.append(a).append(" ");
                }
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    public boolean isDirecionado() {
        return direcionado;
    }

    public void setDirecionado(boolean direcionado) {
        this.direcionado = direcionado;
    }

    public Map<Integer, Set<Integer>> getLista() {
        return lista;
    }

    public String adicionarVertice(Integer vertice) {
        if (lista.containsKey(vertice)) {
            return "Vértice " + vertice + " já existe!";
        }
        lista.put(vertice, new HashSet<>());
        return "Vértice " + vertice + " adicionado com sucesso!";
    }

    public String removerVertice(Integer vertice) {
        if (!lista.containsKey(vertice)) {
            return "Vértice não encontrado!";
        }
        lista.remove(vertice);
        for (Set<Integer> adjacentes : lista.values()) {
            adjacentes.remove(vertice);
        }
        return "Vértice " + vertice + " removido com sucesso!";
    }

    // Adiciona uma aresta entre os vértices u e v
    public void adicionarAresta(int u, int v) {
        lista.putIfAbsent(u, new HashSet<>());
        lista.putIfAbsent(v, new HashSet<>());

        lista.get(u).add(v);
        lista.get(v).add(u); // Grafo não direcionado
    }

    // Remove a aresta entre os vértices u e v
    public void removerAresta(int u, int v) {
        // Verifica se o vértice u existe e se a aresta com v está presente
        if (lista.containsKey(u)) {
            lista.get(u).remove(v);
        }

        // Verifica se o vértice v existe e se a aresta com u está presente
        if (lista.containsKey(v)) {
            lista.get(v).remove(u);
        }
    }

    // Obtém todos os vértices do grafo
    public Set<Integer> obterVertices() {
        return lista.keySet();  // Retorna o conjunto de chaves (vértices)
    }

    // Obtém a lista de adjacentes de um vértice
    public Set<Integer> obterAdjacentes(int u) {
        return lista.getOrDefault(u, new HashSet<>());
    }

    // Obtém o primeiro vizinho de um vértice
    public int obterPrimeiroVizinho(int u) {
        Set<Integer> vizinhos = lista.getOrDefault(u, new HashSet<>());
        Iterator<Integer> iterator = vizinhos.iterator();
        if (iterator.hasNext()) {
            return iterator.next();  // Retorna o primeiro vizinho
        }
        return -1;  // Caso não haja vizinhos
    }

    public void buscaEmLargura(int vertice) {
        Map<Integer, Integer> num = new HashMap<>();
        Map<Integer, Integer> pa = new HashMap<>();

        for (Integer v : lista.keySet()) {
            num.put(v, -1);
            pa.put(v, -1);
        }

        Queue<Integer> fila = new LinkedList<>();
        int cnt = 0;

        num.put(vertice, cnt++);
        pa.put(vertice, vertice);
        fila.add(vertice);

        while (!fila.isEmpty()) {
            int v = fila.poll();

            for (Integer w : lista.get(v)) {
                if (num.get(w) == -1) {
                    num.put(w, cnt++);
                    pa.put(w, v);
                    fila.add(w);
                }
            }
        }

        System.out.println("Ordem de visitação (num):");
        for (Map.Entry<Integer, Integer> entrada : num.entrySet()) {
            System.out.println("Vértice " + entrada.getKey() + ": " + entrada.getValue());
        }

        System.out.println("\nPredecessores (pa):");
        for (Map.Entry<Integer, Integer> entrada : pa.entrySet()) {
            System.out.println("Vértice " + entrada.getKey() + ": " + entrada.getValue());
        }
    }

    public void buscaEmProfundidade(int vertice) {
        Set<Integer> visitados = new HashSet<>();
        Stack<Integer> pilha = new Stack<>();

        dfs(vertice, visitados, pilha);
    }

    private void dfs(int vertice, Set<Integer> visitados, Stack<Integer> pilha) {
        visitados.add(vertice);
        pilha.push(vertice);

        for (int w : lista.get(vertice)) {
            if (!visitados.contains(w)) {
                System.out.println("Visitando aresta (" + vertice + ", " + w + ")");
                dfs(w, visitados, pilha);
            } else if (pilha.contains(w) && !((pilha.indexOf(vertice) - pilha.indexOf(w)) == 1)) {
                System.out.println("Visitando aresta (" + vertice + ", " + w + ")");
            }
        }

        pilha.pop();
    }

    public void buscaEmProfundidadeComPredecessor(int vertice) {
        Set<Integer> visitados = new HashSet<>();
        Map<Integer, Integer> predecessor = new HashMap<>();

        predecessor.put(vertice, null);

        dfsComPredecessor(vertice, visitados, predecessor);

        System.out.println("\nPredecessores:");
        for (Map.Entry<Integer, Integer> entrada : predecessor.entrySet()) {
            System.out.println("Vértice: " + entrada.getKey() + ", Predecessor: " + entrada.getValue());
        }
    }

    private void dfsComPredecessor(int vertice, Set<Integer> visitados, Map<Integer, Integer> predecessor) {
        visitados.add(vertice);

        for (int w : lista.get(vertice)) {
            if (!visitados.contains(w)) {
                predecessor.put(w, vertice);
                System.out.println("Visitando aresta (" + vertice + ", " + w + ")");
                dfsComPredecessor(w, visitados, predecessor);
            }
        }
    }

    public ListaAdjacencia gerarListaAdjacenciaAleatoria(int tamanho, boolean direcionado) {
        Random random = new Random();
        this.direcionado = direcionado;

        for (int i = 0; i < tamanho; i++) {
            lista.putIfAbsent(i, new HashSet<>());
        }

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (i != j && random.nextInt(2) == 1) {
                    lista.get(i).add(j);

                    if (!direcionado) {
                        lista.get(j).add(i);
                    }
                }
            }
        }

        System.out.println("Lista de adjacência gerada: ");
        System.out.println(this);
        return this;
    }

    public MatrizAdjacencia converterParaMatrizAdjacencia() {
        MatrizAdjacencia matrizAdjacencia = new MatrizAdjacencia(numeroDeVertices());
        for (int i = 0; i < numeroDeVertices(); i++) {
            for (Integer adj : lista.get(i)) {
                matrizAdjacencia.getMatriz().get(i).set(adj, 1);
                if (!direcionado) {
                    matrizAdjacencia.getMatriz().get(adj).set(i, 1);
                }
            }
        }
        return matrizAdjacencia;
    }

    public MatrizIncidencia converterParaMatrizIncidencia() {
        Integer vertices = numeroDeVertices();
        Integer arcos = numeroDeArestasOuArcos();

        MatrizIncidencia matrizIncidencia = new MatrizIncidencia(arcos, vertices);
        int indiceArco = 0;

        for (int i = 0; i < numeroDeVertices(); i++) {
            for (Integer adj : lista.get(i)) {
                matrizIncidencia.getMatriz().get(indiceArco).set(i, -1);
                matrizIncidencia.getMatriz().get(indiceArco).set(adj, 1);
                indiceArco++;
            }
        }
        return matrizIncidencia;
    }

    public Integer numeroDeVertices() {
        return lista.size();
    }

    public Integer numeroDeArestasOuArcos() {
        int totalArestas = 0;

        for (Set<Integer> adjacentes : lista.values()) {
            totalArestas += adjacentes.size();
        }

        return direcionado ? totalArestas : totalArestas / 2;
    }

    public void calcularGrauVertices() {
        if (direcionado) {
            Map<Integer, Integer> grauEntrada = new HashMap<>();

            for (Integer vertice : lista.keySet()) {
                grauEntrada.put(vertice, 0);
            }

            for (Set<Integer> conjunto : lista.values()) {
                for (Integer vertice : conjunto) {
                    grauEntrada.put(vertice, grauEntrada.get(vertice) + 1);
                }
            }

            for (Map.Entry<Integer, Set<Integer>> aux : lista.entrySet()) {
                int vertice = aux.getKey();
                int grauSaida = aux.getValue().size();
                int grauEntradaVertice = grauEntrada.get(vertice);
                System.out.println("O vértice " + vertice + " tem grau de saída " + grauSaida + " e grau de entrada "
                        + grauEntradaVertice);
            }

        } else {
            for (Map.Entry<Integer, Set<Integer>> aux : lista.entrySet()) {
                int vertice = aux.getKey();
                int grau = aux.getValue().size();
                System.out.println("Vértice " + vertice + " tem grau " + grau);
            }
        }
    }

    public void verificarAdjacencia(int vertice1, int vertice2) {
        if (lista.get(vertice1).contains(vertice2) || lista.get(vertice2).contains(vertice1)) {
            System.out.println("Os vértices " + vertice1 + " e " + vertice2 + " são adjacentes.");
        } else {
            System.out.println("Os vértices " + vertice1 + " e " + vertice2 + " não adjacentes.");
        }
    }

    public boolean ehConexo() {
        if (lista.isEmpty()) {
            return true;
        }

        Set<Integer> visitados = new HashSet<>();
        Integer primeiroVertice = lista.keySet().iterator().next();
        Stack<Integer> pilha = new Stack<>();

        dfs(primeiroVertice, visitados, pilha);

        return visitados.size() == lista.size();
    }

    public boolean ehBipartido() {
        Map<Integer, Integer> cores = new HashMap<>();

        for (int vertice : lista.keySet()) {
            if (!cores.containsKey(vertice)) {
                if (!bfsColoracao(vertice, cores)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean bfsColoracao(int primeiroVertice, Map<Integer, Integer> cores) {
        Queue<Integer> fila = new LinkedList<>();
        fila.add(primeiroVertice);
        cores.put(primeiroVertice, 0);

        while (!fila.isEmpty()) {
            int vertice = fila.poll();
            int cor = cores.get(vertice);

            for (Integer adj : lista.get(vertice)) {
                if (!cores.containsKey(adj)) {
                    cores.put(adj, 1 - cor);
                    fila.add(adj);
                } else if (cores.get(adj) == cor) {
                    return false;
                }
            }
        }
        return true;
    }

    public int encontrarPontosArticulacao() {
        Map<Integer, Integer> dfsNum = new HashMap<>();
        Map<Integer, Integer> lowpt = new HashMap<>();
        Map<Integer, Integer> pai = new HashMap<>();
        Set<Integer> pontosArticulacao = new HashSet<>();
        int tempo = 0;

        for (Integer v : lista.keySet()) {
            dfsNum.put(v, -1);
            pai.put(v, -1);
        }

        for (Integer v : lista.keySet()) {
            if (dfsNum.get(v) == -1) {
                dfsArticulacao(v, dfsNum, lowpt, pai, pontosArticulacao, tempo);
            }
        }

        System.out.println("Pontos de Articulação: " + pontosArticulacao);
        return pontosArticulacao.size();
    }

    private void dfsArticulacao(int u, Map<Integer, Integer> dfsNum, Map<Integer, Integer> lowpt,
            Map<Integer, Integer> pai, Set<Integer> pontosArticulacao, int tempo) {

        dfsNum.put(u, tempo);
        lowpt.put(u, tempo);
        tempo++;
        int filhos = 0;

        for (int v : lista.get(u)) {
            if (dfsNum.get(v) == -1) {
                pai.put(v, u);
                filhos++;
                dfsArticulacao(v, dfsNum, lowpt, pai, pontosArticulacao, tempo);

                lowpt.put(u, Math.min(lowpt.get(u), lowpt.get(v)));

                if (pai.get(u) == -1 && filhos > 1) {
                    pontosArticulacao.add(u);
                }
                if (pai.get(u) != -1 && lowpt.get(v) >= dfsNum.get(u)) {
                    pontosArticulacao.add(u);
                }
            } else if (v != pai.get(u)) {
                lowpt.put(u, Math.min(lowpt.get(u), dfsNum.get(v)));
            }
        }
    }

    public ListaAdjacencia obterGrafoSubjacente() {
        if (!this.direcionado) {
            Map<Integer, Set<Integer>> copiaLista = new HashMap<>();

            for (Map.Entry<Integer, Set<Integer>> entrada : this.lista.entrySet()) {
                copiaLista.put(entrada.getKey(), new HashSet<>(entrada.getValue()));
            }

            ListaAdjacencia copia = new ListaAdjacencia(copiaLista);
            copia.setDirecionado(this.direcionado);
            return copia;
        }

        ListaAdjacencia subjacente = new ListaAdjacencia();
        subjacente.setDirecionado(false);

        for (Integer vertice : this.lista.keySet()) {
            subjacente.adicionarVertice(vertice);
        }

        for (Map.Entry<Integer, Set<Integer>> entrada : this.lista.entrySet()) {
            Integer v = entrada.getKey();
            for (Integer w : entrada.getValue()) {
                subjacente.getLista().get(v).add(w);
                subjacente.getLista().get(w).add(v);
            }
        }

        return subjacente;
    }

    public void buscaEmProfundidadeComDeterminação(int vertice) {
        Set<Integer> visitados = new HashSet<>();
        int[] tempoEntrada = new int[lista.size()];
        int[] tempoSaida = new int[lista.size()];
        AtomicInteger tempo = new AtomicInteger(0);

        dfsComDeterminacaoDeProfundidade(vertice, visitados, tempoEntrada, tempoSaida, tempo);

        for (int i = 0; i < lista.size(); i++) {
            System.out.println("Vértice " + i + ": Entrada = " + tempoEntrada[i] + ", Saída = " + tempoSaida[i]);
        }
    }

    private void dfsComDeterminacaoDeProfundidade(int vertice, Set<Integer> visitados, int[] tempoEntrada, int[] tempoSaida, AtomicInteger tempo) {
        visitados.add(vertice);
        tempoEntrada[vertice] = tempo.incrementAndGet();

        for (int w : lista.get(vertice)) {
            if (!visitados.contains(w)) {
                dfsComDeterminacaoDeProfundidade(w, visitados, tempoEntrada, tempoSaida, tempo);
            }
        }

        tempoSaida[vertice] = tempo.incrementAndGet();
    }

}
