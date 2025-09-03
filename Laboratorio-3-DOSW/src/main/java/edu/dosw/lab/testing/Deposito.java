package edu.dosw.lab.testing;

import java.math.BigDecimal;
import java.util.Date;

public class Deposito {

    private String idDeposito;
    private Date fecha;
    private BigDecimal monto;
    private CuentaBancaria cuentaBancaria;

    // Constructor
    public Deposito(String idDeposito, Date fecha, BigDecimal monto, CuentaBancaria cuentaBancaria) {
        this.idDeposito = idDeposito;
        this.fecha = fecha;
        this.monto = monto;
        this.cuentaBancaria = cuentaBancaria;
    }

    // Getters y Setters
    public String getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(String idDeposito) {
        this.idDeposito = idDeposito;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }
}