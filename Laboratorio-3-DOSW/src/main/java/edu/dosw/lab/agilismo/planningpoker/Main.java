package edu.dosw.lab.agilismo.planningpoker;

/**
 * Punto de entrada del programa.
 */
public class Main {
    public static void main(String[] args) {
        VoteStrategy strategy = new FibonacciVoteStrategy();
        PlanningPokerGame game = new PlanningPokerGame(strategy);
        game.registerPlayers();
        game.loadStories();
        game.start();
    }
}
