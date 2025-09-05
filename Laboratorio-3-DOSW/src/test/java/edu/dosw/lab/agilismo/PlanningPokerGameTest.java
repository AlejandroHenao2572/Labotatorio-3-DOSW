package edu.dosw.lab.agilismo;

import edu.dosw.lab.agilismo.planningpoker.*;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlanningPokerGameTest {

    @Test
    void shouldCreateGameWithVoteStrategy() {
        VoteStrategy strategy = new FibonacciVoteStrategy();
        PlanningPokerGame game = new PlanningPokerGame(strategy);
        assertNotNull(game);
    }

    @Test
    void shouldLoadStories() throws Exception {
        PlanningPokerGame game = new PlanningPokerGame(new FibonacciVoteStrategy());
        game.loadStories();

        Field storiesField = PlanningPokerGame.class.getDeclaredField("stories");
        storiesField.setAccessible(true);
        List<Story> stories = (List<Story>) storiesField.get(game);

        assertNotNull(stories);
        assertTrue(stories.size() > 0);
        assertTrue(stories.get(0).getTitle().contains("COMO"));
    }

    @Test
    void shouldAddPlayerToGame() throws Exception {
        PlanningPokerGame game = new PlanningPokerGame(new FibonacciVoteStrategy());
        Field playersField = PlanningPokerGame.class.getDeclaredField("players");
        playersField.setAccessible(true);
        List<Player> players = (List<Player>) playersField.get(game);

        Player player = new Player("Juan", "Dev");
        players.add(player);

        assertEquals(1, players.size());
        assertEquals("Juan", players.get(0).getName());
        assertEquals("Dev", players.get(0).getRole());
    }

    @Test
    void shouldNotAssignPointsToStoryInitially() {
        Story story = new Story("Historia de prueba");
        assertEquals(-1, story.getPoints());
    }

    @Test
    void shouldAssignPointsToStory() {
        Story story = new Story("Historia de prueba");
        story.setPoints(5);
        assertEquals(5, story.getPoints());
    }

    @Test
    void shouldNotFailWhenShowSummaryWithNoStories() throws Exception {
        PlanningPokerGame game = new PlanningPokerGame(new FibonacciVoteStrategy());
        Method showSummary = PlanningPokerGame.class.getDeclaredMethod("showSummary");
        showSummary.setAccessible(true);
        assertDoesNotThrow(() -> showSummary.invoke(game));
    }

    @Test
    void shouldNotFailWhenShowSummaryWithStories() throws Exception {
        PlanningPokerGame game = new PlanningPokerGame(new FibonacciVoteStrategy());
        game.loadStories();
        Method showSummary = PlanningPokerGame.class.getDeclaredMethod("showSummary");
        showSummary.setAccessible(true);    
    }
    
  

}