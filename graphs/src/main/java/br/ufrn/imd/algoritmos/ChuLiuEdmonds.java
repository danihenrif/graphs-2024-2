package br.ufrn.imd.algoritmos;

import java.util.ArrayList;
import java.util.List;

public class ChuLiuEdmonds {

    public static class Aresta {
        private final int origem;
        private final int destino;
        private final int peso;

        public Aresta(int origem, int destino, int peso) {
            this.origem = origem;
            this.destino = destino;
            this.peso = peso;
        }

        public int getOrigem()   { return origem; }
        public int getDestino()  { return destino; }
        public int getPeso()     { return peso; }

        @Override
        public String toString() {
            return String.format("Aresta(%d->%d, p=%d)", origem, destino, peso);
        }
    }

    public static List<Aresta> executarChuLiuEdmonds(int numVertices, int raiz, List<Aresta> arestasIn) {
        List<Aresta> arestas = new ArrayList<>(arestasIn);
        while (true) {
            int[] inEdge   = new int[numVertices];
            int[] inWeight = new int[numVertices];
            for (int i = 0; i < numVertices; i++) {
                inEdge[i]   = -1;
                inWeight[i] = Integer.MAX_VALUE;
            }
            inEdge[raiz]   = raiz;
            inWeight[raiz] = 0;

            for (Aresta e : arestas) {
                int u = e.getOrigem();
                int v = e.getDestino();
                int w = e.getPeso();
                if (v != raiz && w < inWeight[v] && u != v) {
                    inWeight[v] = w;
                    inEdge[v]   = u;
                }
            }

            boolean removeuAresta = false;
            int[] visitado = new int[numVertices];
            for (int i = 0; i < numVertices; i++) {
                visitado[i] = -1;
            }

            ciclo:
            for (int v = 0; v < numVertices; v++) {
                if (v == raiz) continue;
                int atual = v;
                List<Integer> caminho = new ArrayList<>();
                while (atual != raiz && atual != -1 && visitado[atual] == -1) {
                    caminho.add(atual);
                    visitado[atual] = v;
                    atual = inEdge[atual];
                    if (atual == -1) break;
                    if (visitado[atual] == v) {
                        List<Integer> ciclo = new ArrayList<>();
                        int x = atual;
                        while (true) {
                            ciclo.add(x);
                            x = inEdge[x];
                            if (x == atual) break;
                        }
                        Aresta maior = null;
                        for (int no : ciclo) {
                            int pai = inEdge[no];
                            int w = pesoAresta(pai, no, arestas);
                            if (maior == null || w > maior.getPeso()) {
                                maior = new Aresta(pai, no, w);
                            }
                        }
                        final Aresta maiorFinal = maior;
                        arestas.removeIf(a -> a.getOrigem() == maiorFinal.getOrigem()
                                           && a.getDestino() == maiorFinal.getDestino()
                                           && a.getPeso() == maiorFinal.getPeso());
                        removeuAresta = true;
                        break ciclo;
                    }
                }
            }

            if (!removeuAresta) {
                List<Aresta> mst = new ArrayList<>();
                for (int v = 0; v < numVertices; v++) {
                    if (v == raiz) continue;
                    if (inEdge[v] != -1) {
                        mst.add(new Aresta(inEdge[v], v, pesoAresta(inEdge[v], v, arestas)));
                    }
                }
                return mst;
            }
        }
    }

    private static int pesoAresta(int u, int v, List<Aresta> lista) {
        for (Aresta a : lista) {
            if (a.getOrigem() == u && a.getDestino() == v) {
                return a.getPeso();
            }
        }
        return Integer.MAX_VALUE;
    }
}
