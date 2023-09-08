package com.gestordepublicacoesdocisuc;

import java.time.Year;
import java.util.*;

/**
 * Classe que contém os métodos para a interface de consola.
 */
public class InterfaceDeConsola {
    /**
     * Método que apresenta o menu na consola, chamando outros métodos para cada uma das tarefas a realizar, consoante o
     * pedido pelo utilizador.
     * @param lI ArrayList de objetos Investigador
     * @param lG ArrayList de objetos GrupoDeInvestigacao
     * @param lP ArrayList de objetos Publicacao
     */
    public static void mostrarMenu(ArrayList<Investigador> lI, ArrayList<GrupoDeInvestigacao> lG, ArrayList<Publicacao> lP) {
        int opcao = 0;
        do {
            System.out.println("""
                
                ------------------------------------------------- MENU -------------------------------------------------
                 1 - Apresentar os indicadores gerais do CISUC
                 2 - Listar as publicações de um grupo de investigação, dos últimos 5 anos, organizadas por ano, por 
                     tipo de publicação e por fator de impacto
                 3 - Listar os membros de um grupo de investigação agrupados por categoria
                 4 - Listar as publicações de um investigador agrupadas por ano, tipo de publicação e fator de impacto
                 5 - Listar todos os grupos de investigação
                 6 - Sair""");
            int verificar = 0;
            while (verificar != 1) {
                try {
                    System.out.print("\nIndique a opção que pretende: ");
                    Scanner sc = new Scanner(System.in);
                    opcao = sc.nextInt();
                    if (opcao>0 && opcao<7)
                        verificar = 1;
                    else
                        System.out.println("Opção incorreta, escolha de novo.");
                } catch (InputMismatchException e) {
                    System.out.println("Opção incorreta, escolha de novo.");
                }
            }
            if (opcao == 1)
                indicadoresGeraisCISUC(lI, lP);
            if (opcao == 2)
                publicacoesGrupoUlt5AnosOrganizadas(lG, lP);
            if (opcao == 3)
                membrosGrupoInvestigacaoAgrupados(lG);
            if (opcao == 4)
                publicacoesInvestigadorAgrupadas(lI, lP);
            if (opcao == 5)
                dadosGruposInvestigacao(lG, lP);
        } while (opcao != 6);
    }


    /**
     * Método que apresenta os indicadores gerais do CISUC: total de membros, número de membros de cada categoria, total
     * de publicações dos últimos 5 anos, número de publicações de cada tipo.
     *
     * @param lI ArrayList de objetos Investigador
     * @param lP ArrayList de objetos Publicacao
     */
    private static void indicadoresGeraisCISUC(ArrayList<Investigador> lI, ArrayList<Publicacao> lP) {
        int opcao = 0;
        do {
            System.out.println("""
                
                ------------------------------------------------- MENU -------------------------------------------------
                 1 - Apresentar os indicadores gerais do CISUC
                     1) Total de membros
                     2) Número total de membros de cada categoria
                     3) Total de publicações dos últimos 5 anos
                     4) Número de publicações de cada tipo
                     5) Voltar""");
            int verificar = 0;
            while (verificar != 1) {
                try {
                    System.out.print("\nIndique a opção que pretende: ");
                    Scanner sc = new Scanner(System.in);
                    opcao = sc.nextInt();
                    if (opcao>0 && opcao<6)
                        verificar = 1;
                    else
                        System.out.println("Opção incorreta, escolha de novo.");
                } catch (InputMismatchException e) {
                    System.out.println("Opção incorreta, escolha de novo.");
                }
            }
            if (opcao == 1) {
                System.out.printf("\nTotal de membros: %d\n", lI.size());
            }
            if (opcao == 2) {
                int[] numMembrosCadaCategoria = numeroMembrosCadaCategoria(lI);
                int numMembrosEfetivos = numMembrosCadaCategoria[0], numEstudantes = numMembrosCadaCategoria[1];
                System.out.printf("\nNúmero de Membros Efetivos: %d\nNúmero de Estudantes: %d\n",
                                    numMembrosEfetivos, numEstudantes);
            }
            if (opcao == 3) {
                System.out.printf("\nTotal de publicações nos últimos 5 anos: %d\n", totalPublicacoesUltimos5Anos(lP));
            }
            if (opcao == 4) {
                int[] numPublicacoesCadaTipo = numeroPublicacoesCadaTipo(lP);
                int numArtConferencia = numPublicacoesCadaTipo[0], numArtRevista = numPublicacoesCadaTipo[1],
                                        numLivro = numPublicacoesCadaTipo[2], numCapLivro = numPublicacoesCadaTipo[3],
                                        numLivArtConferencia = numPublicacoesCadaTipo[4];
                System.out.printf("""
                             
                             Número de publicações 'Artigo de Conferência': %d
                             Número de publicações 'Artigo de Revista': %d
                             Número de publicações 'Livro': %d
                             Número de publicações 'Capítulo de Livro': %d
                             Número de publicações 'Livro de Artigos de Conferência': %d
                             """,
                            numArtConferencia, numArtRevista, numLivro, numCapLivro, numLivArtConferencia);
            }
        } while (opcao != 5);
    }


    /**
     * Método que lista as publicações de um grupo de investigação, dos últimos 5 anos, organizadas por ano, tipo de
     * publicação e por fator de impacto.
     *
     * @param lG ArrayList de objetos GrupoDeInvestigacao
     * @param lP ArrayList de objetos Publicacao
     */
    private static void publicacoesGrupoUlt5AnosOrganizadas(ArrayList<GrupoDeInvestigacao> lG, ArrayList<Publicacao> lP) {
        System.out.println("""
                
                ------------------------------------------------- MENU -------------------------------------------------
                 2 - Listar as publicações de um grupo de investigação, dos últimos 5 anos, organizadas por ano, por 
                     tipo de publicação e por fator de impacto""");
        System.out.print("\nIndique o nome ou acrónimo do grupo de investigação: ");
        Scanner sc = new Scanner(System.in);
        String opcao = sc.nextLine();
        GrupoDeInvestigacao grupo = null;
        for (GrupoDeInvestigacao g: lG) {
            if (opcao.equalsIgnoreCase(g.getNome()) || opcao.equalsIgnoreCase(g.getAcronimo()))
                grupo = g;
        }
        if (grupo != null) {
            ArrayList<Publicacao> lPUlt5Anos = publicacoesUltimos5AnosOrdenadasGrupo(grupo, lP);
            System.out.println();
            for (Publicacao p : lPUlt5Anos) {
                System.out.printf("Publicação com o título '%s', publicada no ano '%d', do tipo '%s' e com fator de impacto '%s'.\n",
                                    p.getTitulo(), p.getAnoPublicacao(), p.getTipoPublicacao(), p.getFatorImpacto());
            }
        }
        else
            System.out.println("Grupo de Investigação não existente.");
    }


    /**
     * Método que lista os membros de um grupo de investigação agrupados por categoria.
     *
     * @param lG ArrayList de objetos GrupoDeInvestigacao
     */
    private static void membrosGrupoInvestigacaoAgrupados(ArrayList<GrupoDeInvestigacao> lG) {
        System.out.println("""
                
                ------------------------------------------------- MENU -------------------------------------------------
                 3 - Listar os membros de um grupo de investigação agrupados por categoria""");
        System.out.print("\nIndique o nome ou acrónimo do grupo de investigação: ");
        Scanner sc = new Scanner(System.in);
        String opcao = sc.nextLine();
        GrupoDeInvestigacao grupo = null;
        for (GrupoDeInvestigacao g: lG) {
            if (opcao.equalsIgnoreCase(g.getNome()) || opcao.equalsIgnoreCase(g.getAcronimo()))
                grupo = g;
        }
        if (grupo != null) {
            ArrayList<Investigador> listaMembrosAgrupados = new ArrayList<>();
            listaMembrosAgrupados.addAll(grupo.getListaInvestigadores());
            listaMembrosAgrupados.sort(Comparator.comparing(Investigador::getTipoInvestigador));
            System.out.println();
            for (Investigador i: listaMembrosAgrupados) {
                System.out.printf("%s %s.\n", i.getTipoInvestigador(), i.getNome());
            }
        }
        else
            System.out.println("Grupo de Investigação não existente.");
    }


    /**
     * Método que lista as publicações de um investigador agrupadas por ano, tipo de publicação e fator de impacto.
     *
     * @param lI ArrayList de objetos Investigador
     * @param lP ArrayList de objetos Publicacao
     */
    private static void publicacoesInvestigadorAgrupadas(ArrayList<Investigador> lI, ArrayList<Publicacao> lP) {
        System.out.println("""
                
                ------------------------------------------------- MENU -------------------------------------------------
                 4 - Listar as publicações de um investigador agrupadas por ano, tipo de publicação e fator de impacto""");
        System.out.print("\nIndique o email do investigador: ");
        Scanner sc = new Scanner(System.in);
        String opcao = sc.nextLine();
        Investigador investigador = null;
        for (Investigador i: lI) {
            if (opcao.equalsIgnoreCase(i.getEmail()))
                investigador = i;
        }
        if (investigador != null) {
            ArrayList<Publicacao> publicacoesInvestigador = publicacoesOrdenadasInvestigador(investigador, lP);
            System.out.println();
            for (Publicacao p : publicacoesInvestigador) {
                System.out.printf("Publicação com o título '%s', publicada no ano '%d', do tipo '%s' e com fator de impacto '%s'.\n",
                        p.getTitulo(), p.getAnoPublicacao(), p.getTipoPublicacao(), p.getFatorImpacto());
            }
        }
        else
            System.out.println("Investigador não existente.");
    }


    /**
     * Método que lista todos os grupos de investigação, e apresenta para cada grupo: total de membros, número de
     * membros de cada categoria, total de publicações dos últimos 5 anos, número de publicações, dos últimos 5 anos,
     * agrupadas por ano, tipo de publicação e fator de impacto.
     *
     * @param lG ArrayList de objetos GrupoDeInvestigacao
     * @param lP ArrayList de objetos Publicacao
     */
    private static void dadosGruposInvestigacao(ArrayList<GrupoDeInvestigacao> lG, ArrayList<Publicacao> lP) {
        System.out.println("""
                
                ------------------------------------------------- MENU -------------------------------------------------
                 5 - Listar todos os grupos de investigação""");
        for (GrupoDeInvestigacao g: lG) {
            String nome = g.getNome();
            int totalMembros = g.getListaInvestigadores().size();
            int[] numMembrosCadaCategoria = numeroMembrosCadaCategoria(g.getListaInvestigadores());
            int numMembrosEfetivos = numMembrosCadaCategoria[0], numEstudantes = numMembrosCadaCategoria[1];
            ArrayList<Publicacao> listaPubGrupo = publicacoesUltimos5AnosOrdenadasGrupo(g, lP);
            int totalPublicacoesUlt5AnosGrupo = listaPubGrupo.size();
            String numPubAgrupadas = "";
            int num = 1;
            for (int i=1; i<listaPubGrupo.size(); i++) {
                if (listaPubGrupo.get(i).getAnoPublicacao() == listaPubGrupo.get(i-1).getAnoPublicacao()
                    && listaPubGrupo.get(i).getTipoPublicacao().equals(listaPubGrupo.get(i-1).getTipoPublicacao())
                    && listaPubGrupo.get(i).getFatorImpacto().equals(listaPubGrupo.get(i-1).getFatorImpacto())) {
                    num ++;
                }
                else {
                    numPubAgrupadas += "Número de publicações no ano '" + listaPubGrupo.get(i-1).getAnoPublicacao() +
                                        "', do tipo '" + listaPubGrupo.get(i-1).getTipoPublicacao() +
                                        "', com fator de impacto '" + listaPubGrupo.get(i-1).getFatorImpacto() +
                                        "': " + num + "\n  ";
                    num = 1;
                }
            }
            numPubAgrupadas += "Número de publicações no ano '" + listaPubGrupo.get(listaPubGrupo.size()-1).getAnoPublicacao() +
                    "', do tipo '" + listaPubGrupo.get(listaPubGrupo.size()-1).getTipoPublicacao() +
                    "', com fator de impacto '" + listaPubGrupo.get(listaPubGrupo.size()-1).getFatorImpacto() +
                    "': " + num + "\n  ";
            System.out.printf("""
                             
                             %s
                               Total de membros: %d
                               Número de Membros Efetivos: %d
                               Número de Estudantes: %d
                               Total de publicações dos últimos 5 anos: %d
                               %s""",
                 nome, totalMembros, numMembrosEfetivos, numEstudantes, totalPublicacoesUlt5AnosGrupo, numPubAgrupadas);
        }
    }


    /**
     * Método que calcula o número de membros de cada categoria.
     *
     * @param l ArrayList com objetos Investigador
     * @return Array de inteiros com o número de membros de cada categoria
     */
    private static int[] numeroMembrosCadaCategoria(ArrayList<Investigador> l) {
        int[] numMembrosCadaCategoria = {0,0};
        for (Investigador i : l) {
            if (i.getTipoInvestigador().equals("Membro Efetivo"))
                numMembrosCadaCategoria[0]++;
            if (i.getTipoInvestigador().equals("Estudante"))
                numMembrosCadaCategoria[1]++;
        }
        return numMembrosCadaCategoria;
    }

    /**
     * Método que calcula o total de publicações dos últimos 5 anos.
     *
     * @param l ArrayList de objetos Publicacao
     * @return inteiro do número de publicações dos últimos 5 anos
     */
    private static int totalPublicacoesUltimos5Anos(ArrayList<Publicacao> l) {
        int ano = Year.now().getValue();
        int numPublicacoesUlt5Anos = 0;
        for (Publicacao p: l) {
            if (p.getAnoPublicacao() >= ano-5 && p.getAnoPublicacao() <= ano)
                numPublicacoesUlt5Anos ++;
        }
        return numPublicacoesUlt5Anos;
    }

    /**
     * Método que calcula o número de publicações de cada tipo.
     *
     * @param l ArrayList de objetos Publicacao
     * @return Array de inteiros com o número de publicações de cada tipo
     */
    private static int[] numeroPublicacoesCadaTipo(ArrayList<Publicacao> l) {
        int[] numPublicacoesCadaTipo = {0,0,0,0,0};
        for (Publicacao p: l) {
            if (p.getTipoPublicacao().equals("Artigo de Conferencia"))
                numPublicacoesCadaTipo[0] ++;
            if (p.getTipoPublicacao().equals("Artigo de Revista"))
                numPublicacoesCadaTipo[1] ++;
            if (p.getTipoPublicacao().equals("Livro"))
                numPublicacoesCadaTipo[2] ++;
            if (p.getTipoPublicacao().equals("Capitulo de Livro"))
                numPublicacoesCadaTipo[3] ++;
            if (p.getTipoPublicacao().equals("Livro de Artigos de Conferencia"))
                numPublicacoesCadaTipo[4] ++;
        }
        return numPublicacoesCadaTipo;
    }


    /**
     * Método que obtém as publicações de um grupo de investigação, dos últimos 5 anos, organizadas.
     *
     * @param g Objeto GrupoDeInvestigacao do qual se pretende saber as publicações
     * @param l ArrayList de objetos Publicacao
     * @return ArrayList com objetos Publicacao as quais qualquer um dos membros do grupo pertence
     */
    private static ArrayList<Publicacao> publicacoesUltimos5AnosOrdenadasGrupo(GrupoDeInvestigacao g, ArrayList<Publicacao> l) {
        ArrayList<String> titulos = new ArrayList<>();
        ArrayList<Publicacao> lPUlt5Anos = new ArrayList<>();
        int ano = Year.now().getValue();
        for (Investigador iG : g.getListaInvestigadores()) {
            for (Publicacao p : l) {
                if (!titulos.contains(p.getTitulo())) {
                    if (p.getNomesAutores().contains(iG.getNome())) {
                        if (p.getAnoPublicacao() >= ano-5 && p.getAnoPublicacao() <= ano) {
                            lPUlt5Anos.add(p);
                            titulos.add(p.getTitulo());
                        }
                    }
                }
            }
        }
        lPUlt5Anos.sort(Comparator.comparing(Publicacao::getAnoPublicacao)
                .thenComparing(Publicacao::getTipoPublicacao)
                .thenComparing(Publicacao::getDimensaoAudiencia));
        return lPUlt5Anos;
    }


    /**
     * Método que obtém as publicações de um investigador ordenadas.
     *
     * @param i Objeto Investigador de quem se pretende saber as publicações
     * @param l ArrayList de objetos Publicacao
     * @return ArrayList com objetos Publicacao aos quais o investigador pertence
     */
    private static ArrayList<Publicacao> publicacoesOrdenadasInvestigador(Investigador i, ArrayList<Publicacao> l) {
        ArrayList<Publicacao> publicacoesInvestigador = new ArrayList<>();
        for (Publicacao p : l) {
            if (p.getNomesAutores().contains(i.getNome())) {
                publicacoesInvestigador.add(p);
            }
        }
        publicacoesInvestigador.sort(Comparator.comparing(Publicacao::getAnoPublicacao)
                .thenComparing(Publicacao::getTipoPublicacao)
                .thenComparing(Publicacao::getDimensaoAudiencia));
        return publicacoesInvestigador;
    }
}