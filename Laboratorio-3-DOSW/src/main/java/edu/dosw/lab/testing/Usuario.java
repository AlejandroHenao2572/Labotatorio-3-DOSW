package edu.dosw.lab.testing;

import java.util.Date;
import java.util.List;

/**
 * Clase que representa un usuario del sistema Bankify.
 * Contiene la informacion personal del usuario y sus cuentas bancarias asociadas.
 * 
 * @author David Patacon - Laura Venegas
 * @version 1.0
 */
public class Usuario {

    private String idUsuario;
    private String nombre;
    private Date fechaNacimiento;
    private String correoElectronico;
    private List<CuentaBancaria> cuentas;

    /**
     * Constructor para crear un nuevo usuario.
     * 
     * @param idUsuario Identificador unico del usuario
     * @param nombre Nombre completo del usuario
     * @param fechaNacimiento Fecha de nacimiento
     * @param correoElectronico Correo electronico del usuario
     */
    public Usuario(String idUsuario, String nombre, Date fechaNacimiento, String correoElectronico) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
    }

    /**
     * Obtiene el identificador unico del usuario.
     * 
     * @return El ID del usuario
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el identificador unico del usuario.
     * 
     * @param idUsuario El nuevo ID del usuario
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el nombre completo del usuario.
     * 
     * @return El nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre completo del usuario.
     * 
     * @param nombre El nuevo nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la fecha de nacimiento del usuario.
     * 
     * @return La fecha de nacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del usuario.
     * 
     * @param fechaNacimiento La nueva fecha de nacimiento
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene el correo electronico del usuario.
     * 
     * @return El correo electronico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Establece el correo electronico del usuario.
     * 
     * @param correoElectronico El nuevo correo electronico
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Obtiene la lista de cuentas bancarias del usuario.
     * 
     * @return Lista de cuentas bancarias, puede ser null
     */
    public List<CuentaBancaria> getCuentas() {
        return cuentas;
    }

    /**
     * Establece la lista de cuentas bancarias del usuario.
     * 
     * @param cuentas La nueva lista de cuentas bancarias
     */
    public void setCuentas(List<CuentaBancaria> cuentas) {
        this.cuentas = cuentas;
    }
}