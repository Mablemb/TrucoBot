import java.util.ArrayList;
import java.util.Locale;

public class Jogador
{

    private String nome;
    private int pontos = 0;
    int time;
    int numero;

    private ArrayList<Carta> mao = new ArrayList<Carta>();

    public Jogador(String nome, int numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void addPontos(int pontos) { this.pontos += pontos; }

    public ArrayList<Carta> getMao() {
        return mao;
    }

    public void ganhaCarta(Carta carta)
    {
        this.mao.add(carta);
    };

    public void resetMao()
    {
        this.mao.clear();
    };

    public Carta jogaCarta(String escolha) // Devolve carta escolhida e marca ela como usada, ou, null caso escolha não seja uma carta
    {
        switch(escolha)
        {
            case "!jogar 1":
                if(!this.mao.get(0).isUsada())
                {
                    this.mao.get(0).setUsada();
                    return this.mao.get(0);
                }
                else
                    return null;
            case "!jogar 2":
                if(!this.mao.get(1).isUsada())
                {
                    this.mao.get(1).setUsada();
                    return this.mao.get(1);
                }
                else
                    return null;
            case "!jogar 3":
                if(!this.mao.get(2).isUsada())
                {
                    this.mao.get(2).setUsada();
                    return this.mao.get(2);
                }
                else
                    return null;
            default:
                return null;
        }

    };

    public void setTime(int time) {
        this.time = time;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public int getTime() {
        return time;
    }
}

