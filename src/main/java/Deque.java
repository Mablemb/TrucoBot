import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deque
{

    private ArrayList<Carta> cartas = new ArrayList<Carta>(); // Todas as cartas do truco

    //Construtor cria um deque com todas as cartas, ordenadas conforme a lista naipes

    public Deque()
    {
        ArrayList<Carta> novoDeque = new ArrayList<Carta>();

        String[] numeros = {"4", "5", "6", "7", "J", "Q", "K", "A", "2", "3"};   // Valores usados no truco
        String[] naipes = {"♢", "♤", "♡", "♧"};                            // Naipes do baralho
        List<String> manilhas = Arrays.asList("♢7", "♤A", "♡7", "♧4");

        int valor = 0;

        for (String numero : numeros) {
            for (String naipe : naipes) {
                Carta carta = new Carta(naipe, numero, valor++);

                if (manilhas.contains(naipe + numero)) {
                    switch (carta.getNaipe()) {
                        case "♢":
                            carta.setValor(40);
                            break;
                        case "♤":
                            carta.setValor(41);
                            break;
                        case "♡":
                            carta.setValor(42);
                            break;
                        case "♧":
                            carta.setValor(43);
                            break;
                    }
                }
                novoDeque.add(carta);
            }
        }
        this.cartas = novoDeque;
    }

    public ArrayList<Carta> embaralhar()
    {
        Collections.shuffle(this.cartas);
        return this.cartas;
    }

    public void distribuir(Deque deque, @NotNull ArrayList<Jogador> jogadores, ArrayList<Integer> ordem)
    {
        ArrayList<Jogador> jogadoresOrdenados = new ArrayList<Jogador>();
        for(Integer i : ordem)
            jogadoresOrdenados.add(jogadores.get(i));
        for(Jogador J : jogadoresOrdenados)
        {
            for(int i = 0; i < 3; i++)
            {
                J.ganhaCarta(deque.getCartas().get(0));
                deque.getCartas().remove(0);
            }
        }
    };

    public ArrayList<Carta> getCartas() {
        return cartas;
    }
}