package edu.dosw.lab.agilismo.planningpoker;

/**
 * Representa un participante del juego (nombre y rol).
 */
public class Player {
    private final String name;
    private final String role;

    public Player(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() { return name; }
    public String getRole() { return role; }
}
