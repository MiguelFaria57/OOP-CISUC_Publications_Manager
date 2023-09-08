package com.gestordepublicacoesdocisuc;

import java.io.Serializable;

/**
 * Classe que permite criar objetos de Membros Efetivos.
 * É uma subclasse da classe Investigador.
 * Implementa a interface Serializable.
 */
public class MembroEfetivo extends Investigador implements Serializable {
    private int numeroGabinete;
    private int numeroTelefoneDEI;

    public MembroEfetivo() {}

    public MembroEfetivo(String nome, String email, GrupoDeInvestigacao grupoInvestigacao, int numeroGabinete, int numeroTelefoneDEI) {
        super(nome, email, grupoInvestigacao);
        this.numeroGabinete = numeroGabinete;
        this.numeroTelefoneDEI = numeroTelefoneDEI;
    }

    public MembroEfetivo(String nome, String email, int numeroGabinete, int numeroTelefoneDEI) {
        super(nome, email);
        this.numeroGabinete = numeroGabinete;
        this.numeroTelefoneDEI = numeroTelefoneDEI;
    }



    public int getNumeroGabinete() {
        return numeroGabinete;
    }
    public void setNumeroGabinete(int numeroGabinete) {
        this.numeroGabinete = numeroGabinete;
    }

    public int getNumeroTelefoneDEI() {
        return numeroTelefoneDEI;
    }
    public void setNumeroTelefoneDEI(int numeroTelefoneDEI) {
        this.numeroTelefoneDEI = numeroTelefoneDEI;
    }

    public String getTipoInvestigador() {
        return "Membro Efetivo";
    }

    public MembroEfetivo getInvestigadorOrientador() {
        return null;
    }


    @Override
    public String toString() {
        return super.toString() +
                "  Número de Gabinete : " + numeroGabinete + "\n" +
                "  Número de Telefone do DEI : " + numeroTelefoneDEI + "\n";
    }
}
