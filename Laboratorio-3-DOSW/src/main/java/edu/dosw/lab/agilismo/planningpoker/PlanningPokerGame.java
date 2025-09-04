package edu.dosw.lab.agilismo.planningpoker;

import java.util.*;

/**
 * Clase que maneja la lógica del juego Planning Poker.
 * @author Laura Alejandra Venegas Piraban y David Alejandro Patacon Henao
 * @version 1.0 
 */
public class PlanningPokerGame {
    private final List<Player> players = new ArrayList<>();
    private final List<Story> stories = new ArrayList<>();
    private final VoteStrategy voteStrategy;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructor de la clase PlanningPokerGame.
     * @param voteStrategy Estrategia de votación a utilizar en el juego.
     */
    public PlanningPokerGame(VoteStrategy voteStrategy) {
        this.voteStrategy = voteStrategy;
    }

    /**
     * Registra los participantes del juego solicitando su nombre y rol.
     * Si el usuario no confirma el inicio, el juego se cancela.
     */
    public void registerPlayers() {
        System.out.println("=== Registro de participantes ===\n");
        System.out.print("¿Cuántos jugadores participarán?:");
        int num = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < num; i++) {
            System.out.print("\nNombre del jugador " + (i+1) + ": ");
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

    /**
     * Carga las historias de usuario de Bankify en la lista de historias del juego.
     */
    public void loadStories() {
        System.out.println("\n=== Historias de usuario de Bankify ===");

        stories.add(new Story("\nCOMO cliente \nQUIERO crear una cuenta bancaria proporcionando un número de 10 dígitos \nPARA PODER empezar a usar los servicios financieros de Bankify.\n"));
        stories.add(new Story("\nCOMO cliente \nQUIERO consultar el saldo actual de mi cuenta en cualquier momento \nPARA PODER conocer mi disponibilidad de fondos.\n"));
        stories.add(new Story("\nCOMO cliente \nQUIERO depositar dinero en mi cuenta \nPARA PODER incrementar mi saldo y poder realizar futuras transacciones.\n"));
        
        stories.add(new Story("\nCOMO administrador \nQUIERO registrar nuevos bancos en la lista de bancos autorizados y sus códigos \nPARA PODER ampliar nuestra oferta.\n"));
        stories.add(new Story("\nCOMO administrador \nQUIERO consultar la lista de bancos autorizados y sus códigos \nPARA PODER asegurar que la validación de nuevas cuentas sea precisa y esté al día.\n"));
        stories.add(new Story("\nCOMO administrador \nQUIERO que el sistema rechace automáticamente cualquier intento de depósito a una cuenta que no exista o no sea válida \nPARA PODER mantener la integridad de los datos y prevenir fraudes.\n"));

        stories.add(new Story("\nCOMO sistema \nQUIERO validar que cada nuevo número de cuenta tenga exactamente 10 dígitos y solo contenga números \nPARA PODER cumplir con las reglas de negocio y mantener la consistencia de los datos.\n"));
        stories.add(new Story("\nCOMO sistema \nQUIERO rechazar cualquier depósito que tenga un monto negativo \nPARA PODER garantizar la lógica y la validez de las transacciones financieras.\n"));
        
        stories.add(new Story("\nCOMO desarrollador \nQUIERO que el código sea analizado automáticamente por SonarQube tras cada cambio \nPARA PODER identificar y corregir vulnerabilidades y problemas de calidad de manera proactiva.\n"));
        stories.add(new Story("\nCOMO desarrollador \nQUIERO generar un reporte de cobertura de código con JaCoCo después de ejecutar las pruebas \nPARA PODER asegurar que las funcionalidades críticas del sistema están adecuadamente cubiertas por pruebas automatizadas.\n"));
    }

    /**
     * Inicia el flujo principal del juego, recorriendo las historias y gestionando la votación.
     * Al finalizar, muestra el resumen de resultados.
     */
    public void start() {
        System.out.println("\n=== Iniciando Planning Poker ===");
        for (Story story : stories) {
            playStory(story);
        }
        showSummary();
    }

    /**
     * Gestiona la votación de una historia específica.
     * Solicita votos a cada jugador y verifica si hay consenso.
     * Si no hay consenso, se repite la votación tras discusión.
     * @param story Historia de usuario a votar.
     */
    private void playStory(Story story) {
        System.out.println("\nVotando: \n" + story.getTitle());

        while (story.getPoints() == -1) {
            Map<Player, Integer> votes = new HashMap<>();
            for (Player p : players) {
                int vote = askVote(p);
                votes.put(p, vote);
            }

            Set<Integer> uniqueVotes = new HashSet<>(votes.values());
            if (uniqueVotes.size() == 1) {
                story.setPoints(uniqueVotes.iterator().next());
                System.out.println("Consenso alcanzado! Puntaje asignado: " + story.getPoints());
            } else {
                System.out.println("Votos divergentes. Discutan y vuelvan a votar.");
                votes.forEach((p, v) -> System.out.println(p.getName() + " votó: " + v));
            }
        }
    }

    /**
     * Solicita a un jugador que ingrese un voto válido según la estrategia de votación.
     * Si el voto es inválido, lo solicita nuevamente.
     * @param player Jugador que debe votar.
     * @return Voto válido ingresado por el jugador.
     */
    private int askVote(Player player) {
        int vote = -1;
        while (true) {
            System.out.print(player.getName() + " (" + player.getRole() + 
                "), ingrese su voto " + voteStrategy.getValidOptions() + ": ");
            try {
                vote = Integer.parseInt(scanner.nextLine());
                if (voteStrategy.isValidVote(vote)) break;
                else System.out.println("Voto inválido, intente de nuevo.");
            } catch (Exception e) {
                System.out.println("Entrada inválida, intente de nuevo.");
            }
        }
        return vote;
    }

    /**
     * Muestra el resumen final del juego, listando cada historia y el puntaje asignado.
     */
    private void showSummary() {
        System.out.println("\n=== Resumen Final ===");
        stories.forEach(s -> 
            System.out.println(s.getTitle() + " → " + s.getPoints() + " puntos")
        );
    }
}
