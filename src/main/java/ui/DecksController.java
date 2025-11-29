package ui;

import core.Carta;
import core.Deck;
import data.fileReader;
import data.fileWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DecksController {

    @FXML private FlowPane containerDecks;
    @FXML private FlowPane containerCartas;

    @FXML private Label elixirMedioLabel;
    @FXML private Button btnSalvarDeck;
    @FXML private TextField nomeDeckField;
    @FXML private ComboBox<Deck> meusDecksComboBox;

    private List<Carta> todasCartas;
    private Deck deckAtual;

    @FXML
    public void initialize() {
        deckAtual = new Deck("novo deck");

        carregarColecao();
        carregarDecksSalvos();

        atualizarVisual();

        meusDecksComboBox.setOnAction(event -> {
            Deck deckSelecionado = meusDecksComboBox.getValue();
            if (deckSelecionado != null) {
                carregarDeckNaTela(deckSelecionado);
            }
        });
        configurarDragDrop();
    }

    private void carregarDecksSalvos() {
        //inicia um leitor de csv
        fileReader fR =  new fileReader();
        //lista de deck com todos os decks salvos no csv
        List<Deck> decksSalvos = fR.lerDeck(todasCartas);

        //popula o combobox com os decks salvos
        meusDecksComboBox.getItems().addAll(decksSalvos);
    }

    private void carregarDeckNaTela(Deck deck) {//carrega o deck atual na tela
        this.deckAtual= deck;

        nomeDeckField.setText(deck.getNomeDeck());

        atualizarVisual();
    }

    private void carregarColecao() {//carrega todas as cartas salvas no csv
        fileReader fR = new fileReader();
        todasCartas = fR.lerCartas();
    }

    private void atualizarVisual() {
        //limpa o container toda vez que a janela abre
        containerCartas.getChildren().clear();
        containerDecks.getChildren().clear();

        //salva as cartas no deck atual
        List<Carta> cartasNoDeck = deckAtual.getCartasDeck();

        for (Carta carta : todasCartas) { //desenha as cartas disponíveis para o deck
            //impede que cartas já adicionadas no deck sejam mostradas como opções
            if(!cartasNoDeck.contains(carta)) {
                containerCartas.getChildren().add(criarBotaoCarta(carta, true));
            }
        }

        for (Carta carta : cartasNoDeck) { //desenha o deck
            containerDecks.getChildren().add(criarBotaoCarta(carta, false));
        }
        elixirMedioLabel.setText(String.format("%.1f",deckAtual.getCustoMedio()));
        btnSalvarDeck.setDisable(cartasNoDeck.size() != 8);
    }

    private Button criarBotaoCarta(Carta carta, boolean modoAdicionar) { //cada carta se comporta como um botão esse método cria esses botões

        //cria o botao e define o tamanho preferível
        Button btn = new Button();
        btn.setPrefSize(90,120);

        //define uma flag para saber se a imagem foi carregada no botão
        Boolean imgCarregada = false;
        //verifica se a carta tem caminho de imagem
        if(carta.getImagem() != null && !carta.getImagem().isEmpty()){
            //adicionar o caminho da imagem como um arquivo de imagem
            //configura tamanho e aspecto
            try{
                File f = new File(carta.getImagem());
                if(f.exists()){
                    ImageView view = new ImageView(new Image(f.toURI().toString()));
                    view.setFitWidth(70);
                    view.setFitHeight(70);
                    view.setPreserveRatio(true);
                    btn.setGraphic(view);
                    btn.setContentDisplay(ContentDisplay.TOP);
                    btn.setText(carta.getNome());
                    imgCarregada = true;
                }
            } catch (Exception e){
                System.out.println("Falha ao carregar imagem: " + e.getMessage());
            }
        }

        //caso a flag retorne negativa ele carrega um layout com o nome e o custo de elixir da carta
        if(!imgCarregada){
            btn.setText(carta.getNome() + "\n(" + carta.getCusto() + ")");
            btn.setTextAlignment(TextAlignment.CENTER);
            btn.setWrapText(true);
        }

        //configura a opção de drag and drop da carta
        btn.setOnDragDetected(event -> {
            //incia do dragboard da carta
            javafx.scene.input.Dragboard db = btn.startDragAndDrop(javafx.scene.input.TransferMode.MOVE);

            //copia a carta pra clipboard e cola onde o drop acontecer
            javafx.scene.input.ClipboardContent content = new javafx.scene.input.ClipboardContent();
            content.putString(carta.getNome());
            db.setContent(content);

            db.setDragView(btn.snapshot(null, null));

            event.consume();
        });

        //define se está adicionando ou removendo cartas do deck
        if(modoAdicionar){
            btn.setStyle("-fx-background-color: #e3f2fd; -fx-cursor: open_hand; -fx-border-color: #bbdefb;");
        }else{
            btn.setStyle("-fx-background-color: #ffcdd2; -fx-cursor: open_hand; -fx-border-color: #ef9a9a;");
        }

        return btn;
    }


    @FXML
    public void salvarDeck() {
        //verifica se o deck tem um nome e mostra um alerta caso o usuário não tenha colocado
        String nome = nomeDeckField.getText().trim();
        if (nome.isEmpty()) {
            mostrarAlerta("Nome Invalido", "Por favor, digite um nome para o seu deck!");
            return;
        }

        //verifica se o deck está completo e alerta caso esteja incompleto
        if (deckAtual.getCartasDeck().size() != 8) {
            mostrarAlerta("Deck Incompleto", "O deck precisa ter 8 cartas");
            return;
        }

        deckAtual.setNomeDeck(nome);

        //cria o array de string a ser transformado em csv
        String[] linhaParaCSV = new String[9];
        //define sempre a primeira posição do array para o nome do deck
        linhaParaCSV[0] = nome;

        //adiciona o nome das cartas do deck no array
        List<Carta> cartas = deckAtual.getCartasDeck();
        for(int i = 0; i < 8; i++){
            linhaParaCSV[i + 1] = cartas.get(i).getNome();
        }

        //le e escreve o array no arquivo csv
        fileWriter fW = new fileWriter();
        if(fW.escreverDeck(linhaParaCSV)){
            mostrarAlerta("Sucesso", "Deck salvo com sucesso!");
        }else{
            mostrarAlerta("Erro", "Erro ao salvar deck!");
        }
    }

    @FXML
    public void mostrarAlerta(String titulo, String mensagem){ //configura um alerta com título e mensagem personalizável
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void configurarDragDrop() {
        //define a possibilidade de drag and drop no container do deck
        containerDecks.setOnDragOver(event -> {
            if(event.getDragboard().hasString()){
                event.acceptTransferModes(javafx.scene.input.TransferMode.MOVE);
            }
            event.consume();
        });

        //adiciona a carta dropada no deck
        containerDecks.setOnDragDropped(event -> {
            String nomeCarta = event.getDragboard().getString();
            Carta carta = buscarCarta(nomeCarta);

            //verifica se a carta existe e adiciona ela no deck e atualiza a tela
            if(carta != null){
                deckAtual.adicionarCarta(carta);
                atualizarVisual();

                event.setDropCompleted(true);
            }else{
                event.setDropCompleted(false);
            }

            event.consume();
        });

        //configura a aba das cartas para drag and drop
        containerCartas.setOnDragOver(event -> {
            if(event.getDragboard().hasString()){
                event.acceptTransferModes(javafx.scene.input.TransferMode.MOVE);
            }
            event.consume();
        });

        //adiciona a carta dropada na aba de cartas disponíveis
        containerCartas.setOnDragDropped(event -> {
            String nomeCarta = event.getDragboard().getString();
            Carta carta = buscarCarta(nomeCarta);

            //se a carta existir, remove ela do deck e atualiza a tela
            if(carta != null){
                deckAtual.removeCarta(carta);
                atualizarVisual();

                event.setDropCompleted(true);
            }else{
                event.setDropCompleted(false);
            }

            event.consume();
        });
    }

    private Carta buscarCarta(String nomeCarta){ //busca a carta baseada no nome dela
        for(Carta carta : todasCartas){
            if(carta.getNome().equals(nomeCarta)){
                return carta;
            }
        }
        return null;
    }

    @FXML
    public void excluirDeckAtual(){
        //verifica se é um deck salvo que possa ser excluído
        if(deckAtual == null || deckAtual.getNomeDeck().equals("Novo Deck")){
            mostrarAlerta("Erro", "Selecione um deck salvo para excluir!");
            return;
        }

        //exibe um alerta de confirmação para excluir o deck
        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacao.setTitle("Excluir Deck");
        confirmacao.setHeaderText("Tem certeza que deseja excluir o deck " +  deckAtual.getNomeDeck() + " ?");
        confirmacao.setContentText("Essa ação não pode ser desfeita.");

        if(confirmacao.showAndWait().get() == ButtonType.OK){
            fileWriter fW = new fileWriter();
            boolean sucesso = fW.removerLinha(deckAtual.getNomeDeck(), "Decks.csv");

            if(sucesso){
                mostrarAlerta("Sucesso", "Deck removido com sucesso!");

                deckAtual = new Deck("Novo Deck");
                nomeDeckField.clear();
                carregarDecksSalvos();
                atualizarVisual();
            }else{
                mostrarAlerta("Erro", "Erro ao remover deck!");
            }
        }
    }

    @FXML
    public void trocarParaMenu(ActionEvent event) throws IOException { //troca a cena para o menu

        Parent menuRoot = FXMLLoader.load(getClass().getResource("/fxml/inicioMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(menuRoot));
        stage.show();
    }
}
