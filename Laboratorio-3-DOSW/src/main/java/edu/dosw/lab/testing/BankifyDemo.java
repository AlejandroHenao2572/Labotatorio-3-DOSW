package edu.dosw.lab.testing;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase demostrativa que muestra el funcionamiento del sistema Bankify
 */
public class BankifyDemo {

    public static void main(String[] args) {
        System.out.println("=== INICIANDO DEMO DE BANKIFY ===");
        
        // Inicializar Bankify
        Bankify bankify = new Bankify();
        
        // Registrar bancos
        System.out.println("\n--- Registrando bancos ---");
        Banco bancolombia = bankify.registrarBanco("01", "Bancolombia");
        System.out.println("Banco registrado: " + bancolombia.getNombre() + " (Código: " + bancolombia.getCodigo() + ")");
        
        Banco davivienda = bankify.registrarBanco("02", "Davivienda");
        System.out.println("Banco registrado: " + davivienda.getNombre() + " (Código: " + davivienda.getCodigo() + ")");
        
        Banco bbva = bankify.registrarBanco("03", "BBVA");
        System.out.println("Banco registrado: " + bbva.getNombre() + " (Código: " + bbva.getCodigo() + ")");
        
        // Crear usuarios
        System.out.println("\n--- Creando usuarios ---");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNacimiento1 = null;
        Date fechaNacimiento2 = null;
        
        try {
            fechaNacimiento1 = sdf.parse("1990-05-15");
            fechaNacimiento2 = sdf.parse("1985-11-23");
        } catch (Exception e) {
            System.err.println("Error al parsear fechas: " + e.getMessage());
            return;
        }
        
        Usuario usuario1 = new Usuario("U001", "Ana María Gómez", fechaNacimiento1, "ana.gomez@email.com");
        Usuario usuario2 = new Usuario("U002", "Carlos Rodríguez", fechaNacimiento2, "carlos.rodriguez@email.com");
        
        bankify.registrarUsuario(usuario1);
        bankify.registrarUsuario(usuario2);
        
        System.out.println("Usuario registrado: " + usuario1.getNombre());
        System.out.println("Usuario registrado: " + usuario2.getNombre());
        
        // Demostrar validación de números de cuenta
        System.out.println("\n--- Validación de números de cuenta ---");
        demoValidarCuenta(bankify, "0123456789"); // Válida (Bancolombia)
        demoValidarCuenta(bankify, "0298765432"); // Válida (Davivienda)
        demoValidarCuenta(bankify, "123456789");  // Inválida (longitud incorrecta)
        demoValidarCuenta(bankify, "05ABCD6789"); // Inválida (contiene letras)
        demoValidarCuenta(bankify, "0523456789"); // Inválida (banco no registrado)
        
        // Crear cuentas bancarias
        System.out.println("\n--- Creando cuentas bancarias ---");
        CuentaBancaria cuenta1 = bankify.crearCuenta("0123456789", usuario1);
        if (cuenta1 != null) {
            System.out.println("Cuenta creada: " + cuenta1.getNumeroCuenta() + " para " + cuenta1.getUsuario().getNombre());
        }
        
        CuentaBancaria cuenta2 = bankify.crearCuenta("0298765432", usuario2);
        if (cuenta2 != null) {
            System.out.println("Cuenta creada: " + cuenta2.getNumeroCuenta() + " para " + cuenta2.getUsuario().getNombre());
        }
        
        CuentaBancaria cuenta3 = bankify.crearCuenta("0354321098", usuario1);
        if (cuenta3 != null) {
            System.out.println("Cuenta creada: " + cuenta3.getNumeroCuenta() + " para " + cuenta3.getUsuario().getNombre());
        }
        
        // Intentar crear una cuenta inválida
        CuentaBancaria cuentaInvalida = bankify.crearCuenta("99ABCDEFGH", usuario1);
        if (cuentaInvalida == null) {
            System.out.println("Cuenta inválida no fue creada, como se esperaba");
        }
        
        // Consultar saldos iniciales
        System.out.println("\n--- Saldos iniciales ---");
        mostrarSaldo(bankify, "0123456789", usuario1.getNombre());
        mostrarSaldo(bankify, "0298765432", usuario2.getNombre());
        mostrarSaldo(bankify, "0354321098", usuario1.getNombre());
        
        // Realizar depósitos
        System.out.println("\n--- Realizando depósitos ---");
        realizarDeposito(bankify, "0123456789", new BigDecimal("1000000"));
        realizarDeposito(bankify, "0298765432", new BigDecimal("500000"));
        realizarDeposito(bankify, "0354321098", new BigDecimal("750000"));
        
        // Consultar saldos actualizados
        System.out.println("\n--- Saldos después de los depósitos ---");
        mostrarSaldo(bankify, "0123456789", usuario1.getNombre());
        mostrarSaldo(bankify, "0298765432", usuario2.getNombre());
        mostrarSaldo(bankify, "0354321098", usuario1.getNombre());
        
        // Realizar más operaciones
        System.out.println("\n--- Realizando más operaciones ---");
        realizarDeposito(bankify, "0123456789", new BigDecimal("250000"));
        realizarDeposito(bankify, "0298765432", new BigDecimal("-5000")); // Depósito inválido
        
        // Consultar saldos finales
        System.out.println("\n--- Saldos finales ---");
        mostrarSaldo(bankify, "0123456789", usuario1.getNombre());
        mostrarSaldo(bankify, "0298765432", usuario2.getNombre());
        mostrarSaldo(bankify, "0354321098", usuario1.getNombre());
        
        // Mostrar historial de depósitos
        System.out.println("\n--- Historial de depósitos de la cuenta 0123456789 ---");
        if (cuenta1 != null && cuenta1.getHistorialDepositos() != null) {
            cuenta1.getHistorialDepositos().forEach(deposito -> {
                System.out.println("ID: " + deposito.getIdDeposito() + 
                                   ", Fecha: " + sdf.format(deposito.getFecha()) + 
                                   ", Monto: $" + deposito.getMonto());
            });
        }
        
        System.out.println("\n=== FINALIZACIÓN DE DEMO DE BANKIFY ===");
    }
    
    /**
     * Método auxiliar para mostrar la validación de una cuenta
     */
    private static void demoValidarCuenta(Bankify bankify, String numeroCuenta) {
        boolean esValida = bankify.validarCuenta(numeroCuenta);
        System.out.println("Cuenta " + numeroCuenta + " es " + (esValida ? "VÁLIDA" : "INVÁLIDA"));
    }
    
    /**
     * Método auxiliar para mostrar el saldo de una cuenta
     */
    private static void mostrarSaldo(Bankify bankify, String numeroCuenta, String nombreUsuario) {
        BigDecimal saldo = bankify.consultarSaldo(numeroCuenta);
        if (saldo != null) {
            System.out.println("Saldo de la cuenta " + numeroCuenta + " (" + nombreUsuario + "): $" + saldo);
        } else {
            System.out.println("No se pudo consultar el saldo de la cuenta " + numeroCuenta);
        }
    }
    
    /**
     * Método auxiliar para realizar un depósito
     */
    private static void realizarDeposito(Bankify bankify, String numeroCuenta, BigDecimal monto) {
        boolean resultado = bankify.realizarDeposito(numeroCuenta, monto);
        if (resultado) {
            System.out.println("Depósito exitoso de $" + monto + " en la cuenta " + numeroCuenta);
        } else {
            System.out.println("No se pudo realizar el depósito de $" + monto + " en la cuenta " + numeroCuenta);
        }
    }
}