package br.com.challenge.purchase;


public class Card {

    private final String nome;
    private final Integer cvv;
    private final String  numero;

    public Card(String nome, Integer cvv, String numero) {
        this.nome = nome;
        this.cvv = cvv;
        this.numero = numero;
    }
}
