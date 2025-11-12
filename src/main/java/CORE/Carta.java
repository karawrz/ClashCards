package CORE;

import javafx.scene.image.Image;

public abstract class Carta {
    private String nome; //nome da carta
    private String raridade; //raridade (comum, rara, epica, lendária, campeão)
    private String velocidade; //(lenta, média, rápida, muito rápida )
    private String alvos; //(aereo, terrestre, construção, multiplos )
    private int nivel; //(de 1 a 15)
    private int custo; //(de 1 a 9)
    private int dano; // (pontos de dano)
    private int vida; //(pontos de vida)
    private double danoPS; //(pontos de dano por segundo)
    private double alcance;  //(fisico proximo, fisico medio, fisico longo, projetil(double))
    private double velocidadeImpacto; //(em segundos)
    private Image imagem; //(imagem da carta, fazer prontas ou carregar mais)

    public void Carta(String nome, String raridade, String velocidade, String alvos,
                      int nivel, int custo, int dano, int vida, double danoPS, double alcance,
                      double velocidadeImpacto, Image imagem) {
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



    public Image getImagem() {
        return imagem;
    }
    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public double getVelocidadeImpacto() {
        return velocidadeImpacto;
    }
    public void setVelocidadeImpacto(double velocidadeImpacto) {
        this.velocidadeImpacto = velocidadeImpacto;
    }

    public double getAlcance() {
        return alcance;
    }
    public void setAlcance(double alcance) {
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

    public String getAlvos() {
        return alvos;
    }
    public void setAlvos(String alvos) {
        this.alvos = alvos;
    }

    public String getVelocidade() {
        return velocidade;
    }
    public void setVelocidade(String velocidade) {
        this.velocidade = velocidade;
    }

    public String getRaridade() {
        return raridade;
    }
    public void setRaridade(String raridade) {
        this.raridade = raridade;
    }

    public enum enumRaridade{
        Comum,
        Rara,
        Epica,
        Lendaria,
        Campeao
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public enum enumVelocidade{
        Lenta,
        Media,
        Rapida,
        MuitoRapida
    }

    public enum enumAlvos{
        Aereo,
        Terrestre,
        Construção,
    }

    public enum enumAlcance{
        fisicoProximo,
        fisicoMedio,
        fisicoLongo,
        projetil
    }

}

