package com.gestordepublicacoesdocisuc;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe que contém os métodos para a leitura e escrita de ficheiros.
 */
public class LeituraEscritaFicheiros {
    /**
     * Método que adiciona os objetos aos ArrayLists correspondentes.
     *
     * @param lI ArrayList de objetos Investigador
     * @param lG ArrayList de objetos GrupoDeInvestigacao
     * @param lP ArrayList de objetos Publicacao
     */
    public static void lerEscreverFicheiros(ArrayList<Investigador> lI, ArrayList<GrupoDeInvestigacao> lG, ArrayList<Publicacao> lP) {
        lI.addAll(ficheirosInvestigadores());
        lG.addAll(ficheirosGruposDeInvestigacao(lI));
        lP.addAll(ficheirosPublicacoes(lI));
    }


    /**
     * Método que lê o ficheiro de objetos que contem o ArrayList com objetos Investigador caso exista;
     * caso não exista o ficheiro de objetos, lê o ficheiro de texto que contém as informações acerca dos investigadores
     * e cria o ArrayList com objetos Investigador (ainda não verificados, uma vez que não se tem informação sobre o seu
     * grupo de investigação);
     * caso nao exista o ficheiro de texto, é emitida uma mensagem de erro.
     *
     * @return ArrayList de objetos Investigador
     */
    private static ArrayList<Investigador> ficheirosInvestigadores() {
        ArrayList<Investigador> listaInvestigadores = new ArrayList<>();
        File fAO = new File("InvestigadoresObjetos.obj");
        if (fAO.exists() && fAO.isFile()) {
            listaInvestigadores = lerFicheiroInvestigadoresObjetos(fAO);
        }
        else {
            System.out.print("Ficheiro de objetos de 'Investigador' não existe, a criar.\n");
            File fA = new File("Investigadores.txt");
            if (fA.exists() && fA.isFile()) {
                listaInvestigadores = lerFicheiroInvestigadoresTexto(fA);
            }
            else {
                System.out.print("Ficheiro não existe.\n");
            }
        }
        return listaInvestigadores;
    }


    /**
     * Método que lê o ficheiro de objetos que contém o ArrayList com objetos GrupoDeInvestigacao caso exista;
     * caso não exista o ficheiro de objetos, lê o ficheiro de texto que contém as informações acerca dos grupos de
     * investigação e cria o ArrayList com objetos GrupoDeInvestigacao, escreve esse ArrayList num ficheiro de
     * objetos, coloca os objetos corretos no ArrayList com objetos Investigador e escreve esse ArrayList num ficheiro
     * de objetos;
     * caso não exista o ficheiro de texto, é emitida uma mensagem de erro.
     *
     * @param lI ArrayList de objetos Investigador que vai permitir criar os objetos GrupoDeInvestigacao e onde vão ser
     *           colocados os objetos Investigador já verificados
     * @return ArrayList de objetos GrupoDeInvestigacao
     */
    private static ArrayList<GrupoDeInvestigacao> ficheirosGruposDeInvestigacao(ArrayList<Investigador> lI) {
        ArrayList<GrupoDeInvestigacao> listaGruposDeInvestigacao = new ArrayList<>();
        File fAO = new File("GruposDeInvestigacaoObjetos.obj");
        if (fAO.exists() && fAO.isFile()) {
            listaGruposDeInvestigacao = lerFicheiroGruposDeInvestigacaoObjetos(fAO);
        }
        else {
            System.out.print("Ficheiro de objetos de 'GrupoDeInvestigacao' não existe, a criar.\n");
            File fA = new File("GruposDeInvestigacao.txt");
            if (fA.exists() && fA.isFile()) {
                listaGruposDeInvestigacao = lerFicheiroGruposDeInvestigacaoTexto(fA, lI);
                escreverFicheiroGruposDeInvestigacao(listaGruposDeInvestigacao);
                lI.clear();
                for (GrupoDeInvestigacao g: listaGruposDeInvestigacao) {
                    lI.addAll(g.getListaInvestigadores());
                }
                escreverFicheiroInvestigadores(lI);
            }
            else {
                System.out.print("Ficheiro não existe.\n");
            }
        }
        return listaGruposDeInvestigacao;
    }


    /**
     * Método que lê o ficheiro de objetos que contém o ArrayList com objetos Publicacao caso exista;
     * caso não exista o ficheiro de objetos, lê o ficheiro de texto que contém as informações acerca das publicações
     * e cria o ArrayList com objetos Publicacao e escreve esse ArrayList num ficheiro de objetos;
     * caso nao exista o ficheiro de texto, é emitida uma mensagem de erro.
     *
     * @param lI ArrayList de objetos Investigador que vai permitir criar os objetos Publicacao
     * @return ArrayList de objetos Publicacao
     */
    private static ArrayList<Publicacao> ficheirosPublicacoes(ArrayList<Investigador> lI) {
        ArrayList<Publicacao> listaPublicacoes = new ArrayList<>();
        File fAO = new File("PublicacoesObjetos.obj");
        if (fAO.exists() && fAO.isFile()) {
            listaPublicacoes = lerFicheiroPublicacoesObjetos(fAO);
        }
        else {
            System.out.print("Ficheiro de objetos de 'Publicacao' não existe, a criar.\n");
            File fA = new File("Publicacoes.txt");
            if (fA.exists() && fA.isFile()) {
                listaPublicacoes = lerFicheiroPublicacoesTexto(fA, lI);
                escreverFicheiroPublicacoes(listaPublicacoes);
            }
            else {
                System.out.print("Ficheiro não existe.\n");
            }
        }
        return listaPublicacoes;
    }


    /**
     * Método que lê o ficheiro de objetos que contém o ArrayList com objetos Investigador e devolve esse ArrayList.
     *
     * @param f Objeto File com a diretoria do ficheiro de objetos que contém o ArrayList com objetos Investigador a ler
     * @return ArrayList de objetos Investigador
     */
    private static ArrayList<Investigador> lerFicheiroInvestigadoresObjetos(File f) {
        ArrayList<Investigador> lI = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            lI = (ArrayList<Investigador>) ois.readObject();

            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            System.out.print("Erro a abrir ficheiro.\n");
        } catch (IOException e) {
            System.out.print("Erro a ler ficheiro.\n");
        } catch (ClassNotFoundException e) {
            System.out.print("Erro a converter objeto.\n");
        }
        return lI;
    }


    /**
     * Método que lê o ficheiro de texto que contém as informações acerca dos investigadores, cria o ArrayList com os
     * objetos Investigador e devolve esse ArrayList.
     *
     * @param f Objeto File com a diretoria do ficheiro de texto que contém as informações acerca dos investigadores a
     *          ler
     * @return ArrayList de objetos Investigador (não verificados e sem grupo de investigacao)
     */
    private static ArrayList<Investigador> lerFicheiroInvestigadoresTexto(File f) {
        ArrayList<Investigador> lI = new ArrayList<>();
        ArrayList<String[]> linhasME = new ArrayList<>();
        ArrayList<String[]> linhasE = new ArrayList<>();

        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] aux1 = linha.split("#");
                if (aux1[0].equals("Membro Efetivo"))
                    linhasME.add(aux1);
                else if (aux1[0].equals("Estudante"))
                    linhasE.add(aux1);
            }

            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.print("Erro a abrir ficheiro de texto.\n");
        } catch (IOException e) {
            System.out.print("Erro a ler ficheiro de texto.\n");
        }

        ArrayList<MembroEfetivo>  arrayME = new ArrayList<>();
        for (String[] l: linhasME) {
            String nome = l[1];
            String email = l[2];
            int numeroGabinete = Integer.parseInt(l[3]);
            int numeroTelefoneDEI = Integer.parseInt(l[4]);
            MembroEfetivo objeto = new MembroEfetivo(nome, email, numeroGabinete, numeroTelefoneDEI);
            arrayME.add(objeto);
            lI.add(objeto);
        }
        for (String[] l: linhasE) {
            String nome = l[1];
            String email = l[2];
            String tituloTese = l[3];
            String[] aux2 = l[4].split("/");
            Data dataConclusao = new Data(Integer.parseInt(aux2[0]), Integer.parseInt(aux2[1]),
                    Integer.parseInt(aux2[2]));
            MembroEfetivo investigadorOrientador = new MembroEfetivo();
            for (MembroEfetivo m: arrayME) {
                if (l[5].equals(m.getEmail())) {
                    investigadorOrientador = m;
                }
            }
            Estudante objeto = new Estudante(nome, email, tituloTese, dataConclusao, investigadorOrientador);
            lI.add(objeto);
        }
        return lI;
    }


    /**
     * Método que escreve o ArrayList com objetos Investigador num ficheiro de objetos.
     *
     * @param lI ArrayList de objetos Investigador a ser escrito
     */
    private static void escreverFicheiroInvestigadores(ArrayList<Investigador> lI) {
        File fAO = new File("InvestigadoresObjetos.obj");
        try {
            FileOutputStream fos = new FileOutputStream(fAO);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(lI);

            oos.close();
            fos.close();
            System.out.print("Ficheiro de objetos 'Investigador' criado.\n");
        } catch (FileNotFoundException e) {
            System.out.print("Erro a criar ficheiro.\n");
        } catch (IOException e) {
            System.out.print("Erro a escrever para o ficheiro.\n");
        }
    }


    /**
     * Método que lê o ficheiro de objetos que contém o ArrayList com objetos GrupoDeInvestigacao e devolve esse
     * ArrayList.
     *
     * @param f Objeto File com a diretoria do ficheiro de objetos que contém o ArrayList com objetos
     *          GrupoDeInvestigacao a ler
     * @return ArrayList de objetos GrupoDeInvestigacao
     */
    private static ArrayList<GrupoDeInvestigacao> lerFicheiroGruposDeInvestigacaoObjetos(File f) {
        ArrayList<GrupoDeInvestigacao> lG = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            lG = (ArrayList<GrupoDeInvestigacao>) ois.readObject();

            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            System.out.print("Erro a abrir ficheiro.\n");
        } catch (IOException e) {
            System.out.print("Erro a ler ficheiro.\n");
        } catch (ClassNotFoundException e) {
            System.out.print("Erro a converter objeto.\n");
        }
        return lG;
    }


    /**
     * Método que lê o ficheiro de texto que contém as informações acerca dos grupos de investigação, cria o ArrayList
     * com os objetos GrupoDeInvestigacao e devolve esse ArrayList; é também atualizado em cada objeto do ArrayList com
     * objetos Investigador o grupo de investigação.
     *
     * @param f Objeto File com a diretoria do ficheiro de texto que contém as informações acerca dos grupos de
     *          investigação a ler
     * @param lI ArrayList de objetos Investigador que vai permitir criar os objetos GrupoDeInvestigacao
     * @return ArrayList de objetos GrupoDeInvestigacao
     */
    private static ArrayList<GrupoDeInvestigacao> lerFicheiroGruposDeInvestigacaoTexto(File f, ArrayList<Investigador> lI) {
        ArrayList<GrupoDeInvestigacao> lG = new ArrayList<>();
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] aux1 = linha.split("#");
                String nome = aux1[0];
                String acronimo = aux1[1];
                MembroEfetivo membroEfetivo = new MembroEfetivo();
                for (Investigador i: lI) {
                    if (aux1[2].equals(i.getEmail())) {
                        membroEfetivo = (MembroEfetivo) i;
                    }
                }
                String[] aux2 = aux1[3].split(",");
                ArrayList<Investigador> listaInvestigadores = new ArrayList<>();
                for (String s: aux2) {
                    for (Investigador i: lI) {
                        if (s.equals(i.getEmail())) {
                            if (i.getTipoInvestigador().equals("Estudante")) {
                                if (Arrays.asList(aux2).contains(i.getInvestigadorOrientador().getEmail()))
                                    listaInvestigadores.add(i);
                                else
                                    lI.remove(i);
                            }
                            else {
                                listaInvestigadores.add(i);
                            }
                        }
                    }
                }
                GrupoDeInvestigacao objeto = new GrupoDeInvestigacao(nome, acronimo, membroEfetivo, listaInvestigadores);
                for(Investigador iG: objeto.getListaInvestigadores()) {
                    lI.get(lI.indexOf(iG)).setGrupoInvestigacao(objeto);
                }
                lG.add(objeto);
            }

            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.print("Erro a abrir ficheiro de texto.\n");
        } catch (IOException e) {
            System.out.print("Erro a ler ficheiro de texto.\n");
        }
        return lG;
    }


    /**
     * Método que escreve o ArrayList com objetos GrupoDeInvestigacao num ficheiro de objetos.
     *
     * @param lG ArrayList de objetos GrupoDeInvestigacao a ser escrito
     */
    private static void escreverFicheiroGruposDeInvestigacao(ArrayList<GrupoDeInvestigacao> lG) {
        File fAO = new File("GruposDeInvestigacaoObjetos.obj");
        try {
            FileOutputStream fos = new FileOutputStream(fAO);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(lG);

            oos.close();
            fos.close();
            System.out.print("Ficheiro de objetos 'GrupoDeInvestigacao' criado.\n");
        } catch (FileNotFoundException e) {
            System.out.print("Erro a criar ficheiro.\n");
        } catch (IOException e) {
            System.out.print("Erro a escrever para o ficheiro.\n");
        }
    }


    /**
     * Método que lê o ficheiro de objetos que contém o ArrayList com objetos Publicacao e devolve esse ArrayList.
     *
     * @param f Objeto File com a diretoria do ficheiro de objetos que contém o ArrayList com objetos Publicacao a ler
     * @return ArrayList de objetos Publicacao
     */
    private static ArrayList<Publicacao> lerFicheiroPublicacoesObjetos(File f) {
        ArrayList<Publicacao> lP = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

             lP = (ArrayList<Publicacao>) ois.readObject();

            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            System.out.print("Erro a abrir ficheiro.\n");
        } catch (IOException e) {
            System.out.print("Erro a ler ficheiro.\n");
        } catch (ClassNotFoundException e) {
            System.out.print("Erro a converter objeto.\n");
        }
        return lP;
    }


    /**
     * Método que lê o ficheiro de texto que contém as informações acerca das publicações, cria o ArrayList com os
     * objetos Publicacao e devolve esse ArrayList.
     *
     * @param f Objeto File com a diretoria do ficheiro de texto que contém as informações acerca das publicações
     * @param lI ArrayList de objetos Investigador que vai permitir criar os objetos Publicacao
     * @return ArrayList de objetos Publicacao
     */
    private static ArrayList<Publicacao> lerFicheiroPublicacoesTexto(File f, ArrayList<Investigador> lI) {
        ArrayList<Publicacao> lP = new ArrayList<>();
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] aux1 = linha.split("#");
                String[] aux2 = aux1[0].split(",");
                ArrayList<Investigador> autores = new ArrayList<>();
                for (String a: aux2) {
                    for (Investigador i: lI) {
                        if (a.equals(i.getEmail())) {
                            autores.add(i);
                        }
                    }
                }
                String titulo = aux1[1];
                String[] palavrasChave = aux1[2].split(",");
                int anoPublicacao = Integer.parseInt(aux1[3]);
                String tipoPublicacao = aux1[4];
                int dimensaoAudiencia = Integer.parseInt(aux1[5]);
                String resumo = aux1[6];
                switch (tipoPublicacao) {
                    case "Artigo de Conferencia" -> {
                        String nomeConferencia = aux1[7];
                        String[] aux3 = aux1[8].split("/");
                        Data dataConferencia = new Data(Integer.parseInt(aux3[0]), Integer.parseInt(aux3[1]),
                                Integer.parseInt(aux3[2]));
                        String localizacaoConferencia = aux1[9];
                        ArtigoDeConferencia objeto = new ArtigoDeConferencia(autores, titulo, palavrasChave,
                                anoPublicacao, tipoPublicacao, dimensaoAudiencia, resumo,
                                nomeConferencia, dataConferencia, localizacaoConferencia);
                        lP.add(objeto);
                    }
                    case "Artigo de Revista" -> {
                        String nomeRevista = aux1[7];
                        String[] aux3 = aux1[8].split("/");
                        Data dataRevista = new Data(Integer.parseInt(aux3[0]), Integer.parseInt(aux3[1]),
                                Integer.parseInt(aux3[2]));
                        int numeroRevista = Integer.parseInt(aux1[9]);
                        ArtigoDeRevista objeto = new ArtigoDeRevista(autores, titulo, palavrasChave,
                                anoPublicacao, tipoPublicacao, dimensaoAudiencia, resumo,
                                nomeRevista, dataRevista, numeroRevista);
                        lP.add(objeto);
                    }
                    case "Livro" -> {
                        String editora = aux1[7];
                        String ISBN = aux1[8];
                        Livro objeto = new Livro(autores, titulo, palavrasChave, anoPublicacao,
                                tipoPublicacao, dimensaoAudiencia, resumo, editora, ISBN);
                        lP.add(objeto);
                    }
                    case "Capitulo de Livro" -> {
                        String editora = aux1[7];
                        String ISBN = aux1[8];
                        String nomeCapitulo = aux1[9];
                        int paginaInicio = Integer.parseInt(aux1[10]);
                        int paginaFim = Integer.parseInt(aux1[11]);
                        CapitulosDeLivro objeto = new CapitulosDeLivro(autores, titulo, palavrasChave,
                                anoPublicacao, tipoPublicacao, dimensaoAudiencia, resumo,
                                editora, ISBN, nomeCapitulo, paginaInicio, paginaFim);
                        lP.add(objeto);
                    }
                    case "Livro de Artigos de Conferencia" -> {
                        String editora = aux1[7];
                        String ISBN = aux1[8];
                        String nomeConferencia = aux1[9];
                        int numerosArtigos = Integer.parseInt(aux1[10]);
                        LivroDeArtigosConferencia objeto = new LivroDeArtigosConferencia(autores, titulo,
                                palavrasChave, anoPublicacao, tipoPublicacao,
                                dimensaoAudiencia, resumo, editora, ISBN,
                                nomeConferencia, numerosArtigos);
                        lP.add(objeto);
                    }
                }
            }

            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.print("Erro a abrir ficheiro de texto.\n");
        } catch (IOException e) {
            System.out.print("Erro a ler ficheiro de texto.\n");
        }
        return lP;
    }


    /**
     * Método que escreve o ArrayList com objetos Publicacao num ficheiro de objetos.
     *
     * @param lP ArrayList de objetos Publicacao a ser escrito
     */
    private static void escreverFicheiroPublicacoes(ArrayList<Publicacao> lP) {
        File fAO = new File("PublicacoesObjetos.obj");
        try {
            FileOutputStream fos = new FileOutputStream(fAO);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(lP);

            oos.close();
            fos.close();
            System.out.print("Ficheiro de objetos 'Publicacao' criado.\n");
        } catch (FileNotFoundException e) {
            System.out.print("Erro a criar ficheiro.\n");
        } catch (IOException e) {
            System.out.print("Erro a escrever para o ficheiro.\n");
        }
    }
}