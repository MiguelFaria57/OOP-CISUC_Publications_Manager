package com.gestordepublicacoesdocisuc;

import java.io.Serializable;

/**
 * Classe que permite criar objetos de Estudantes.
 * É uma subclasse da classe Investigador.
 * Implementa a interface Serializable.
 */
public class Estudante extends Investigador implements Serializable {
    private String tituloTese;
    private Data dataConclusao;
    private MembroEfetivo investigadorOrientador;


    public Estudante() {}

    public Estudante(String nome, String email, GrupoDeInvestigacao grupoInvestigacao, String tituloTese, Data dataConclusao, MembroEfetivo investigadorOrientador) {
        super(nome, email, grupoInvestigacao);
        this.tituloTese = tituloTese;
        this.dataConclusao = dataConclusao;
        this.investigadorOrientador = investigadorOrientador;
    }

    public Estudante(String nome, String email, String tituloTese, Data dataConclusao, MembroEfetivo investigadorOrientador) {
        super(nome, email);
        this.tituloTese = tituloTese;
        this.dataConclusao = dataConclusao;
        this.investigadorOrientador = investigadorOrientador;
    }


    public String getTituloTese() {
        return tituloTese;
    }
    public void setTituloTese(String tituloTese) {
        this.tituloTese = tituloTese;
    }

    public Data getDataConclusao() {
        return dataConclusao;
    }
    public void setDataConclusao(Data dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public MembroEfetivo getInvestigadorOrientador() {
        return investigadorOrientador;
    }
    public void setInvestigadorOrientador(MembroEfetivo investigadorOrientador) {
        this.investigadorOrientador = investigadorOrientador;
    }

    public String getTipoInvestigador() {
        return "Estudante";
    }


    @Override
    public String toString() {
        return super.toString() +
                "  Título da Tese : " + tituloTese + "\n" +
                "  Data de Conclusão : " + dataConclusao + "\n" +
                "  Investigador Orientador : " + investigadorOrientador.getNome() + "\n";
    }
}
