package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DecksController {

    @FXML
    public void trocarParaMenu(ActionEvent event) throws IOException { //troca a cena para o menu

        Parent menuRoot = FXMLLoader.load(getClass().getResource("/fxml/inicioMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(menuRoot));
        stage.show();
    }
}
