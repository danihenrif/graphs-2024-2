package br.ufrn.imd;

import br.ufrn.imd.datastructures.ListaAdjacencia;
import br.ufrn.imd.datastructures.MatrizAdjacencia;

public class Grafo {
    private final ListaAdjacencia listaAdjacencia;
    private final MatrizAdjacencia matrizAdjacencia;

    public Grafo() {
        this.listaAdjacencia = new ListaAdjacencia();
        this.matrizAdjacencia = new MatrizAdjacencia();
    }

    public ListaAdjacencia getListaAdjacencia() {
        return listaAdjacencia;
    }

    public MatrizAdjacencia getMatrizAdjacencia() {
        return matrizAdjacencia;
    }

    @Override
    public String toString() {
        return listaAdjacencia.toString();
    }

    public String adicionarVertice(Integer vertice) {
        return listaAdjacencia.adicionarVertice(vertice);
    }

    public String removerVertice(Integer vertice) {
        return listaAdjacencia.removerVertice(vertice);
    }
}
