package edu.dosw.lab.testing;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Clase responsable de validar numeros de cuentas bancarias segun los requisitos establecidos.
 * Verifica longitud, formato numerico y existencia del banco asociado.
 * 
 * @author David Alejandro - Laurea Venegas
 * @version 1.0
 */
public class CuentaValidator {
    
    private static final int LONGITUD_CUENTA = 10;
    private static final Pattern SOLO_NUMEROS = Pattern.compile("^[0-9]+$");
    
    private final List<Banco> bancosRegistrados;
    
    /**
     * Constructor que inicializa el validador con la lista de bancos registrados.
     * 
     * @param bancosRegistrados Lista de bancos registrados en el sistema (no null)
     * @throws IllegalArgumentException si la lista de bancos es null
     */
    public CuentaValidator(List<Banco> bancosRegistrados) {
        if (bancosRegistrados == null) {
            throw new IllegalArgumentException("La lista de bancos no puede ser null");
        }
        this.bancosRegistrados = bancosRegistrados;
    }
    
    /**
     * Valida que un numero de cuenta cumpla con todos los criterios establecidos.
     * Verifica longitud (10 digitos), formato numerico y codigo de banco valido.
     * 
     * @param numeroCuenta El numero de cuenta a validar
     * @return true si el numero de cuenta es valido, false en caso contrario
     */
    public boolean validarNumeroCuenta(String numeroCuenta) {
        return validarLongitud(numeroCuenta) && 
               validarSoloDigitos(numeroCuenta) && 
               validarCodigoBanco(numeroCuenta);
    }
    
    /**
     * Verifica que la longitud del numero de cuenta sea exactamente 10 digitos.
     * 
     * @param numeroCuenta Numero de cuenta a verificar
     * @return true si tiene 10 digitos, false en caso contrario
     */
    private boolean validarLongitud(String numeroCuenta) {
        return numeroCuenta != null && numeroCuenta.length() == LONGITUD_CUENTA;
    }
    
    /**
     * Verifica que el numero de cuenta contenga unicamente digitos numericos.
     * 
     * @param numeroCuenta Numero de cuenta a verificar
     * @return true si contiene solo numeros, false en caso contrario
     */
    private boolean validarSoloDigitos(String numeroCuenta) {
        return numeroCuenta != null && SOLO_NUMEROS.matcher(numeroCuenta).matches();
    }
    
    /**
     * Verifica que los dos primeros digitos correspondan a un banco registrado.
     * 
     * @param numeroCuenta Numero de cuenta a verificar
     * @return true si el codigo de banco existe, false en caso contrario
     */
    private boolean validarCodigoBanco(String numeroCuenta) {
        if (numeroCuenta == null || numeroCuenta.length() < 2) {
            return false;
        }
        
        String codigoBanco = numeroCuenta.substring(0, 2);
        
        return bancosRegistrados.stream()
                .anyMatch(banco -> banco.getCodigo().equals(codigoBanco));
    }
    
    /**
     * Valida un numero de cuenta y lanza excepcion con detalle del error si es invalido.
     * 
     * @param numeroCuenta Numero de cuenta a validar
     * @throws IllegalArgumentException si el numero de cuenta es invalido, con detalle especifico del error
     */
    public void validarNumeroCuentaConExcepcion(String numeroCuenta) {
        if (!validarLongitud(numeroCuenta)) {
            throw new IllegalArgumentException("El numero de cuenta debe tener exactamente 10 digitos");
        }
        if (!validarSoloDigitos(numeroCuenta)) {
            throw new IllegalArgumentException("El numero de cuenta debe contener solo digitos numericos");
        }
        if (!validarCodigoBanco(numeroCuenta)) {
            String codigoBanco = numeroCuenta != null && numeroCuenta.length() >= 2 ? 
                numeroCuenta.substring(0, 2) : "codigo invalido";
            throw new IllegalArgumentException("No existe un banco registrado con el codigo: " + codigoBanco);
        }
    }
}
