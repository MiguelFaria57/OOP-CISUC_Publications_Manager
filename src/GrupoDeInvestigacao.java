package com.gestordepublicacoesdocisuc;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe que permite criar objetos de Grupos de Investigação.
 * Implementa a interface Serializable.
 */
public class GrupoDeInvestigacao implements Serializable {
    private String nome;
    private String acronimo;
    private MembroEfetivo investigadorResponsavel;
    private ArrayList<Investigador> listaInvestigadores;


    public GrupoDeInvestigacao() {}

    public GrupoDeInvestigacao(String nome, String acronimo, MembroEfetivo investigadorResponsavel, ArrayList<Investigador> listaInvestigadores) {
        this.nome = nome;
        this.acronimo = acronimo;
        this.investigadorResponsavel = investigadorResponsavel;
        this.listaInvestigadores = listaInvestigadores;
    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAcronimo() {
        return acronimo;
    }
    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public MembroEfetivo getInvestigadorResponsavel() {
        return investigadorResponsavel;
    }
    public void setInvestigadorResponsavel(MembroEfetivo investigadorResponsavel) {
        this.investigadorResponsavel = investigadorResponsavel;
    }

    public ArrayList<Investigador> getListaInvestigadores() {
        return listaInvestigadores;
    }
    public void setListaInvestigadores(ArrayList<Investigador> listaInvestigadores) {
        this.listaInvestigadores = listaInvestigadores;
    }


    @Override
    public String toString() {
        ArrayList<String> lI = new ArrayList<>();
        for (Investigador i: listaInvestigadores)
            lI.add(i.getNome());
        return "Grupo De Investigação\n" +
                "  Nome : " + nome + "\n" +
                "  Acrónimo : " + acronimo + "\n" +
                "  Investigador Responsável : " + investigadorResponsavel.getNome() + "\n" +
                "  Lista de Investigadores : " + lI + "\n";
    }
}
