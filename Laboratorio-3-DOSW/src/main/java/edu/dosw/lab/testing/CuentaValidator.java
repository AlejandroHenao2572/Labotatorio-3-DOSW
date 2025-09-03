package edu.dosw.lab.testing;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Clase responsable de validar las cuentas bancarias según los requisitos establecidos.
 */
public class CuentaValidator {
    
    private static final int LONGITUD_CUENTA = 10;
    private static final Pattern SOLO_NUMEROS = Pattern.compile("^[0-9]+$");
    
    private final List<Banco> bancosRegistrados;
    
    /**
     * Constructor que recibe la lista de bancos registrados para validación.
     * 
     * @param bancosRegistrados Lista de bancos registrados en el sistema
     */
    public CuentaValidator(List<Banco> bancosRegistrados) {
        this.bancosRegistrados = bancosRegistrados;
    }
    
    /**
     * Valida que un número de cuenta cumpla con todos los criterios establecidos.
     * 
     * @param numeroCuenta El número de cuenta a validar
     * @return true si el número de cuenta es válido, false en caso contrario
     */
    public boolean validarNumeroCuenta(String numeroCuenta) {
        return validarLongitud(numeroCuenta) && 
               validarSoloDigitos(numeroCuenta) && 
               validarCodigoBanco(numeroCuenta);
    }
    
    /**
     * Verifica que la longitud del número de cuenta sea exactamente 10 dígitos.
     */
    private boolean validarLongitud(String numeroCuenta) {
        return numeroCuenta != null && numeroCuenta.length() == LONGITUD_CUENTA;
    }
    
    /**
     * Verifica que el número de cuenta contenga únicamente dígitos numéricos.
     */
    private boolean validarSoloDigitos(String numeroCuenta) {
        return numeroCuenta != null && SOLO_NUMEROS.matcher(numeroCuenta).matches();
    }
    
    /**
     * Verifica que los dos primeros dígitos correspondan a un banco registrado.
     */
    private boolean validarCodigoBanco(String numeroCuenta) {
        if (numeroCuenta == null || numeroCuenta.length() < 2) {
            return false;
        }
        
        String codigoBanco = numeroCuenta.substring(0, 2);
        
        return bancosRegistrados.stream()
                .anyMatch(banco -> banco.getCodigo().equals(codigoBanco));
    }
}
