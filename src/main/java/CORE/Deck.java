package CORE;

import java.util.ArrayList;

public class Deck {
    public static final int MAXIMO = 8;
    private String nomeDeck;
    ArrayList<Carta> cartasDeck = new ArrayList<>();  //lista de cartas do deck

    //Construtor
    public Deck(String nomeDeck){
        this.nomeDeck = nomeDeck;
    }

    //Getters e Setters
    public String getNomeDeck() {
        return nomeDeck;
    }
    public String setNomeDeck(String nomeDeck) {
        return this.nomeDeck = nomeDeck;
    }

    public ArrayList<Carta> getCartasDeck() {
        return cartasDeck;
    }
    public void setCartasDeck(ArrayList<Carta> cartasDeck) {
        this.cartasDeck = cartasDeck;
    }

    public void AddCarta(Carta carta){
        if(cartasDeck.size() >= MAXIMO){
            System.out.println("O deck " + nomeDeck +" já está cheio, máximo de "+ MAXIMO +" cartas.");

        } else if (cartasDeck.contains(carta)) {
            System.out.println("Carta repetida, a carta " + carta.getNome() + " já está no deck");

        }else {
            this.cartasDeck.add(carta);
        }
    }

    public void removeCarta(Carta carta){
        cartasDeck.remove(carta);
    }

    //calcula custo médio de elixir do deck
    public double custoMedio(){
        double custo = 0.0;
        for (Carta carta : cartasDeck) {
            custo += carta.getCusto();                  //calcula custo total das cartas
        }
        if(cartasDeck.size() == 0) return 0.0;
        return custo / (double)cartasDeck.size();  //divide custo total pela quantidade de cartas
    }

    //toString
    @Override
    public String toString() {
        String estado;

        if(cartasDeck.size() == MAXIMO) estado = "Cheio";
        else if(cartasDeck.size() == 0) estado = "Vazio";
        else estado = "Incompleto";

        return String.format("%s (Status: %s) - Média de Elixir: %.2f", nomeDeck, estado, custoMedio());
    }

}
