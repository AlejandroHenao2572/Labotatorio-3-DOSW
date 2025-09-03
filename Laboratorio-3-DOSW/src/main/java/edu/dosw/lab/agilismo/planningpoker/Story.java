package edu.dosw.lab.agilismo.planningpoker;

/**
 * Representa una historia de usuario con su puntaje final.
 */
public class Story {
    private final String title;
    private int points;

    public Story(String title) {
        this.title = title;
        this.points = -1; // no asignado
    }

    public String getTitle() { return title; }
    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
}
