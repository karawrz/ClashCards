package UI;

import CORE.Carta;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class App extends Application {

    public void start(Stage stage) {

        //tamanho da tela
        int scrWidth = 1200;
        int scrHeight = 650;
        stage.setResizable(false);

        //
        BorderPane root = new BorderPane();
        root.getStyleClass().add("root-pane");
        HBox centro = new HBox(50);
        VBox esquerdaVb = new VBox(10);
        VBox direitaVb = new VBox(10);
        Label lblTitulo = new Label("CLASHCARDS");
        Scene cena = new Scene(root, scrWidth, scrHeight);

        ChoiceBox<String> cbRaridade = new ChoiceBox<>();
        for(Carta.enumRaridade raridade : Carta.enumRaridade.values()){
            cbRaridade.getItems().addAll(raridade.toString());
        }
        cbRaridade.setValue("Comum");
        cbRaridade.getStyleClass().add("choice-box");

        Label lblRaridade = new Label("RARIDADE:");
        esquerdaVb.getChildren().addAll(lblRaridade, cbRaridade);

        esquerdaVb.setPadding(new javafx.geometry.Insets(40, 40, 40, 200));
        esquerdaVb.setAlignment(Pos.TOP_LEFT);

        centro.setAlignment(Pos.CENTER);
        root.setTop(lblTitulo);
        root.setLeft(esquerdaVb);
        root.setRight(direitaVb);
        BorderPane.setAlignment(lblTitulo, Pos.CENTER);


        stage.setTitle("ClashCards");

        lblTitulo.getStyleClass().add("titulo");
        //arquivo do css
        cena.getStylesheets().add(getClass().getResource("/Application.css").toExternalForm());
        stage.setScene(cena);
        stage.show();
        }
    }
