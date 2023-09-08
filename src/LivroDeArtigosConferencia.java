package com.gestordepublicacoesdocisuc;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que permite criar objetos de Livros de Artigos de Conferência.
 * É uma subclasse da classe Livro.
 * Implementa a interface Serializable.
 */
public class LivroDeArtigosConferencia extends Livro implements Serializable {
    private String nomeConferencia;
    private int numeroArtigos;


    public LivroDeArtigosConferencia() {}

    public LivroDeArtigosConferencia(ArrayList<Investigador> autores, String titulo, String[] palavrasChave,
                                     int anoPublicacao, String tipoPublicacao, int dimensaoAudiencia, String resumo,
                                     String editora, String ISBN, String nomeConferencia, int numeroArtigos) {
        super(autores, titulo, palavrasChave, anoPublicacao, tipoPublicacao, dimensaoAudiencia, resumo, editora, ISBN);
        this.nomeConferencia = nomeConferencia;
        this.numeroArtigos = numeroArtigos;
    }


    public String getNomeConferencia() {
        return nomeConferencia;
    }
    public void setNomeConferencia(String nomeConferencia) {
        this.nomeConferencia = nomeConferencia;
    }

    public int getNumeroArtigos() {
        return numeroArtigos;
    }
    public void setNumeroArtigos(int numeroArtigos) {
        this.numeroArtigos = numeroArtigos;
    }

    public String getFatorImpacto() {
        if (dimensaoAudiencia >= 7500)
            return "A";
        else if (dimensaoAudiencia < 2500)
            return "C";
        else
            return "B";
    }


    @Override
    public String toString() {
        return super.toString() +
                "  Nome da Conferência : " + nomeConferencia + "\n" +
                "  Número de Artigos : " + numeroArtigos + "\n";
    }
}