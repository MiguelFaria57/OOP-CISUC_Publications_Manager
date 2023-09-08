package com.gestordepublicacoesdocisuc;

import java.io.Serializable;

/**
 * Classe abstrata de objetos de Investigadores.
 * Implementa a interface Serializable.
 */
public abstract class Investigador implements Serializable {
    protected String nome;
    protected String email;
    protected GrupoDeInvestigacao grupoInvestigacao;


    public Investigador() {}

    public Investigador(String nome, String email, GrupoDeInvestigacao grupoInvestigacao) {
        this.nome = nome;
        this.email = email;
        this.grupoInvestigacao = grupoInvestigacao;
    }

    public Investigador(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public GrupoDeInvestigacao getGrupoInvestigacao() {
        return grupoInvestigacao;
    }
    public void setGrupoInvestigacao(GrupoDeInvestigacao grupoInvestigacao) {
        this.grupoInvestigacao = grupoInvestigacao;
    }

    public abstract String getTipoInvestigador();
    public abstract MembroEfetivo getInvestigadorOrientador();


    @Override
    public String toString() {
        return getTipoInvestigador() + "\n" +
                "  Nome : " + nome + "\n" +
                "  Email : " + email + "\n" +
                "  Grupo de Investigação : " + grupoInvestigacao.getNome() + "\n";
    }
}
