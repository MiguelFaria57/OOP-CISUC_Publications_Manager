package com.gestordepublicacoesdocisuc;

import java.util.ArrayList;

/**
 * Classe que contém o método 'main' onde é criada a aplicação.
 */
public class GestorDePublicacoesDoCISUC {
    /**
     * Método 'main' onde são inicializados os ArrayLists para objetos Investigador, GrupoDeInvestigacao e Publicacao e
     * chamadas as funções para ler ou escrever ficheiros e apresentar a interface de consola.
     * @param args (default)
     */
    public static void main(String[] args) {
        ArrayList<Investigador> listaInvestigadores = new ArrayList<>();
        ArrayList<GrupoDeInvestigacao> listaGruposInvestigacao = new ArrayList<>();
        ArrayList<Publicacao> listaPublicacoes = new ArrayList<>();

        LeituraEscritaFicheiros.lerEscreverFicheiros(listaInvestigadores, listaGruposInvestigacao, listaPublicacoes);
        if (!listaInvestigadores.isEmpty() || !listaGruposInvestigacao.isEmpty() || !listaPublicacoes.isEmpty())
            InterfaceDeConsola.mostrarMenu(listaInvestigadores, listaGruposInvestigacao, listaPublicacoes);
        else
            System.out.println("\nErro - verifique os ficheiros.");
    }
}