package com.gestordepublicacoesdocisuc;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que permite criar objetos de Artigos de Conferência.
 * É uma subclasse da classe Publicacao.
 * Implementa a interface Serializable.
 */
public class ArtigoDeConferencia extends Publicacao implements Serializable {
    private String nomeConferencia;
    private Data dataConferencia;
    private String localizacaoConferencia;


    public ArtigoDeConferencia() {}

    public ArtigoDeConferencia(ArrayList<Investigador> autores, String titulo, String[] palavrasChave, int anoPublicacao,
                               String tipoPublicacao, int dimensaoAudiencia, String resumo, String nomeConferencia,
                               Data dataConferencia, String localizacaoConferencia) {
        super(autores, titulo, palavrasChave, anoPublicacao, tipoPublicacao, dimensaoAudiencia, resumo);
        this.nomeConferencia = nomeConferencia;
        this.dataConferencia = dataConferencia;
        this.localizacaoConferencia = localizacaoConferencia;
    }


    public String getNomeConferencia() {
        return nomeConferencia;
    }
    public void setNomeConferencia(String nomeConferencia) {
        this.nomeConferencia = nomeConferencia;
    }

    public Data getDataConferencia() {
        return dataConferencia;
    }
    public void setDataConferencia(Data dataConferencia) {
        this.dataConferencia = dataConferencia;
    }

    public String getLocalizacaoConferencia() {
        return localizacaoConferencia;
    }
    public void setLocalizacaoConferencia(String localizacaoConferencia) {
        this.localizacaoConferencia = localizacaoConferencia;
    }

    public String getFatorImpacto() {
        if (dimensaoAudiencia >= 500)
            return "A";
        else if (dimensaoAudiencia < 200)
            return "C";
        else
            return "B";
    }


    @Override
    public String toString() {
        return super.toString() +
                "  Nome da Conferência : " + nomeConferencia + "\n" +
                "  Data de Conferência : " + dataConferencia + "\n" +
                "  Localização da Conferência : " + localizacaoConferencia + "\n";
    }
}