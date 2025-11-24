package data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class fileWriter {
    private static final String CARDS_FILE_PATH = "cartas.csv";
    private static final String DECKS_FILE_PATH = "decks.csv";

    public Boolean escreverCarta (String[] carta){ //salva a carta no arquvio csv

        java.io.File f = new java.io.File("Cards.csv");

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(CARDS_FILE_PATH, true))){

            String cartaFormatada = String.join(",", carta);

            bw.write(cartaFormatada);
            bw.newLine();

        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public Boolean escreverDeck (String[] deck){ //salva o deck no arquivo csv

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(DECKS_FILE_PATH, true))){

            String  deckFormatado = String.join(",", deck);

            bw.write(deckFormatado);
            bw.newLine();

        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean removerLinha(String nomeDoItem, String caminhoDoArquivo){ //apaga a carta (ou deck) do arquivo csv
        List<String> linhasMantidas = new ArrayList<>();
        boolean encontrou = false;

        try(BufferedReader br = new BufferedReader(new FileReader(caminhoDoArquivo))){
            String linha;

            while ((linha = br.readLine()) != null){
                String[] dados = linha.split(",");
                if(dados.length > 0){
                    String nomeNaLinha = dados[0];

                    if(nomeNaLinha.equals(nomeDoItem)){
                        encontrou = true;
                    }else{
                        linhasMantidas.add(linha);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler linha para remover: " + e.getMessage());
            return false;
        }

        if(!encontrou){
            return false;
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoDoArquivo, false))){
            for(String linha : linhasMantidas){
                bw.write(linha);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao reescrever arquivo: " + e.getMessage());
            return false;
        }

    }
}
