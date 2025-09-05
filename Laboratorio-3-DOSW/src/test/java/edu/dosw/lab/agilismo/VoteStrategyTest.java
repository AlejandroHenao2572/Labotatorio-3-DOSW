package edu.dosw.lab.agilismo;

import edu.dosw.lab.agilismo.planningpoker.VoteStrategy;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VoteStrategyTest {

    static class DummyVoteStrategy implements VoteStrategy {
        @Override
        public boolean isValidVote(int vote) {
            return vote == 42;
        }

        @Override
        public List<Integer> getValidOptions() {
            return List.of(42, 99);
        }
    }

    @Test
    void shouldReturnTrueForValidVote() {
        VoteStrategy strategy = new DummyVoteStrategy();
        assertTrue(strategy.isValidVote(42));
    }

    @Test
    void shouldNotReturnTrueForInvalidVote() {
        VoteStrategy strategy = new DummyVoteStrategy();
        assertFalse(strategy.isValidVote(1));
        assertFalse(strategy.isValidVote(99));
    }

    @Test
    void shouldReturnValidOptions() {
        VoteStrategy strategy = new DummyVoteStrategy();
        List<Integer> options = strategy.getValidOptions();
        assertEquals(List.of(42, 99), options);
    }

    @Test
    void shouldNotReturnEmptyOptions() {
        VoteStrategy strategy = new DummyVoteStrategy();
        assertFalse(strategy.getValidOptions().isEmpty());
    }
}
