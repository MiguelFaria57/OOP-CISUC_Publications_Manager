package com.gestordepublicacoesdocisuc;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que permite criar objetos de Artigos de Revista.
 * É uma subclasse da classe Publicacao.
 * Implementa a interface Serializable.
 */
public class ArtigoDeRevista extends Publicacao implements Serializable {
    private String nomeRevista;
    private Data dataRevista;
    private int numeroRevista;


    public ArtigoDeRevista() {}

    public ArtigoDeRevista(ArrayList<Investigador> autores, String titulo, String[] palavrasChave, int anoPublicacao,
                           String tipoPublicacao, int dimensaoAudiencia, String resumo, String nomeRevista,
                           Data dataRevista, int numeroRevista) {
        super(autores, titulo, palavrasChave, anoPublicacao, tipoPublicacao, dimensaoAudiencia, resumo);
        this.nomeRevista = nomeRevista;
        this.dataRevista = dataRevista;
        this.numeroRevista = numeroRevista;
    }


    public String getNomeRevista() {
        return nomeRevista;
    }
    public void setNomeRevista(String nomeRevista) {
        this.nomeRevista = nomeRevista;
    }

    public Data getDataRevista() {
        return dataRevista;
    }
    public void setDataRevista(Data dataRevista) {
        this.dataRevista = dataRevista;
    }

    public int getNumeroRevista() {
        return numeroRevista;
    }
    public void setNumeroRevista(int numeroRevista) {
        this.numeroRevista = numeroRevista;
    }

    public String getFatorImpacto() {
        if (dimensaoAudiencia >= 1000)
            return "A";
        else if (dimensaoAudiencia < 500)
            return "C";
        else
            return "B";
    }


    @Override
    public String toString() {
        return super.toString() +
                "  Nome da Revista : " + nomeRevista + "\n" +
                "  Data da Revista : " + dataRevista + "\n" +
                "  Número da Revista : " + numeroRevista + "\n";
    }
}