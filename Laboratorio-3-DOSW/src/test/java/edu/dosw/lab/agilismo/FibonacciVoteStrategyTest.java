package edu.dosw.lab.agilismo;

import edu.dosw.lab.agilismo.planningpoker.FibonacciVoteStrategy;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FibonacciVoteStrategyTest {

    @Test
    void shouldReturnTrueForValidFibonacciVotes() {
        FibonacciVoteStrategy strategy = new FibonacciVoteStrategy();
        assertTrue(strategy.isValidVote(1));
        assertTrue(strategy.isValidVote(2));
        assertTrue(strategy.isValidVote(3));
        assertTrue(strategy.isValidVote(5));
        assertTrue(strategy.isValidVote(8));
        assertTrue(strategy.isValidVote(13));
    }

    @Test
    void shouldNotReturnTrueForInvalidVotes() {
        FibonacciVoteStrategy strategy = new FibonacciVoteStrategy();
        assertFalse(strategy.isValidVote(0));
        assertFalse(strategy.isValidVote(4));
        assertFalse(strategy.isValidVote(6));
        assertFalse(strategy.isValidVote(7));
        assertFalse(strategy.isValidVote(9));
        assertFalse(strategy.isValidVote(12));
        assertFalse(strategy.isValidVote(21));
        assertFalse(strategy.isValidVote(-1));
        assertFalse(strategy.isValidVote(100));
    }

    @Test
    void shouldReturnAllValidFibonacciOptions() {
        FibonacciVoteStrategy strategy = new FibonacciVoteStrategy();
        List<Integer> expected = List.of(1, 2, 3, 5, 8, 13);
        assertEquals(expected, strategy.getValidOptions());
    }
}
