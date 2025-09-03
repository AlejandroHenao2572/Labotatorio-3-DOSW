package edu.dosw.lab.testing;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Clase que representa un deposito realizado en una cuenta bancaria.
 * Registra la informacion de la transaccion incluyendo monto, fecha y cuenta destino.
 * 
 * @author David Patacon - Laura Venegas
 * @version 1.0
 * @since 2025
 */
public class Deposito {

    private String idDeposito;
    private Date fecha;
    private BigDecimal monto;
    private CuentaBancaria cuentaBancaria;

    /**
     * Constructor para crear un nuevo deposito.
     * 
     * @param idDeposito Identificador unico del deposito 
     * @param fecha Fecha del deposito 
     * @param monto Cantidad depositada 
     * @param cuentaBancaria Cuenta destino del deposito 
     */
    public Deposito(String idDeposito, Date fecha, BigDecimal monto, CuentaBancaria cuentaBancaria) {
        this.idDeposito = idDeposito;
        this.fecha = fecha;
        this.monto = monto;
        this.cuentaBancaria = cuentaBancaria;
    }

    /**
     * Obtiene el identificador unico del deposito.
     * 
     * @return El ID del deposito
     */
    public String getIdDeposito() {
        return idDeposito;
    }

    /**
     * Establece el identificador unico del deposito.
     * 
     * @param idDeposito El nuevo ID del deposito (no null)
     */
    public void setIdDeposito(String idDeposito) {
        this.idDeposito = idDeposito;
    }

    /**
     * Obtiene la fecha del deposito.
     * 
     * @return La fecha del deposito
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del deposito.
     * 
     * @param fecha La nueva fecha del deposito (no null)
     * @throws IllegalArgumentException si la fecha es null
     */
    public void setFecha(Date fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser null");
        }
        this.fecha = fecha;
    }

    /**
     * Obtiene el monto del deposito.
     * 
     * @return El monto depositado
     */
    public BigDecimal getMonto() {
        return monto;
    }

    /**
     * Establece el monto del deposito.
     * 
     * @param monto El nuevo monto del deposito (no null, positivo)
     */
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    /**
     * Obtiene la cuenta bancaria asociada al deposito.
     * 
     * @return La cuenta bancaria destino
     */
    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    /**
     * Establece la cuenta bancaria asociada al deposito.
     * 
     * @param cuentaBancaria La nueva cuenta bancaria (no null)
     */
    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }
}