package CORE;

import java.util.ArrayList;

public class Deck {
    private String nomeDeck;
    ArrayList<Carta> cartasDeck = new ArrayList<>();

    public Deck(String nomeDeck){
        this.nomeDeck = nomeDeck;
    }

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
        if(cartasDeck.size() == 8){
            System.out.println("O deck " + nomeDeck +" já está cheio");
        } else if (cartasDeck.contains(carta)) {
            System.out.println("Carta repetida");
        }else {
            cartasDeck.add(carta);
        }
    }

    public void removeCarta(Carta carta){
        cartasDeck.remove(carta);
    }

    public double custoMedio(Deck deck){
        double custo = 0;
        for (int i = 0; i < deck.cartasDeck.size(); i++){
            custo += deck.cartasDeck.get(i).getCusto();
        }
        return custo / (double)deck.cartasDeck.size();
    }


}
