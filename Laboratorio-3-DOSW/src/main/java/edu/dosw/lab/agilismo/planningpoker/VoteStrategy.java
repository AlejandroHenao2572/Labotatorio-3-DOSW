package edu.dosw.lab.agilismo.planningpoker;

import java.util.List;

/**
 * Interfaz que define la estrategia de votación en el juego de Planning Poker.
 * @author Laura Alejandra Venegas Piraban y David Alejandro Patacon Henao
 * @version 1.0
 */
public interface VoteStrategy {
    /**
     * Verifica si el voto dado es válido según la estrategia de votación.
     * @param vote Valor del voto a validar.
     * @return true si el voto es válido, false en caso contrario.
     */
    boolean isValidVote(int vote);

    /**
     * Retorna la lista de opciones válidas de voto según la estrategia.
     * @return Lista de enteros representando las opciones válidas de voto.
     */
    List<Integer> getValidOptions();
}
