package CORE;

import java.util.ArrayList;

public class Deck {

    public static final int MAXIMO = 8;
    private String nomeDeck;
    private double custoMedio;

    private ArrayList<Carta> cartasDeck = new ArrayList<>();  //lista de cartas do deck

    //Construtor
    public Deck(String nomeDeck){
        this.nomeDeck = nomeDeck;
        this.custoMedio = 0.0;
    }

    //Getters e Setters
    public String getNomeDeck(){return nomeDeck;}
    public void setNomeDeck(String nomeDeck) {this.nomeDeck = nomeDeck;}

    public double getCustoMedio() {return custoMedio;}
    public void setCustoMedio(double custoMedio) {this.custoMedio = custoMedio;}

    public ArrayList<Carta> getCartasDeck() {
        return cartasDeck;
    }
    public void setCartasDeck(ArrayList<Carta> cartasDeck) {
        this.cartasDeck = cartasDeck;
    }

    public void adicionarCarta(Carta carta){
        if(cartasDeck.size() >= MAXIMO){
            System.out.println("O deck " + nomeDeck +" já está cheio, máximo de "+ MAXIMO +" cartas.");

        } else if (cartasDeck.contains(carta)) {
            System.out.println("Carta repetida, a carta " + carta.getNome() + " já está no deck");

        }else {
            this.cartasDeck.add(carta);
            this.custoMedio += carta.getCusto();
        }
    }

    public void removeCarta(Carta carta){
        if(cartasDeck.contains(carta)) {
            cartasDeck.remove(carta);
            this.custoMedio -= carta.getCusto();
        }
    }

    //calcula custo médio de elixir do deck
    public void custoMedio(){
        if(cartasDeck.isEmpty()) {
            this.custoMedio= 0.0;
        }
        else{
            double soma = 0;

            for(Carta carta : cartasDeck){
                soma += carta.getCusto();
            }

            this.custoMedio = soma / cartasDeck.size();
            }
    }

    //toString
    @Override
    public String toString() {
        String estado;

        if(cartasDeck.size() == MAXIMO) estado = "Cheio";
        else if(cartasDeck.isEmpty()) estado = "Vazio";
        else estado = "Incompleto";

        return String.format("%s (Status: %s) - Média de Elixir: %.2f", nomeDeck, estado, custoMedio);
    }

}
