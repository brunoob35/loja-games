import java.util.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class MainCompras {
    private static final Locale PT_BR = new Locale("pt", "BR");
    private static final NumberFormat MOEDA = NumberFormat.getCurrencyInstance(PT_BR);

    public static void main(String[] args) {
        // Catalog (same list as your GUI example, with an extra title you added)
        List<Game> catalogo = criarCatalogoExemplo();

        // Budget: R$ 200,00
        BigDecimal saldoInicial = new BigDecimal("200.00");
        Cliente cliente = new Cliente("Thay", "thay@example.com", saldoInicial);

        // Buy as many games as possible (greedy by price, implemented in Cliente)
        cliente.comprarMaximo(catalogo);

        // Results
        int qtdComprados = cliente.getGamesComprados().size();
        BigDecimal totalGasto = cliente.getGamesComprados().stream()
                .map(Game::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal saldoRestante = cliente.getSaldo();

        System.out.println("===== RESULTADO =====");
        System.out.println("Saldo inicial: " + MOEDA.format(saldoInicial));
        System.out.println("Quantidade de jogos comprados: " + qtdComprados);
        System.out.println("Total gasto: " + MOEDA.format(totalGasto));
        System.out.println("Saldo restante: " + MOEDA.format(saldoRestante));
        System.out.println("\nJogos comprados:");
        cliente.getGamesComprados().forEach(g ->
                System.out.println(" - " + g.getNome() + " (" + MOEDA.format(g.getPreco()) + ")"));
    }

    private static List<Game> criarCatalogoExemplo() {
        return Arrays.asList(
            new Game("Minecraft", 89.90, "Aventura", 10),
            new Game("FIFA 2023", 199.90, "Esporte", 0),
            new Game("The Sims 4", 129.90, "Simulação", 12),
            new Game("Grand Theft Auto V", 149.90, "Ação", 18),
            new Game("Fortnite", 0.00, "Battle Royale", 12),
            new Game("Hades", 79.90, "Roguelike", 14),
            new Game("Stardew Valley", 24.99, "Simulação", 0),
            new Game("Celeste", 36.90, "Plataforma", 10),
            new Game("Among Us", 19.99, "Party", 7),
            new Game("Cyberpunk 2077", 149.99, "RPG", 18),
            new Game("Valorant", 0.00, "FPS", 14),
            new Game("Rocket League", 39.99, "Esporte", 3),
            new Game("Call of Duty", 379.00, "FPS", 17)
        );
    }
}
