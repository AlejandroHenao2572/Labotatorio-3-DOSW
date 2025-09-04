package edu.dosw.lab.agilismo.planningpoker;

/**
 * Clase principal para iniciar el juego de Planning Poker.
 * @author Laura Alejandra Venegas Piraban y David Alejandro Patacon Henao
 * @version 1.0
 */
public class Main {
    /**
     * Método principal que inicia la ejecución del juego Planning Poker.
     * Crea la estrategia de votación, inicializa el juego, registra jugadores,
     * carga historias y comienza el juego.
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        VoteStrategy strategy = new FibonacciVoteStrategy();
        PlanningPokerGame game = new PlanningPokerGame(strategy);
        game.registerPlayers();
        game.loadStories();
        game.start();
    }
}
