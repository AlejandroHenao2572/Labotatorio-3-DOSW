package planningpoker;

import java.util.*;

/**
 * Lógica central del juego de Planning Poker.
 */
public class PlanningPokerGame {
    private final List<Player> players = new ArrayList<>();
    private final List<Story> stories = new ArrayList<>();
    private final VoteStrategy voteStrategy;
    private final Scanner scanner = new Scanner(System.in);

    public PlanningPokerGame(VoteStrategy voteStrategy) {
        this.voteStrategy = voteStrategy;
    }

    /** Registro de participantes */
    public void registerPlayers() {
        System.out.println("=== Registro de participantes ===");
        System.out.print("¿Cuántos jugadores participarán? ");
        int num = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < num; i++) {
            System.out.print("Nombre del jugador " + (i+1) + ": ");
            String name = scanner.nextLine();
            System.out.print("Rol del jugador " + (i+1) + ": ");
            String role = scanner.nextLine();
            players.add(new Player(name, role));
        }

        System.out.println("\nParticipantes registrados:");
        players.forEach(p -> System.out.println("- " + p.getName() + " (" + p.getRole() + ")"));

        System.out.print("\n¿Confirmar inicio del juego? (s/n): ");
        String confirm = scanner.nextLine();
        if (!confirm.equalsIgnoreCase("s")) {
            System.out.println("Juego cancelado.");
            System.exit(0);
        }
    }

    /** Carga las historias de usuario de Bankify */
    public void loadStories() {
        System.out.println("\n=== Historias de usuario de Bankify ===");

        stories.add(new Story("COMO cliente QUIERO crear una cuenta bancaria proporcionando un número de 10 dígitos PARA PODER empezar a usar los servicios financieros de Bankify."));
        stories.add(new Story("COMO cliente QUIERO consultar el saldo actual de mi cuenta en cualquier momento PARA PODER conocer mi disponibilidad de fondos."));
        stories.add(new Story("COMO cliente QUIERO depositar dinero en mi cuenta PARA PODER incrementar mi saldo y poder realizar futuras transacciones."));
        stories.add(new Story("COMO cliente QUIERO ser notificado si mi saldo se vuelve negativo PARA PODER tomar acciones correctivas y regularizar mi estado financiero."));

        stories.add(new Story("COMO administrador QUIERO registrar nuevos bancos en la lista de bancos autorizados y sus códigos PARA PODER ampliar nuestra oferta."));
        stories.add(new Story("COMO administrador QUIERO consultar la lista de bancos autorizados y sus códigos PARA PODER asegurar que la validación de nuevas cuentas sea precisa y esté al día."));
        stories.add(new Story("COMO administrador QUIERO que el sistema rechace automáticamente cualquier intento de depósito a una cuenta que no exista o no sea válida PARA PODER mantener la integridad de los datos y prevenir fraudes."));

        stories.add(new Story("COMO sistema QUIERO validar que cada nuevo número de cuenta tenga exactamente 10 dígitos y solo contenga números PARA PODER cumplir con las reglas de negocio y mantener la consistencia de los datos."));
        stories.add(new Story("COMO sistema QUIERO rechazar cualquier depósito que tenga un monto negativo PARA PODER garantizar la lógica y la validez de las transacciones financieras."));
        stories.add(new Story("COMO sistema QUIERO impedir una transferencia si la cuenta de origen no tiene saldo suficiente PARA PODER prevenir sobregiros no autorizados y mantener la solvencia del sistema."));

        stories.add(new Story("COMO desarrollador QUIERO que el código sea analizado automáticamente por SonarQube tras cada cambio PARA PODER identificar y corregir vulnerabilidades y problemas de calidad de manera proactiva."));
        stories.add(new Story("COMO desarrollador QUIERO generar un reporte de cobertura de código con JaCoCo después de ejecutar las pruebas PARA PODER asegurar que las funcionalidades críticas del sistema están adecuadamente cubiertas por pruebas automatizadas."));
    }

    /** Inicia el flujo del juego */
    public void start() {
        System.out.println("\n=== Iniciando Planning Poker ===");
        for (Story story : stories) {
            playStory(story);
        }
        showSummary();
    }

    /** Votación de una historia */
    private void playStory(Story story) {
        System.out.println("\nVotando: " + story.getTitle());

        while (story.getPoints() == -1) {
            Map<Player, Integer> votes = new HashMap<>();
            for (Player p : players) {
                int vote = askVote(p);
                votes.put(p, vote);
            }

            Set<Integer> uniqueVotes = new HashSet<>(votes.values());
            if (uniqueVotes.size() == 1) {
                story.setPoints(uniqueVotes.iterator().next());
                System.out.println("✅ Consenso alcanzado! Puntaje asignado: " + story.getPoints());
            } else {
                System.out.println("⚠ Votos divergentes – Discutan y vuelvan a votar.");
                votes.forEach((p, v) -> System.out.println(p.getName() + " votó: " + v));
            }
        }
    }

    /** Pide un voto válido a un jugador */
    private int askVote(Player player) {
        int vote = -1;
        while (true) {
            System.out.print(player.getName() + " (" + player.getRole() + 
                "), ingrese su voto " + voteStrategy.getValidOptions() + ": ");
            try {
                vote = Integer.parseInt(scanner.nextLine());
                if (voteStrategy.isValidVote(vote)) break;
                else System.out.println("❌ Voto inválido, intente de nuevo.");
            } catch (Exception e) {
                System.out.println("❌ Entrada inválida, intente de nuevo.");
            }
        }
        return vote;
    }

    /** Muestra el resumen final */
    private void showSummary() {
        System.out.println("\n=== Resumen Final ===");
        stories.forEach(s -> 
            System.out.println(s.getTitle() + " → " + s.getPoints() + " puntos")
        );
    }
}
