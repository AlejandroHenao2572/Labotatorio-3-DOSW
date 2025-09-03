package edu.dosw.lab.agilismo.planningpoker;

import java.util.List;

/**
 * Estrategia para definir reglas de votación.
 */
public interface VoteStrategy {
    boolean isValidVote(int vote);
    List<Integer> getValidOptions();
}
