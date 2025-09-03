package edu.dosw.lab.testing;

import java.math.BigDecimal;
import java.util.List;

public class CuentaBancaria {

    private String numeroCuenta;
    private BigDecimal saldo;
    private Usuario usuario;
    private List<Deposito> historialDepositos;

    // Constructor
    public CuentaBancaria(String numeroCuenta, BigDecimal saldo, Usuario usuario) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.usuario = usuario;
    }

    // Getters y Setters
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Deposito> getHistorialDepositos() {
        return historialDepositos;
    }

    public void setHistorialDepositos(List<Deposito> historialDepositos) {
        this.historialDepositos = historialDepositos;
    }
}