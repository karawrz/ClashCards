package CORE;

public class Teste {

    public static void main(String[] args) {
        System.out.println("=== Iniciando Testes do ClashCards ===\n");

        //TESTE CRIACAO DE CARTAS
        System.out.println("==== TESTE DE CRIACAO DE CARTAS ===");

        Carta c1 = new Carta("Cavaleiro", "Comum", "Media", "Terrestre", 9, 3, 160, 1200, 100.0, 1.0, 1.2, null);

        Carta c2 = new Carta("Arqueira", "Comum", "Media", "Terrestre", 9, 3, 90, 250, 80.0, 5.0, 1.0, null);

        Carta c3 = new Carta("P.E.K.K.A", "Epica", "Lenta", "Terrestre", 7, 7, 600, 3000, 300.0, 1.0, 1.8, null);

        System.out.println("Carta 1: " + c1.toString());
        System.out.println("Carta 2: " + c2.toString());


        // Teste do deck
        System.out.println("\n==== TESTE DE DECK ===");
        Deck primeiroDeck = new Deck("Log Bait");

        // Teste Vazio
        System.out.println("\n==== TESTE DE INICIALIZACAO VAZIA ===");
        System.out.println("Estado antes: " + primeiroDeck.toString());
        if (primeiroDeck.getCartasDeck().isEmpty()) {
            System.out.println("Deck iniciou vazio.");
        } else {
            System.out.println("Deck não iniciou vazio.");
        }

        // Teste do deck com cartas(3)
        System.out.println("\n==== TESTE ADICIONAR 3 CARTAS ===");
        primeiroDeck.AddCarta(c1);
        primeiroDeck.AddCarta(c2);
        primeiroDeck.AddCarta(c3);
        System.out.println("Estado: " + primeiroDeck.toString());

        // Teste com repetidas
        System.out.println("\n==== TESTE DE CARTA REPETIDA ===");
        int tamanhoAntes = primeiroDeck.getCartasDeck().size();
        primeiroDeck.AddCarta(c1);
        int tamanhoDepois = primeiroDeck.getCartasDeck().size();

        if (tamanhoAntes == tamanhoDepois) {
            System.out.println(">> SUCESSO: Carta repetida foi bloqueada.");
        } else {
            System.out.println(">> ERRO: Carta repetida foi adicionada.");
        }

        // Teste elixir medio
        System.out.println("\n==== TESTE DE ELIXIR MEDIO ===");
        double mediaEsperada = (3 + 3 + 7) / 3.0;
        double mediaCalculada = primeiroDeck.custoMedio();
        System.out.printf("\nMédia de Elixir -> Esperada: %.2f | Calculada: %.2f%n", mediaEsperada, mediaCalculada);

        // Teste cheio
        System.out.println("\n==== TESTE DE CHEIO DO DECK ===");
        for (int i = 4; i <= 8; i++) {
            Carta cExtra = new Carta("Carta " + i, "Comum", "Media", "Terrestre", 1, 2, 50, 50, 25.0, 1.0, 1.0, null);
            primeiroDeck.AddCarta(cExtra);
        }
        System.out.println(primeiroDeck.toString());

        // Teste do limite
        System.out.println("\n==== TESTE DO LIMITE DE 8 CARTAS ===");
        Carta cLimite = new Carta("Carta Extra", "Lendaria", "Media", "Terrestre", 1, 1, 10, 10, 10.0, 1.0, 1.0, null);
        primeiroDeck.AddCarta(cLimite);

        if (primeiroDeck.getCartasDeck().size() == 8) {
            System.out.println(">> SUCESSO: O deck respeitou o limite de 8 cartas.");
        } else {
            System.out.println(">> ERRO: O deck permitiu mais de 8 cartas.");
        }

        // Teste remoção
        System.out.println("\n==== TESTE REMOÇÃO DE CARTAS ===");
        System.out.println("Removendo Cavaleiro...");
        primeiroDeck.removeCarta(c1);
        System.out.println("Estado final: " + primeiroDeck.toString());

        System.out.println("\n=== Testes Finalizados ===");
    }
}
