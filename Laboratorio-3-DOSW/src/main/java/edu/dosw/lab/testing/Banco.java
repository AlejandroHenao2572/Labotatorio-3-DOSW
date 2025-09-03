package edu.dosw.lab.testing;

import java.util.List;

public class Banco {

    private String codigo;
    private String nombre;
    private List<Usuario> usuarios;
    private List<CuentaBancaria> cuentas;

    // Constructor
    public Banco(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }


    public List<CuentaBancaria> getCuentasUsuarios() {
        return cuentas;
    }

    public void setCuentasUsuarios(List<CuentaBancaria> cuentas) {
        this.cuentas = cuentas;
    }

}