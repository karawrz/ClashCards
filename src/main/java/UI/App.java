package UI;

import CORE.Carta;
import CORE.Deck;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {


    public void start(Stage stage) throws IOException {
        Font SuperCell = Font.loadFont(getClass().getResourceAsStream("/fonts/SuperCell-Magic.ttf"), 30);

        FXMLLoader loader = new FXMLLoader(App.class.getResource("/FXML/InicioMenu.fxml"));

        Parent root = loader.load();
        stage.setTitle("ClashCards");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

