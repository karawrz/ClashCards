package UI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {

        //tamanho da tela
        int scrWidth = 1200;
        int scrHeight = 650;

        //caracteristicas da janela
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.setTitle("ClashCards");

        //Stack pane com fundo
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        root.setPrefSize(scrWidth, scrHeight);
        root.getStyleClass().add("root-pane");

        //Vbox base (meio)
        VBox vboxBase = new VBox();
        vboxBase.setPrefSize(725, 650);
        vboxBase.setSpacing(10);
        root.getChildren().add(vboxBase);

        // Criar container para o título
        StackPane topContainer = new StackPane();
        topContainer.setAlignment(Pos.TOP_CENTER);
        topContainer.setPrefSize(scrWidth, 100);
        root.getChildren().add(topContainer);

        //uitop
        Rectangle topUi = new Rectangle(scrWidth, 80);
        topUi.setFill(Color.CORNFLOWERBLUE);
        topUi.setStroke(Color.ALICEBLUE);
        topUi.setStrokeWidth(8);
        topUi.getStyleClass().add("top-ui");
        topContainer.getChildren().add(topUi);

        //titulo
        Label lblTitulo = new Label("ClashCards");
        lblTitulo.getStyleClass().add("titulo");
        topContainer.getChildren().add(lblTitulo);

        //cena
        Scene scene = new Scene(root, scrWidth, scrHeight);
        //arquivo do css
        scene.getStylesheets().add(getClass().getResource("/Application.css").toExternalForm());

        //stage
        Image icon = new Image(getClass().getResourceAsStream("/images/icon.png"));
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }
}
