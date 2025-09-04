package edu.dosw.lab.agilismo.planningpoker;

/**
 * Clase que representa una historia de usuario en el juego de Planning Poker.
 * @author Laura Alejandra Venegas Piraban y David Alejandro Patacon Henao
 * @version 1.0
 */
public class Story {
    private final String title;
    private int points;

    /**
     * Constructor de la clase Story.
     * @param title Título o descripción de la historia de usuario.
     */
    public Story(String title) {
        this.title = title;
        this.points = -1; // no asignado
    }

    /**
     * Obtiene el título o descripción de la historia de usuario.
     * @return Título de la historia.
     */
    public String getTitle() { return title; }

    /**
     * Obtiene los puntos asignados a la historia.
     * @return Puntos de la historia.
     */
    public int getPoints() { return points; }

    /**
     * Asigna los puntos a la historia.
     * @param points Puntos a asignar.
     */
    public void setPoints(int points) { this.points = points; }
}
