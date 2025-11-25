package ui;

import core.Carta;
import data.fileReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
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

    private VBox criarVisual(Carta carta){
        VBox cardBox = new javafx.scene.layout.VBox();
        cardBox.setAlignment(javafx.geometry.Pos.CENTER);
        cardBox.setSpacing(5);


        Button btnCarta = new Button();
        btnCarta.setPrefSize(120, 160);

        btnCarta.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-radius: 5; -fx-background-radius: 5; -fx-cursor: hand;");


        //carrega a imagem caso tenha
        boolean imagemCarregada = false;
        String caminhoImg = carta.getImagem();

        if (caminhoImg != null && !caminhoImg.isEmpty()) {
            try {
                java.io.File arquivo = new java.io.File(caminhoImg);


                if (arquivo.exists()) {
                    Image img = new Image(arquivo.toURI().toString());
                    ImageView view = new ImageView(img);


                    view.setFitWidth(100);
                    view.setFitHeight(100);
                    view.setPreserveRatio(true);

                    btnCarta.setGraphic(view);
                    btnCarta.setText(carta.getNome());
                    btnCarta.setContentDisplay(ContentDisplay.TOP);
                    imagemCarregada = true;
                }
            } catch (Exception e) {
                System.out.println("Erro ao carregar imagem: " + e.getMessage());
            }
        }

//caso nao tenha imagem gera um layout com o nome dela e a raridade
        if (!imagemCarregada) {
            btnCarta.setText(carta.getNome() + "\n(" + carta.getRaridade() + ")");
            btnCarta.setTextAlignment(TextAlignment.CENTER);
            btnCarta.setWrapText(true);
        }



        //inicia o botao de inforamções da carta
        Button btnInfo = new Button("Info");
        btnInfo.setPrefWidth(120);
        btnInfo.setStyle("-fx-background-color: #2196f3; -fx-text-fill: white; -fx-cursor: hand; -fx-background-radius: 0;");

        btnInfo.setOnAction(evento -> {
            mostrarDetalhes(carta);
        });


        //inicia e configura o botão de excluir a carta
        Button btnExcluir = new Button("Excluir");
        btnExcluir.setStyle("-fx-background-color: #ffcdd2; -fx-text-fill: darkred; -fx-font-size: 11px; -fx-cursor: hand;");
        btnExcluir.setPrefWidth(120);

        btnExcluir.setOnAction(e -> {
            Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacao.setTitle("Excluir Carta");
            confirmacao.setHeaderText("Tem certeza que deseja apagar '" + carta.getNome() + "'?");

            if (confirmacao.showAndWait().get() == ButtonType.OK) {

                data.fileWriter escritor = new data.fileWriter();

                if (escritor.removerLinha(carta.getNome(), "Cards.csv")) {
                    listaColecao.remove(carta);
                } else {
                    mostrarAlerta("Erro", "Não foi possível apagar do arquivo.");
                }
            }
        });

        //adiciona todos os botões n vbox
        cardBox.getChildren().addAll(btnCarta, btnInfo, btnExcluir);

        return cardBox;
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarDetalhes(Carta carta){
        //abre uma janela com as informações da carta
        try{
            FXMLLoader loader = new  FXMLLoader(getClass().getResource("/fxml/DetalhesCarta.fxml")); //inicia a janela de detalhes
            Parent root = loader.load();

            InfoCartaController controller = loader.getController();
            controller.definirDados(carta);

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.initStyle(javafx.stage.StageStyle.TRANSPARENT);
            scene.setFill(javafx.scene.paint.Color.TRANSPARENT);

            stage.setScene(scene);

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void trocarParaMenu(ActionEvent event) throws IOException {

        Parent menuRoot = FXMLLoader.load(getClass().getResource("/FXML/inicioMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(menuRoot));
        stage.show();
    }

}
