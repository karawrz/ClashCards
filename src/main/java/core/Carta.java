package core;

public class Carta {

    //Atributos com encapsulamento

    private String nome; //nome da carta
    private Raridade raridade; //raridade (comum, rara, epica, lendária, campeão)
    private Velocidade velocidade; //(lenta, média, rápida, muito rápida )
    private Alvos alvos; //(aereo, terrestre, construção, multiplos )
    private int nivel; //(de 1 a 15)
    private int custo; //(de 1 a 9)
    private int dano; // (pontos de dano)
    private int vida; //(pontos de vida)
    private double danoPS; //(pontos de dano por segundo)
    private Alcance alcance;  //(fisico proximo, fisico medio, fisico longo, projetil)
    private double velocidadeImpacto; //(em segundos)
    private String imagem; //(imagem da carta, fazer prontas ou carregar mais)

    public Carta(){}

    //Construtor
    public Carta(String nome, Raridade raridade, Velocidade velocidade, Alvos alvos,
                 int nivel, int custo, int dano, int vida, double danoPS, Alcance alcance,
                 double velocidadeImpacto, String imagem) {
        this.nome = nome;
        this.raridade = raridade;
        this.velocidade = velocidade;
        this.alvos = alvos;
        this.nivel = nivel;
        this.custo = custo;
        this.dano = dano;
        this.vida = vida;
        this.danoPS = danoPS;
        this.alcance = alcance;
        this.velocidadeImpacto = velocidadeImpacto;
        this.imagem = imagem;
    }

    //Getters e Setters
    public String getImagem() {
        return imagem;
    }
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public double getVelocidadeImpacto() {
        return velocidadeImpacto;
    }
    public void setVelocidadeImpacto(double velocidadeImpacto) {
        this.velocidadeImpacto = velocidadeImpacto;
    }

    public Alcance getAlcance() {
        return alcance;
    }
    public void setAlcance(Alcance alcance) {
        this.alcance = alcance;
    }

    public double getDanoPS() {
        return danoPS;
    }
    public void setDanoPS(double danoPS) {
        this.danoPS = danoPS;
    }

    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDano() {
        return dano;
    }
    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getCusto() {
        return custo;
    }
    public void setCusto(int custo) {
        this.custo = custo;
    }

    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Alvos getAlvos() {
        return alvos;
    }
    public void setAlvos(Alvos alvos) {
        this.alvos = alvos;
    }

    public Velocidade getVelocidade() {
        return velocidade;
    }
    public void setVelocidade(Velocidade velocidade) {
        this.velocidade = velocidade;
    }

    public Raridade getRaridade() {
        return raridade;
    }
    public void setRaridade(Raridade raridade) {
        this.raridade = raridade;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    //toString
    @Override
    public String toString() {
        return String.format("%s (Nível %d) - Custo: %d Elixir | Alcance: %s | Raridade: %s",  nome, nivel, custo, alcance, raridade);
    }

    public String[] toCVS(){ //array de string armazenado no csv
        String[] string = new String[12];

        string[0] = this.getNome();
        string[1] = String.valueOf(this.getNivel());
        string[2] = String.valueOf(this.getCusto());
        string[3] = this.getRaridade().toString();
        string[4] = this.getImagem();
        string[5] = String.valueOf(this.getDano());
        string[6] = String.valueOf(this.getDanoPS());
        string[7] = String.valueOf(this.getVida());
        string[8] = this.getAlvos().toString();
        string[9] = this.getAlcance().toString();
        string[10] = this.getVelocidade().toString();
        string[11] = String.valueOf(this.getVelocidadeImpacto());

        return string;
    }
}

