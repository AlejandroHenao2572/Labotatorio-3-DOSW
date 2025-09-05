package edu.dosw.lab.agilismo;

import edu.dosw.lab.agilismo.planningpoker.Story;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StoryTest {

    @Test
    void shouldCreateStoryWithTitle() {
        Story story = new Story("Historia de usuario");
        assertNotNull(story);
        assertEquals("Historia de usuario", story.getTitle());
    }

    @Test
    void shouldReturnInitialPointsAsMinusOne() {
        Story story = new Story("Historia inicial");
        assertEquals(-1, story.getPoints());
    }

    @Test
    void shouldSetAndReturnPoints() {
        Story story = new Story("Historia con puntos");
        story.setPoints(8);
        assertEquals(8, story.getPoints());
    }

    @Test
    void shouldNotReturnIncorrectTitle() {
        Story story = new Story("Historia correcta");
        assertNotEquals("Historia incorrecta", story.getTitle());
    }

    @Test
    void shouldNotReturnIncorrectPoints() {
        Story story = new Story("Historia");
        story.setPoints(5);
        assertNotEquals(3, story.getPoints());
    }
}
