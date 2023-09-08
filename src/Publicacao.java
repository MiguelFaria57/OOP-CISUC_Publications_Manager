package com.gestordepublicacoesdocisuc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe abstrata de objetos de Publicações.
 * Implementa a interface Serializable.
 */
public abstract class Publicacao implements Serializable {
    protected ArrayList<Investigador> autores;
    protected String titulo;
    protected String[] palavrasChave;
    protected int anoPublicacao;
    protected String tipoPublicacao;
    protected int dimensaoAudiencia;
    protected String resumo;


    public Publicacao() {}

    public Publicacao(ArrayList<Investigador> autores, String titulo, String[] palavrasChave, int anoPublicacao, String tipoPublicacao, int dimensaoAudiencia, String resumo) {
        this.autores = autores;
        this.titulo = titulo;
        this.palavrasChave = palavrasChave;
        this.anoPublicacao = anoPublicacao;
        this.tipoPublicacao = tipoPublicacao;
        this.dimensaoAudiencia = dimensaoAudiencia;
        this.resumo = resumo;
    }


    public ArrayList<Investigador> getAutores() {
        return autores;
    }
    public void setAutores(ArrayList<Investigador> autores) {
        this.autores = autores;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String[] getPalavrasChave() {
        return palavrasChave;
    }
    public void setPalavrasChave(String[] palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }
    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getTipoPublicacao() {
        return tipoPublicacao;
    }
    public void setTipoPublicacao(String tipoPublicacao) {
        this.tipoPublicacao = tipoPublicacao;
    }

    public int getDimensaoAudiencia() {
        return dimensaoAudiencia;
    }
    public void setDimensaoAudiencia(int dimensaoAudiencia) {
        this.dimensaoAudiencia = dimensaoAudiencia;
    }

    public String getResumo() {
        return resumo;
    }
    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public ArrayList<String> getNomesAutores() {
        ArrayList<String> nomesAutores = new ArrayList<>();
        for (Investigador i: autores)
            nomesAutores.add(i.getNome());
        return nomesAutores;
    }
    public abstract String getFatorImpacto();


    public String toString() {
        return tipoPublicacao + "\n" +
                "  Autores : " + getNomesAutores() + "\n" +
                "  Título : " + titulo + "\n" +
                "  Palavras-Chave : " + Arrays.toString(palavrasChave) + "\n" +
                "  Ano da Publicação : " + anoPublicacao + "\n" +
                "  Tipo de Publicação : " + tipoPublicacao + "\n" +
                "  Dimensao da Audiência : " + dimensaoAudiencia + "\n" +
                "  Resumo : " + resumo + "\n";
    }
}