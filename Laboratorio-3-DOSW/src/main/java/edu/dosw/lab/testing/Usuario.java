package edu.dosw.lab.testing;

import java.util.Date;
import java.util.List;

public class Usuario {

    private String idUsuario;
    private String nombre;
    private Date fechaNacimiento;
    private String correoElectronico;
    private List<CuentaBancaria> cuentas;

    // Constructor
    public Usuario(String idUsuario, String nombre, Date fechaNacimiento, String correoElectronico) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
    }

    // Getters y Setters
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public List<CuentaBancaria> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<CuentaBancaria> cuentas) {
        this.cuentas = cuentas;
    }
}