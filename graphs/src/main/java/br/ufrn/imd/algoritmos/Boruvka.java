package br.ufrn.imd.algoritmos;

import br.ufrn.imd.estruturasdedados.ListaAdjacencia;
import java.util.*;

public class Boruvka {

    public static class Aresta {
        private final int origem;
        private final int destino;
        private final int peso;

        public Aresta(int origem, int destino, int peso) {
            this.origem = origem;
            this.destino = destino;
            this.peso = peso;
        }

        public int getOrigem() {
            return origem;
        }

        public int getDestino() {
            return destino;
        }

        public int getPeso() {
            return peso;
        }

        @Override
        public String toString() {
            return "Aresta{" + "origem=" + origem + ", destino=" + destino + ", peso=" + peso + '}';
        }
    }

    public static List<Aresta> executarBoruvka(ListaAdjacencia grafo) {
        int n = grafo.numeroDeVertices();
        List<Aresta> mst = new ArrayList<>();
        int[] comp = new int[n];
        for (int i = 0; i < n; i++) comp[i] = i;
        int restantes = n;
        while (restantes > 1) {
            Aresta[] menor = new Aresta[n];
            for (int u = 0; u < n; u++) {
                for (int v : grafo.obterAdjacentes(u)) {
                    int p = grafo.obterPeso(u, v);
                    if (comp[u] != comp[v]) {
                        if (menor[comp[u]] == null || p < menor[comp[u]].getPeso()) {
                            menor[comp[u]] = new Aresta(u, v, p);
                        }
                    }
                }
            }
            for (Aresta a : menor) {
                if (a != null) {
                    int cU = comp[a.getOrigem()];
                    int cV = comp[a.getDestino()];
                    if (cU != cV) {
                        mst.add(a);
                        for (int i = 0; i < n; i++) {
                            if (comp[i] == cV) comp[i] = cU;
                        }
                        restantes--;
                    }
                }
            }
        }
        return mst;
    }
    
}
