package UI;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class App extends Application {

    public int scrWidth = 1200;
    public int scrHeight = 600;

    public void start(Stage stage){

        Group telaInicial = new Group();

        //gradiente da tela inicial
        LinearGradient gradiente = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(1, Color.ROYALBLUE),
                new Stop(0, Color.MIDNIGHTBLUE)
        );

        Rectangle bg = new Rectangle(scrWidth, scrHeight, gradiente);
        telaInicial.getChildren().add(bg);

        Image title = new Image(getClass().getResourceAsStream("/images/clashcardstitle.png"));
        ImageView imageView = new ImageView(title);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitWidth(title.getWidth()/3);
        imageView.setFitHeight(title.getHeight()/3);
        telaInicial.getChildren().add(imageView);

        Scene scene = new Scene(telaInicial, 1200, 600);

        stage.setResizable(false);
        stage.setTitle("ClashCards");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
