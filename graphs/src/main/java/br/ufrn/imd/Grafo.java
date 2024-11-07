package br.ufrn.imd;

import br.ufrn.imd.estruturasdedados.ListaAdjacencia;
import br.ufrn.imd.estruturasdedados.MatrizAdjacencia;

public class Grafo {
    private ListaAdjacencia listaAdjacencia;
    private MatrizAdjacencia matrizAdjacencia;

    public Grafo() {
        this.listaAdjacencia = new ListaAdjacencia();
        this.matrizAdjacencia = new MatrizAdjacencia();
    }

    public Grafo(ListaAdjacencia listaAdjacencia) {
        this.listaAdjacencia = listaAdjacencia;
        this.matrizAdjacencia = new MatrizAdjacencia();
    }

    public void setMatrizAdjacencia(MatrizAdjacencia matrizAdjacencia) {
        this.matrizAdjacencia = matrizAdjacencia;
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

    public ListaAdjacencia converterParaListaAdjacencia() {
        return matrizAdjacencia.converterParaListaAdjacencia();
    }
}
