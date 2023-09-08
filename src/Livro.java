package com.gestordepublicacoesdocisuc;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que permite criar objetos de Livros.
 * É uma subclasse da classe Publicacao.
 * Implementa a interface Serializable.
 */
public class Livro extends Publicacao implements Serializable {
    protected String editora;
    protected String ISBN;


    public Livro() {}

    public Livro(ArrayList<Investigador> autores, String titulo, String[] palavrasChave, int anoPublicacao,
                 String tipoPublicacao, int dimensaoAudiencia, String resumo, String editora, String ISBN) {
        super(autores, titulo, palavrasChave, anoPublicacao, tipoPublicacao, dimensaoAudiencia, resumo);
        this.editora = editora;
        this.ISBN = ISBN;
    }


    public String getEditora() {
        return editora;
    }
    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
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
                "  Editora : " + editora + "\n" +
                "  ISBN : " + ISBN + "\n";
    }
}