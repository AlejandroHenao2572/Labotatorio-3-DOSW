package edu.dosw.lab.agilismo.planningpoker;

import java.util.Arrays;
import java.util.List;
/**
 * Clase que implementa la estrategia de votación basada en la secuencia de Fibonacci.
 * @author Laura Alejandra Venegas Piraban y David Alejandro Patacon Henao
 * @version 1.0
 */

/**
 * Estrategia de votación basada en la secuencia de Fibonacci.
 */
public class FibonacciVoteStrategy implements VoteStrategy {
    private final List<Integer> fibonacci = Arrays.asList(1, 2, 3, 5, 8, 13);

    /**
     * Verifica si el voto dado es válido según la secuencia de Fibonacci.
     * @param vote El valor del voto a validar.
     * @return true si el voto está en la secuencia de Fibonacci, false en caso contrario.
     */
    @Override
    public boolean isValidVote(int vote) {
        return fibonacci.contains(vote);
    }

    /**
     * Retorna la lista de opciones válidas de voto basadas en la secuencia de Fibonacci.
     * @return Lista de enteros representando las opciones válidas de voto.
     */
    @Override
    public List<Integer> getValidOptions() {
        return fibonacci;
    }
}
