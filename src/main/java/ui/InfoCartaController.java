package ui;

import core.Carta;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;

public class InfoCartaController {

    @FXML private Label nomeLabel;
    @FXML private ImageView imagemView;
    @FXML private Label raridadeLabel;
    @FXML private Label elixirLabel;
    @FXML private Label vidaLabel;
    @FXML private Label danoLabel;

    public void definirDados(Carta carta) { //define os dados a serem mostrados quando clica no botão de informação
        nomeLabel.setText(carta.getNome());
        raridadeLabel.setText("Raridade: " + carta.getRaridade());
        elixirLabel.setText("Elixir: " + carta.getCusto());
        vidaLabel.setText("Vida: " + carta.getVida());
        danoLabel.setText("Dano: " + carta.getDano());

        //carrega a imagem da carta na janela
        if (carta.getImagem() != null && !carta.getImagem().isEmpty()) {
            try {
                File f = new File(carta.getImagem());
                if (f.exists()) {
                    imagemView.setImage(new Image(f.toURI().toString()));
                }
            } catch (Exception e) {}
        }
    }

    //fecha a janela atual
    @FXML
    public void fecharJanela() {
        Stage stage = (Stage) nomeLabel.getScene().getWindow();
        stage.close();
    }

}
