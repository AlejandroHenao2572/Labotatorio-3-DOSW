package edu.dosw.lab.testing;

import java.util.List;

public class Bankify {

    private List<Usuario> usuarios;
    private List<Banco> bancosRegistrados;

    // Getters y Setters
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Banco> getBancosRegistrados() {
        return bancosRegistrados;
    }

    public void setBancosRegistrados(List<Banco> bancosRegistrados) {
        this.bancosRegistrados = bancosRegistrados;
    }
}