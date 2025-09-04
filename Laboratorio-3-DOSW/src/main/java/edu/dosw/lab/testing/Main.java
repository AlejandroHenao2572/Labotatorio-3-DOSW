package edu.dosw.lab.testing;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Clase main de prueba para demostrar el funcionamiento del sistema Bankify.
 *
 * @author David Patacon - Laura Venegas
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("SISTEMA BANKIFY\n");
        
        Main test = new Main();
        test.ejecutarPruebas();
    }
    
    /**
     * Ejecuta todas las pruebas del sistema Bankify.
     */
    public void ejecutarPruebas() {
        // Inicializar Bankify
        Bankify bankify = new Bankify();
        
        // Ejecutar pruebas paso a paso
        probarRegistroBancos(bankify);
        probarRegistroUsuarios(bankify);
        probarValidacionCuentas(bankify);
        probarCreacionCuentas(bankify);
        probarDepositos(bankify);
        probarConsultaSaldos(bankify);

    }
    
    /**
     * Prueba el registro de bancos en el sistema.
     */
    private void probarRegistroBancos(Bankify bankify) {
        System.out.println("1. PROBANDO REGISTRO DE BANCOS: \n");
        
        // Registrar bancos validos
        Banco banco1 = bankify.registrarBanco("01", "BANCOLOMBIA");
        Banco banco2 = bankify.registrarBanco("02", "DAVIVIENDA");
        Banco banco3 = bankify.registrarBanco("03", "NUBANK");
        
        System.out.println("Banco registrado: " + banco1.getCodigo() + " - " + banco1.getNombre());
        System.out.println("Banco registrado: " + banco2.getCodigo() + " - " + banco2.getNombre());
        System.out.println("Banco registrado: " + banco3.getCodigo() + " - " + banco3.getNombre());
        
        // Verificar total de bancos
        System.out.println("Total de bancos registrados: " + bankify.getBancosRegistrados().size());
        System.out.println();
    }
    
    /**
     * Prueba el registro de usuarios en el sistema.
     */
    private void probarRegistroUsuarios(Bankify bankify) {
        System.out.println("2. PROBANDO REGISTRO DE USUARIOS: \n");
        
        // Crear usuarios de prueba
        Usuario usuario1 = new Usuario("U001", "Juan Perez", new Date(), "juan.perez@email.com");
        Usuario usuario2 = new Usuario("U002", "Maria Garcia", new Date(), "maria.garcia@email.com");
        Usuario usuario3 = new Usuario("U003", "Carlos Rodriguez", new Date(), "carlos.rodriguez@email.com");
        
        // Registrar usuarios
        bankify.registrarUsuario(usuario1);
        bankify.registrarUsuario(usuario2);
        bankify.registrarUsuario(usuario3);
        
        System.out.println("Usuario registrado: " + usuario1.getNombre());
        System.out.println("Usuario registrado: " + usuario2.getNombre());
        System.out.println("Usuario registrado: " + usuario3.getNombre());
        
        // Verificar total de usuarios
        System.out.println("Total de usuarios registrados: " + bankify.getUsuarios().size());
        System.out.println();
    }
    
    /**
     * Prueba la validacion de numeros de cuenta.
     */
    private void probarValidacionCuentas(Bankify bankify) {
        System.out.println("3. PROBANDO VALIDACION DE CUENTAS: \n");
        
        // Casos de validacion
        String[] cuentasValidas = {"0112345678", "0298765432", "0387654321"};
        String[] cuentasInvalidas = {"123456789", "01234567890", "0112345abc", "9999999999"};
        
        System.out.println("Probando cuentas validas:");
        for (String cuenta : cuentasValidas) {
            boolean esValida = bankify.validarCuenta(cuenta);
            System.out.println("  " + cuenta + " - " + (esValida ? "VALIDA" : "INVALIDA"));
        }
        
        System.out.println("\nProbando cuentas invalidas:");
        for (String cuenta : cuentasInvalidas) {
            boolean esValida = bankify.validarCuenta(cuenta);
            System.out.println("  " + cuenta + " - " + (esValida ? "VALIDA" : "INVALIDA"));
        }
        System.out.println();
    }
    
    /**
     * Prueba la creacion de cuentas bancarias.
     */
    private void probarCreacionCuentas(Bankify bankify) {
        System.out.println("4. PROBANDO CREACION DE CUENTAS: \n");

        // Obtener usuarios para asignar cuentas
        Usuario usuario1 = bankify.getUsuarios().get(0);
        Usuario usuario2 = bankify.getUsuarios().get(1);
        Usuario usuario3 = bankify.getUsuarios().get(2);
        
        // Crear cuentas validas
        CuentaBancaria cuenta1 = bankify.crearCuenta("0112345678", usuario1);
        CuentaBancaria cuenta2 = bankify.crearCuenta("0298765432", usuario2);
        CuentaBancaria cuenta3 = bankify.crearCuenta("0387654321", usuario3);
        
        System.out.println("Cuenta creada: " + cuenta1.getNumeroCuenta() + 
                          " para " + cuenta1.getUsuario().getNombre());
        System.out.println("Cuenta creada: " + cuenta2.getNumeroCuenta() + 
                          " para " + cuenta2.getUsuario().getNombre());
        System.out.println("Cuenta creada: " + cuenta3.getNumeroCuenta() + 
                          " para " + cuenta3.getUsuario().getNombre());
        
        // Verificar saldos iniciales
        System.out.println("\nSaldos iniciales:");
        System.out.println("  " + cuenta1.getNumeroCuenta() + ": $" + cuenta1.getSaldo());
        System.out.println("  " + cuenta2.getNumeroCuenta() + ": $" + cuenta2.getSaldo());
        System.out.println("  " + cuenta3.getNumeroCuenta() + ": $" + cuenta3.getSaldo());
        System.out.println();
    }
    
    /**
     * Prueba las operaciones de deposito.
     */
    private void probarDepositos(Bankify bankify) {
        System.out.println("5. PROBANDO DEPOSITOS: \n");
        
        // Realizar depositos en las cuentas
        boolean deposito1 = bankify.realizarDeposito("0112345678", new BigDecimal("1000.00"));
        boolean deposito2 = bankify.realizarDeposito("0298765432", new BigDecimal("2500.50"));
        boolean deposito3 = bankify.realizarDeposito("0387654321", new BigDecimal("500.25"));
        
        System.out.println("Deposito de $1000.00 en cuenta 0112345678: " + 
                          (deposito1 ? "EXITOSO" : "FALLIDO"));
        System.out.println("Deposito de $2500.50 en cuenta 0298765432: " +
                          (deposito2 ? "EXITOSO" : "FALLIDO"));
        System.out.println("Deposito de $500.25 en cuenta 0387654321: " +
                          (deposito3 ? "EXITOSO" : "FALLIDO"));
        
        System.out.println();
    }
    
    /**
     * Prueba la consulta de saldos.
     */
    private void probarConsultaSaldos(Bankify bankify) {
        System.out.println("6. PROBANDO CONSULTA DE SALDOS: \n");

        // Consultar saldos actuales
        BigDecimal saldo1 = bankify.consultarSaldo("0112345678");
        BigDecimal saldo2 = bankify.consultarSaldo("0298765432");
        BigDecimal saldo3 = bankify.consultarSaldo("0387654321");
        
        System.out.println("Saldos actuales:");
        System.out.println("  Cuenta 0112345678: $" + saldo1);
        System.out.println("  Cuenta 0298765432: $" + saldo2);
        System.out.println("  Cuenta 0387654321: $" + saldo3);
        
        // Calcular total
        BigDecimal totalSaldos = saldo1.add(saldo2).add(saldo3);
        System.out.println("  Total en el sistema: $" + totalSaldos);
        System.out.println();
    }
    
}
