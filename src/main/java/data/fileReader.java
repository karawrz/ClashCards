package data;

import core.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class fileReader {

    private static final String CARDS_FILE_PATH = "cartas.csv";
    private static final String DECKS_FILE_PATH = "decks.csv";

    public List<Carta> lerCartas(){ //le a linha da carta do arquivo csv e retorna um objeto carta
        List<Carta> listaDeCartas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CARDS_FILE_PATH))){
            String linha;

            while((linha = br.readLine()) != null){
                if(linha.trim().isEmpty()) {
                    continue;
                }
                String[] dados = linha.split(",");

                if(dados.length < 12){
                    continue;
                }

                try{
                    Carta c = new Carta();

                    c.setNome(dados[0]);
                    c.setNivel(Integer.parseInt(dados[1]));
                    c.setCusto(Integer.parseInt(dados[2]));
                    c.setRaridade(Raridade.valueOf(dados[3]));
                    c.setImagem(dados[4]);
                    c.setDano(Integer.parseInt(dados[5]));
                    c.setDanoPS(Double.parseDouble(dados[6]));
                    c.setVida(Integer.parseInt(dados[7]));
                    c.setAlvos(Alvos.valueOf(dados[8]));
                    c.setAlcance(Alcance.valueOf(dados[9]));
                    c.setVelocidade(Velocidade.valueOf(dados[10]));
                    c.setVelocidadeImpacto(Double.parseDouble(dados[11]));

                    listaDeCartas.add(c);
                } catch (Exception e){
                    System.out.println("Erro ao ler uma carta específica (Pulei ela): " + e.getMessage());
                }
            }
        } catch (java.io.FileNotFoundException e) {
            // Este catch resolve o erro específico do new FileReader()
            System.out.println("Arquivo não encontrado: O arquivo será criado ao salvar a primeira carta.");

        } catch (java.io.IOException e) {
            // Este catch resolve erros gerais de leitura (readLine)
            System.out.println("Erro de leitura: " + e.getMessage());
        }


        return  listaDeCartas;
    }

    private Carta buscarPorCarta(String nome, List<Carta> listaDeCartas){ //busca a carta em um deck baseado em seu nome
        for(Carta c : listaDeCartas){
            if(c.getNome().equalsIgnoreCase(nome.trim())){
                return c;
            }
        }
        return null;
    }

    public List<Deck> lerDeck(List<Carta> listaDeCartas) { //le a linha de um deck no csv e retorna um objeto deck
        List<Deck> listaDeck = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(DECKS_FILE_PATH))){
            String linha;

            while((linha = br.readLine()) != null){
                if(linha.trim().isEmpty()) {
                    continue;
                }
                String[] dados = linha.split(",");
                if(dados.length < 9){
                    continue;
                }

                String nomeDeck = dados[0];
                Deck d = new Deck(nomeDeck);

                for(int i = 1; i < dados.length; i++){
                    String nomeCarta = dados[i];
                    Carta cartaEncontrada = buscarPorCarta(nomeCarta, listaDeCartas);

                    if(cartaEncontrada != null){
                        d.adicionarCarta(cartaEncontrada);
                    }
                }
                listaDeck.add(d);
            }
        } catch(java.io.FileNotFoundException e){
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        } catch(java.io.IOException e){
            System.out.println("Erro de leitura: " + e.getMessage());
        }


        return listaDeck;
    }
}