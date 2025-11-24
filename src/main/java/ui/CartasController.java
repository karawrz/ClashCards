package ui;

import core.Carta;
import data.fileReader;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

import java.io.FileReader;
import java.util.List;

public class CartasController {

    @FXML private FlowPane containerCartas;

    private List<Carta> listaColecao;

    public void initialize(){
        carregarCartas();
        updategrid();
    }

    private void carregarCartas(){
        fileReader fr = new fileReader();
        this.listaColecao = fr.lerCartas();
    }

    private void updategrid(){
        containerCartas.getChildren().clear();

        if(listaColecao != null){
            for(Carta carta : listaColecao){
                containerCartas.getChildren().add(criarVisual(carta));
            }
        }
    }


}
