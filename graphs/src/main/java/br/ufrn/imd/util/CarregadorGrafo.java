package br.ufrn.imd.util;

import br.ufrn.imd.Grafo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class CarregadorGrafo {

    public static String carregar(Grafo g, String nomeArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            g.getListaAdjacencia().setDirecionado(lerTipoGrafo(br));
            System.out.println("Grafo direcionado: " + g.getListaAdjacencia().isDirecionado());

            lerVertices(g, br);

            lerArestas(g, br);

            return "Grafo carregado com sucesso!\n";
        } catch (IOException e) {
            e.printStackTrace();
            return "Falha ao carregar o grafo: " + e.getMessage();
        } catch (NumberFormatException e) {
            return "Erro ao converter um vértice para Integer: " + e.getMessage();
        }
    }

    private static boolean lerTipoGrafo(BufferedReader br) throws IOException {
        String linha = br.readLine();
        return linha.trim().equalsIgnoreCase("d");
    }

    private static void lerVertices(Grafo g, BufferedReader br) throws IOException {
        String linha = br.readLine();
        if (linha != null) {
            System.out.println("Vértices lidos: " + linha);
            String[] vertices = linha.split(",");

            for (String vertice : vertices) {
                int idVertice = Integer.parseInt(vertice.trim());
                g.getListaAdjacencia().getLista().put(idVertice, new HashSet<>());
            }
        }
    }

    private static void lerArestas(Grafo g, BufferedReader br) throws IOException {
        String linha = br.readLine();
        if (linha != null) {
            System.out.println("Arestas lidas: " + linha);
            String[] arestas = linha.split(" ");

            for (String aresta : arestas) {
                adicionarAresta(g, aresta.trim());
            }
        }
    }

    private static void adicionarAresta(Grafo g, String aresta) {
        if (aresta.startsWith("(") && aresta.endsWith(")")) {
            String[] extremidades = aresta.substring(1, aresta.length() - 1).split(",");
            if (extremidades.length == 2) {
                try {
                    int de = Integer.parseInt(extremidades[0].trim());
                    int para = Integer.parseInt(extremidades[1].trim());

                    if (g.getListaAdjacencia().getLista().containsKey(de)
                            && g.getListaAdjacencia().getLista().containsKey(para)) {
                        g.getListaAdjacencia().getLista().get(de).add(para);
                        if (!g.getListaAdjacencia().isDirecionado()) {
                            g.getListaAdjacencia().getLista().get(para).add(de);
                        }
                    } else {
                        System.err.println("Um dos vértices não existe: " + de + " ou " + para);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao converter vértice para Integer: " + e.getMessage());
                }
            }
        } else {
            System.err.println("Formato de aresta inválido: " + aresta);
        }
    }
}
