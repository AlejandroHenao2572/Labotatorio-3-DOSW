package edu.dosw.lab.agilismo;

import edu.dosw.lab.agilismo.planningpoker.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void shouldCreatePlayerWithNameAndRole() {
        Player player = new Player("Juan", "Scrum Master");
        assertNotNull(player);
        assertEquals("Juan", player.getName());
        assertEquals("Scrum Master", player.getRole());
    }

    @Test
    void shouldReturnCorrectName() {
        Player player = new Player("Ana", "Developer");
        assertEquals("Ana", player.getName());
    }

    @Test
    void shouldReturnCorrectRole() {
        Player player = new Player("Luis", "Tester");
        assertEquals("Tester", player.getRole());
    }

    @Test
    void shouldNotReturnIncorrectName() {
        Player player = new Player("Pedro", "Product Owner");
        assertNotEquals("Ana", player.getName());
    }

    @Test
    void shouldNotReturnIncorrectRole() {
        Player player = new Player("Maria", "Developer");
        assertNotEquals("Scrum Master", player.getRole());
    }
}
