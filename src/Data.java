package com.gestordepublicacoesdocisuc;

import java.io.Serializable;

/**
 * Classe que permite criar objetos de Datas.
 * Implementa a interface Serializable.
 */
public class Data implements Serializable {
    private int dia, mes, ano;


    Data() {}

    Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }


    int getDia() {return dia;}
    int getMes() {return mes;}
    int getAno() {return ano;}

    void setDia(int d) {dia = d;}
    void setMes(int m) {mes = m;}
    void setAno(int a) {ano = a;}


    @Override
    public String toString() {
        return dia + "/" + mes + "/" + ano;
    }
}
