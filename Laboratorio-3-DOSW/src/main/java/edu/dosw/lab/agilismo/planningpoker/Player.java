package edu.dosw.lab.agilismo.planningpoker;

/**
 * Clase que representa a un jugador en el juego de Planning Poker.
 * @author Laura Alejandra Venegas Piraban y David Alejandro Patacon Henao
 * @version 1.0
 */
public class Player {
    private final String name;
    private final String role;

    /**
     * Constructor de la clase Player.
     * @param name Nombre del jugador.
     * @param role Rol del jugador dentro del juego.
     */
    public Player(String name, String role) {
        this.name = name;
        this.role = role;
    }

    /**
     * Obtiene el nombre del jugador.
     * @return Nombre del jugador.
     */
    public String getName() { return name; }

    /**
     * Obtiene el rol del jugador.
     * @return Rol del jugador.
     */
    public String getRole() { return role; }
}
