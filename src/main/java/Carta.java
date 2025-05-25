import org.jetbrains.annotations.NotNull;

public class Carta implements Comparable<Carta> {

    private String naipe;
    private String numero;
    private int valor;
    private boolean usada = false;

    public Carta(String naipe, String numero, int valor) {
        this.naipe = naipe;
        this.numero = numero;
        this.valor = valor;
    }

    public String getNaipe() {
        return naipe;
    }

    public String getNumero() {
        return numero;
    }

    public int getValor() {
        return valor;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean isUsada() {
        return usada;
    }

    public void setUsada() { this.usada = true; }

    @Override
    public int compareTo(@NotNull Carta outraCarta) {
        if (this.valor > outraCarta.getValor())
            return 1;
        else
            return 0;
    }
}
