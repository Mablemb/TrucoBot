import java.util.ArrayList;
import java.util.Collections;

public class Mesa {

    private Deque deque;
    private ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
    private ArrayList<Jogador> ordemDeJogada= new ArrayList<Jogador>();
    private ArrayList<Carta> cartasNaMesa = new ArrayList<Carta>();

    private int pontosRodadaTime1 = 0;
    private int pontosRodadaTime2 = 0;
    private int pontosEtapaTime1 = 0;
    private int pontosEtapaTime2 = 0;


    private ArrayList<Jogador> time1 = new ArrayList<Jogador>();
    private ArrayList<Jogador> time2 = new ArrayList<Jogador>();

    private int contadorRodada = 1;
    private int embaralhador = (2+contadorRodada)%4;

    private int ultimoVencedor = 0;
    private int vezDe = 1;
    boolean desistencia = false;

    private int pontosRodada = 0;

    public String iniciar()
    {
        jogadores.clear();
        ordemDeJogada.clear();
        cartasNaMesa.clear();

        return("Digite \"!entrar\" para se juntar à mesa.");
    };

    public String adicionarJogador(String name) {

        Jogador novoJogador = new Jogador(name, jogadores.size());

        if (getJogadores().size() < 4)
        {
            if(getJogadores().size() < 2)
            {
                novoJogador.setTime(1);
                jogadores.add(novoJogador);
                time1.add(novoJogador);
                return (name + " entrou no time 1!");
            }
            else
            {
                novoJogador.setTime(2);
                jogadores.add(novoJogador);
                time2.add(novoJogador);
                if (getJogadores().size() < 4)
                    return (name + " entrou no time 2!");

                else
                    ordemDeJogada.add(jogadores.get(0));
                    ordemDeJogada.add(jogadores.get(2));
                    ordemDeJogada.add(jogadores.get(1));
                    ordemDeJogada.add(jogadores.get(3));

                    return ("\n"+name + "entrou no time 2! \n\n Time 1: \n"
                            + time1.get(0).getNome() + "\n" + time1.get(1).getNome()
                            + "\n \n Time 2:\n"
                            + time2.get(0).getNome() + "\n" + time2.get(1).getNome()
                            + "\n\nDigite \"!embaralhar\" para começar!");
            }

        }
        else
            return ("Mesa cheia :(");
    }

    public String novaRodada()
    {
        if (getJogadores().size() == 4)
        {
            deque = new Deque();
            ArrayList<Integer> ordem = definirOrdem(embaralhador);
            deque.embaralhar();

            for (Jogador jogador : jogadores)
                jogador.resetMao();

            deque.distribuir(deque, ordemDeJogada, ordem);

            return ("Rodada " + contadorRodada++ + ":\n\n" +
                    ordemDeJogada.get(ordem.get(3)).getNome()+ " embaralhando e distribuindo...\n\n"+
                    ordemDeJogada.get(ordem.get(0)).getNome()+ " recebendo cartas...\n"+
                    ordemDeJogada.get(ordem.get(1)).getNome()+ " recebendo cartas...\n"+
                    ordemDeJogada.get(ordem.get(2)).getNome()+ " recebendo cartas...\n"+
                    ordemDeJogada.get(ordem.get(3)).getNome()+ " recebendo cartas...\n\n"+
                    "(Digite \"!cartas\" para receber suas cartas por mensagem privada)\n"+
                    jogadores.get(ordem.get(0)).getNome() + " digite \"!jogar\" + \"(1, 2 ou 3)\" para jogar uma carta!");
        }
        else
            return("Ainda faltam jogadores na mesa!");
    }

    public String jogar(String nomeJogador, String escolha)
    {
        // Descobrir quem jogou:

        Jogador jogadorDaVez = null;
        for(Jogador jogador : jogadores)
            if(jogador.getNome().equals(nomeJogador))
                jogadorDaVez = jogador;

        if (jogadorDaVez == null)
            return("Jogador não encontrado!");

        Carta escolhida = jogadorDaVez.jogaCarta(escolha);      // Caso jogue uma carta

        if(escolhida != null)
        {
            cartasNaMesa.add(escolhida);
            if(cartasNaMesa.size() < 4)
                return(nomeJogador + " jogou " + escolhida.getNumero() + escolhida.getNaipe());
            else
                return(comparaCartas());
        }

        else if(escolha.equals("!f"))                           // Caso desista
        {
            if(comparaCartas().equals("-1"))
            {
                if(jogadorDaVez.getTime() == 1)
                {
                    if(pontosRodada == 0)
                    {
                        time2.get(0).addPontos(1);
                        time2.get(1).addPontos(1);
                        pontosRodadaTime2 += 1;
                    }
                    else
                    {
                        time2.get(0).addPontos(pontosRodada);
                        time2.get(1).addPontos(pontosRodada);
                        pontosRodadaTime2 += pontosRodada;
                        pontosRodada = 0;
                    }
                }
                else
                {
                    if(pontosRodada == 0)
                    {
                        time1.get(0).addPontos(1);
                        time1.get(1).addPontos(1);
                        pontosRodadaTime1 += 1;
                    }
                    else
                    {
                        time1.get(0).addPontos(pontosRodada);
                        time1.get(1).addPontos(pontosRodada);
                        pontosRodadaTime1 += pontosRodada;
                        pontosRodada = 0;
                    }
                }
                return(jogadorDaVez.getNome() + "correu!\nPlacar (Time 1 / Time 2): " + pontosRodadaTime1 + " / " + pontosRodadaTime2);
            }
            else
                return (comparaCartas());
        }

        else if(escolha.equals("!truco"))                       // Caso peça truco
        {
            return ("VISH! Digite \"!cai\" para aceitar ou \"!f\" para desistir!");
        }

        else if(escolha.equals("!cai"))                       // Caso peça truco
        {
            pontosRodada += 3;
            return (jogadorDaVez.getNome() + "Aceitou!! Rodada agora vale " + pontosRodada + "!");
        }

        else
            return ("Comando não reconhecido, escolha uma carta não utilizada (1, 2, 3), !f ou !truco");
    }

    private String comparaCartas()
    {
        if(cartasNaMesa.size() > 0)
        {
            for(Carta carta : cartasNaMesa)
                System.out.println(carta.getNumero()+carta.getNaipe()+": "+carta.getValor());

            Carta maiorCarta = Collections.max(cartasNaMesa);

            System.out.println(maiorCarta.getNumero()+maiorCarta.getNaipe()+maiorCarta.getValor());

            Jogador ganhador = null;

            for(Jogador J : jogadores)
            {
                if(J.getMao().contains(maiorCarta))
                {
                    ultimoVencedor = J.getNumero();
                    ganhador = J;

                    System.out.println(ganhador.getNome());


                    if(pontosEtapaTime1 < 2 & pontosEtapaTime2 <2) {
                        if (ganhador.getTime() == 1)
                            pontosEtapaTime1 += 1;
                        else
                            pontosEtapaTime2 += 1;
                        if (pontosEtapaTime1 < 2 & pontosEtapaTime2 < 2)
                        {
                            int pts = (pontosRodada == 0) ?  1 : pontosRodada;

                            if (ganhador.getTime() == 1)
                            {
                                pontosRodadaTime1 += pts;
                                time1.get(0).setPontos(pts);
                                time1.get(1).setPontos(pts);
                            }
                            else
                            {
                                pontosRodadaTime2 += pts;
                                time2.get(0).setPontos(pts);
                                time2.get(1).setPontos(pts);
                            }

                            pontosRodada = 0;
                        }
                    }

                }
            }


            cartasNaMesa.clear();
            return("Maior carta: " + maiorCarta.getNumero()+maiorCarta.getNaipe() + "(" + ganhador.getNome() +"), ponto(s) para o time " + ganhador.getTime()
            + "Placar (Time 1 / Time 2): " + pontosRodadaTime1 + " / " + pontosRodadaTime2);
        }
        else
        {
            return("-1");
        }
    }

    public String verOpções(String nomeDoJogador)
    {
        Jogador solicitador = null;

        for(Jogador jogador : jogadores)
        {
            if (jogador.getNome().equals(nomeDoJogador))
                solicitador = jogador;
        }

            if (solicitador != null)
            {
                return
                        (
                                "1: " + solicitador.getMao().get(0).getNumero() + solicitador.getMao().get(0).getNaipe() +
                                        "\n2: " + solicitador.getMao().get(1).getNumero() + solicitador.getMao().get(1).getNaipe() +
                                        "\n3: " + solicitador.getMao().get(2).getNumero() + solicitador.getMao().get(2).getNaipe()
                        );
            }
            else
                return("Jogador não encontrado");
    }

    private ArrayList<Integer> definirOrdem(int vencedor)
    {
        ArrayList<Integer> ordem = new ArrayList<Integer>();
        switch (vencedor)
        {
            case 0:
                ordem.add(0);
                ordem.add(2);
                ordem.add(1);
                ordem.add(3);
                break;
            case 1:
                ordem.add(2);
                ordem.add(1);
                ordem.add(3);
                ordem.add(0);
                break;
            case 2:
                ordem.add(1);
                ordem.add(3);
                ordem.add(0);
                ordem.add(2);
                break;
            case 3:
                ordem.add(3);
                ordem.add(0);
                ordem.add(2);
                ordem.add(1);
                break;
        }
        return ordem;
    };

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public String getPontos()
    {
        return("Equipe 1: " + pontosRodadaTime1 + "\nEquipe 2: " + pontosRodadaTime2);
    };

}

