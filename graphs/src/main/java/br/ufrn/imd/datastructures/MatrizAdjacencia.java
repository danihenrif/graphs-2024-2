package br.ufrn.imd.datastructures;

import br.ufrn.imd.util.GrafoUtils;

import java.util.*;

public class MatrizAdjacencia {
    private final List<List<Integer>> matriz;
    private boolean direcionado;

    public MatrizAdjacencia() {
        this.matriz = new ArrayList<>();
        this.direcionado = false;
    }

    public MatrizAdjacencia(List<List<Integer>> matriz) {
        this.matriz = matriz;
    }

    public MatrizAdjacencia(Integer tamanho) {
        this.matriz = new ArrayList<>();

        for (int i = 0; i < tamanho; i++) {
            List<Integer> linha = new ArrayList<>(Collections.nCopies(tamanho, 0));
            matriz.add(linha);
        }
    }

    public ListaAdjacencia converterParaListaAdjacencia() {
        GrafoUtils.validarMatrizAdjacencia(matriz);

        int numeroDeVertices = matriz.size();

        Map<Integer, Set<Integer>> listaAdjacencia = new HashMap<>();

        for (int i = 0; i < numeroDeVertices; i++) {
            listaAdjacencia.put(i, new HashSet<>());
        }

        for (int i = 0; i < numeroDeVertices; i++) {
            for (int j = 0; j < numeroDeVertices; j++) {
                if (matriz.get(i).get(j) == 1) {
                    listaAdjacencia.get(i).add(j);
                    if (!direcionado) {
                        listaAdjacencia.get(j).add(i);
                    }
                }
            }
        }

        return new ListaAdjacencia(listaAdjacencia);
    }

    public List<List<Integer>> getMatriz() {
        return matriz;
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();
        int tamanho = matriz.size();

        for (List<Integer> linha : matriz) {
            for (int j = 0; j < tamanho; j++) {
                resultado.append(linha.get(j)).append(" ");
            }
            resultado.append("\n");
        }

        return resultado.toString();
    }

    public void setDirecionado(boolean direcionado) {
        this.direcionado = direcionado;
    }

    public MatrizAdjacencia gerarMatrizAdjacenciaAleatoria(Integer tamanho) {
        Random random = new Random();

        for (int i = 0; i < tamanho; i++) {
            ArrayList<Integer> linha = new ArrayList<>();

            for (int j = 0; j < tamanho; j++) {
                if (i == j) {
                    linha.add(0);
                } else {
                    linha.add(random.nextInt(2));
                }
            }
            matriz.add(linha);
        }
        System.out.println("Matriz de adjacência gerada: ");
        System.out.println(this);
        return this;
    }

    public MatrizIncidencia converterParaMatrizIncidencia() {
        Integer[] numeroVerticesArestas = GrafoUtils.obterNumeroVerticesEArestas(matriz);
        Integer numeroDeVertices = numeroVerticesArestas[0];
        Integer numeroDeArestas = numeroVerticesArestas[1];

        MatrizIncidencia matrizIncidencia = new MatrizIncidencia(numeroDeVertices, numeroDeArestas);
        int indiceAresta = 0;

        for (int i = 0; i < numeroDeVertices; i++) {
            for (int j = 0; j < numeroDeVertices; j++) {
                if (indiceAresta < numeroDeArestas) {
                    if (matriz.get(i).get(j) == 1) {
                        matrizIncidencia.getMatriz().get(indiceAresta).set(i, -1);
                        matrizIncidencia.getMatriz().get(indiceAresta).set(j, 1);
                        indiceAresta++;
                    }
                }
            }
        }

        return matrizIncidencia;
    }

    public EstrelaDireta converterParaEstrelaDireta() {
        Integer[] numeroVerticesArestas = GrafoUtils.obterNumeroVerticesEArestas(matriz);
        Integer numeroDeVertices = numeroVerticesArestas[0];
        Integer numeroDeArcos = numeroVerticesArestas[1];

        EstrelaDireta estrelaDireta = new EstrelaDireta(numeroDeArcos, numeroDeVertices);

        int indiceArco = 0;
        Integer verticeOrigem = 1;

        out: for (int i = 0; i < numeroDeVertices; i++) {
            if (i > verticeOrigem) {
                verticeOrigem++;
            }
            for (int j = 0; j < numeroDeVertices; j++) {
                if (matriz.get(i).get(j) == 1) {
                    estrelaDireta.getArcos().get(indiceArco).add(i);
                    estrelaDireta.getArcos().get(indiceArco).add(j);

                    if (i == verticeOrigem) {
                        estrelaDireta.getPont()[verticeOrigem] = indiceArco;
                        verticeOrigem++;
                    }

                    indiceArco++;
                }
                if (indiceArco >= numeroDeArcos) {
                    break out;
                }
            }
        }

        for (int i = 1; i < estrelaDireta.getPont().length - 1; i++) {
            if (estrelaDireta.getPont()[i] == null) {
                estrelaDireta.getPont()[i] = encontrarProximoNaoNulo(estrelaDireta.getPont(), i + 1);
            }
        }

        return estrelaDireta;
    }

    public EstrelaReversa converterParaEstrelaReversa() {
        Integer[] numeroVerticesArestas = GrafoUtils.obterNumeroVerticesEArestas(matriz);
        Integer numeroDeVertices = numeroVerticesArestas[0];
        Integer numeroDeArcos = numeroVerticesArestas[1];

        EstrelaReversa estrelaReversa = new EstrelaReversa(numeroDeArcos, numeroDeVertices);

        int indiceArco = 0;
        Integer verticeDestino = 1;

        out: for (int i = 0; i < numeroDeVertices; i++) {
            if (i > verticeDestino) {
                verticeDestino++;
            }

            for (int j = 0; j < numeroDeVertices; j++) {
                if (matriz.get(j).get(i) == 1) {
                    estrelaReversa.getArcos().get(indiceArco).add(i);
                    estrelaReversa.getArcos().get(indiceArco).add(j);

                    if (i == verticeDestino) {
                        estrelaReversa.getPont()[verticeDestino] = indiceArco;
                        verticeDestino++;
                    }
                    indiceArco++;
                }
                if (indiceArco >= numeroDeArcos) {
                    break out;
                }
            }
        }

        for (int i = 1; i < estrelaReversa.getPont().length - 1; i++) {
            if (estrelaReversa.getPont()[i] == null) {
                estrelaReversa.getPont()[i] = encontrarProximoNaoNulo(estrelaReversa.getPont(), i + 1);
            }
        }

        return estrelaReversa;
    }

    private Integer encontrarProximoNaoNulo(Integer[] pont, int indice) {
        if (pont[indice] != null) {
            return pont[indice];
        }
        return encontrarProximoNaoNulo(pont, indice + 1);
    }

    public List<Integer> gerarCodigoPruffer() {
        int numeroDeVertices = matriz.size();
        List<Integer> codigoPruffer = new ArrayList<>();

        Integer[] grau = new Integer[numeroDeVertices];
        for (int i = 0; i < numeroDeVertices; i++) {
            grau[i] = 0;
            for (int j = 0; j < numeroDeVertices; j++) {
                if (matriz.get(i).get(j) == 1) {
                    grau[i]++;
                }
            }
        }

        for (int i = 0; i < numeroDeVertices - 2; i++) {
            int folha = -1;
            for (int j = 0; j < numeroDeVertices; j++) {
                if (grau[j] == 1) {
                    folha = j;
                    break;
                }
            }

            for (int j = 0; j < numeroDeVertices; j++) {
                if (matriz.get(folha).get(j) == 1 && grau[j] != 0) {
                    codigoPruffer.add(j);
                    grau[j]--;
                    break;
                }
            }

            grau[folha] = 0;
        }

        return codigoPruffer;
    }

    public void buscaEmProfundidade(int s) {
        int n = matriz.size();
        boolean[] visitados = new boolean[n];
        int[] predecessor = new int[n];

        Arrays.fill(predecessor, -1);

        Stack<Integer> pilha = new Stack<>();
        visitados[s] = true;

        pilha.push(s);

        while (!pilha.isEmpty()) {
            int u = pilha.peek();
            boolean encontrouNaoVisitado = false;

            for (int v = 0; v < n; v++) {
                if (matriz.get(u).get(v) != 0 && !visitados[v]) {
                    visitados[v] = true;
                    predecessor[v] = u;

                    pilha.push(v);

                    encontrouNaoVisitado = true;
                    break;
                }
            }

            if (!encontrouNaoVisitado) {
                pilha.pop();
            }
        }

        System.out.println("Predecessores:");

        for (int i = 0; i < n; i++) {
            System.out.println("Vértice " + i + " predecessor: " + predecessor[i]);
        }
    }
}