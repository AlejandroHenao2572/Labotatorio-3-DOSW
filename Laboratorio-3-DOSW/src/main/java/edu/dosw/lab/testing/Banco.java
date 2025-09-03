package edu.dosw.lab.testing;

import java.util.List;

/**
 * Clase que representa una entidad bancaria dentro del sistema Bankify.
 * 
 * @author David Patacon - Laura Venegas
 * @version 1.0
 */
public class Banco {

    private String codigo;
    private String nombre;
    private List<CuentaBancaria> cuentas;

    /**
     * Constructor para crear una nueva instancia de Banco.
     * 
     * @param codigo El codigo unico identificador del banco.
     * @param nombre El nombre comercial del banco..
     * @throws IllegalArgumentException si algun parametro es null o vacio
     */
    public Banco(String codigo, String nombre) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo del banco no puede ser null o vacio");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del banco no puede ser null o vacio");
        }
        
        this.codigo = codigo;
        this.nombre = nombre;
    }

    /**
     * Obtiene el codigo identificador del banco
     * 
     * @return El codigo unico del banco
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el codigo identificador del banco.
     * 
     * @param codigo El nuevo codigo del banco.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el nombredel banco.
     * 
     * @return El nombre del banco
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del banco.
     * 
     * @param nombre El nuevo nombre del banco.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la lista de cuentas bancarias asociadas al banco.
     * 
     * @return La lista de cuentas bancarias, puede ser null si no hay cuentas registradas
     */
    public List<CuentaBancaria> getCuentas() {
        return cuentas;
    }

    /**
     * Establece la lista de cuentas bancarias asociadas al banco.
     * 
     * @param cuentas La nueva lista de cuentas bancarias. Puede ser null.
     */
    public void setCuentas(List<CuentaBancaria> cuentas) {
        this.cuentas = cuentas;
    }
}