package DATA;

import CORE.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class fileReader {

        private static final String CARDS_FILE_PATH = "cartas.csv";
        private static final String DECKS_FILE_PATH = "decks.csv";

        public List<Carta> lerCartas(){
            List<Carta> listaDeDeck = new ArrayList<>();

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

                        listaDeDeck.add(c);
                    } catch (Exception e){
                        System.out.println("Erro ao ler : " + e.getMessage());
                    }
                }
            } catch (java.io.FileNotFoundException e) {
                System.out.println("Arquivo não encontrado.");

            } catch (java.io.IOException e) {
                System.out.println("Erro de leitura: " + e.getMessage());
            }
            
            return  listaDeDeck;
        }
}


