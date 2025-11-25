package ui;

import core.Alcance;
import core.Alvos;
import core.Raridade;
import core.Velocidade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class StudioController {
    @FXML private TextField nomeTextField;
    @FXML private TextField urlTextField;

    @FXML private ChoiceBox<Raridade> raridadeChoiceBox;
    @FXML private ChoiceBox<Alcance> alcanceChoiceBox;
    @FXML private ChoiceBox<Velocidade> velocidadeChoiceBox;
    @FXML private ChoiceBox<Alvos> alvosChoiceBox;


    @FXML
    public void initialize(){
        raridadeChoiceBox.getItems().addAll(Raridade.values());
        alcanceChoiceBox.getItems().addAll(Alcance.values());
        velocidadeChoiceBox.getItems().addAll(Velocidade.values());
        alvosChoiceBox.getItems().addAll(Alvos.values());

    }

    @FXML
    public void trocarParaMenu(ActionEvent event) throws IOException { //troca a cena para o menu

        Parent menuRoot = FXMLLoader.load(getClass().getResource("/fxml/inicioMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(menuRoot));
        stage.show();
    }
}
