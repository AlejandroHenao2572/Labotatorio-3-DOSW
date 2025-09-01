package planningpoker;

import java.util.Arrays;
import java.util.List;

/**
 * Estrategia de votación basada en la secuencia de Fibonacci.
 */
public class FibonacciVoteStrategy implements VoteStrategy {
    private final List<Integer> fibonacci = Arrays.asList(1, 2, 3, 5, 8, 13);

    @Override
    public boolean isValidVote(int vote) {
        return fibonacci.contains(vote);
    }

    @Override
    public List<Integer> getValidOptions() {
        return fibonacci;
    }
}
