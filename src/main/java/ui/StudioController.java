package ui;

import core.*;
import data.fileWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class StudioController {
    @FXML private TextField nomeTextField;
    @FXML private TextField urlTextField;
    @FXML private TextField elixirTextField;
    @FXML private int vidaTextField;
    @FXML private int danoTextField;
    @FXML private TextField nivelTextField;
    @FXML private TextField custoTextField;
    @FXML private TextField danoPSTextField;
    @FXML private TextField velocidadeImpactoTextField;


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

    public void salvar(){
        try {
            fileWriter fw = new fileWriter();

            String nome = nomeTextField.getText();
            String alvos = alvosChoiceBox.getValue().toString();
            String url = urlTextField.getText();
            String alcance = alcanceChoiceBox.getValue().toString();
            String velocidade = velocidadeChoiceBox.getValue().toString();
            String elixir = elixirTextField.getText();
            int vida = vidaTextField;
            int dano = danoTextField;
            String nivel = nivelTextField.getText();
            String custo = custoTextField.getText();
            String danoPS = danoPSTextField.getText();
            String velocidadeImpacto = velocidadeImpactoTextField.getText();
            String raridade = raridadeChoiceBox.getValue().toString();

            Carta carta = new Carta();
            carta.setNome(nome);
            carta.setAlvos(alvos);
            carta.setRaridade(raridade);
            carta.setVelocidade(velocidade);
            carta.setVelocidadeImpacto(velocidadeImpacto);
            carta.setDano(dano);
            carta.setNivel(nivel);
            carta.setVida(vida);
            carta.setAlcance(alcance);
            carta.setCusto(elixir);
            carta.setImagem(url);
            carta.setDanoPS(danoPS);
    }

    @FXML
    public void trocarParaMenu(ActionEvent event) throws IOException { //troca a cena para o menu

        Parent menuRoot = FXMLLoader.load(getClass().getResource("/fxml/inicioMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(menuRoot));
        stage.show();
    }

    private void limpar(){
        nomeTextField.clear();
        urlTextField.clear();
        elixirTextField.clear();
        vidaTextField.clear();
        danoTextField.clear();
        nivelTextField.clear();
        custoTextField.clear();
        danoPSTextField.clear();
        velocidadeImpactoTextField.clear();
        raridadeChoiceBox.setValue(null);
        alcanceChoiceBox.setValue(null);
        velocidadeChoiceBox.setValue(null);
        alvosChoiceBox.setValue(null);
    }

    public void avisarErro(String nome, String texto){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(nome);
        alert.setHeaderText(nome);
        alert.setContentText(texto);
        alert.showAndWait();
    }
}
