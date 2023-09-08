package com.gestordepublicacoesdocisuc;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que permite criar objetos de Capítulos de Livros.
 * É uma subclasse da classe Livro.
 * Implementa a interface Serializable.
 */
public class CapitulosDeLivro extends Livro implements Serializable {
    private String nomeCapitulo;
    private int paginaInicio;
    private int paginaFim;


    public CapitulosDeLivro() {}

    public CapitulosDeLivro(ArrayList<Investigador> autores, String titulo, String[] palavrasChave, int anoPublicacao,
                            String tipoPublicacao, int dimensaoAudiencia, String resumo, String editora, String ISBN,
                            String nomeCapitulo, int paginaInicio, int paginaFim) {
        super(autores, titulo, palavrasChave, anoPublicacao, tipoPublicacao, dimensaoAudiencia, resumo, editora, ISBN);
        this.nomeCapitulo = nomeCapitulo;
        this.paginaInicio = paginaInicio;
        this.paginaFim = paginaFim;
    }


    public String getNomeCapitulo() {
        return nomeCapitulo;
    }
    public void setNomeCapitulo(String nomeCapitulo) {
        this.nomeCapitulo = nomeCapitulo;
    }

    public int getPaginaInicio() {
        return paginaInicio;
    }
    public void setPaginaInicio(int paginaInicio) {
        this.paginaInicio = paginaInicio;
    }

    public int getPaginaFim() {
        return paginaFim;
    }
    public void setPaginaFim(int paginaFim) {
        this.paginaFim = paginaFim;
    }

    public String getFatorImpacto() {
        if (dimensaoAudiencia >= 10000)
            return "A";
        else if (dimensaoAudiencia < 5000)
            return "C";
        else
            return "B";
    }


    @Override
    public String toString() {
        return super.toString() +
                "  Nome do Capítulo : " + nomeCapitulo + "\n" +
                "  Página de Início : " + paginaInicio + "\n" +
                "  Página de Fim : " + paginaFim + "\n";
    }
}