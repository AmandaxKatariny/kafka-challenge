package br.com.challenge.purchase;

public class Card {

    private final String nome;
    private final String cvv;
    private final Number numero;

    public Card(String nome, String cvv, Number numero) {
        this.nome = nome;
        this.cvv = cvv;
        this.numero = numero;
    }

    public String getCvv() {
        return cvv;
    }
    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Card{" +
                "nome='" + nome + '\'' +
                ", cvv=" + cvv +
                ", numero=" + numero +
                '}';
    }
}
