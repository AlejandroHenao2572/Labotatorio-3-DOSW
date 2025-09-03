package edu.dosw.lab.testing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Clase responsable de gestionar las operaciones con cuentas bancarias.
 */
public class CuentaGestor {
    
    private final List<CuentaBancaria> cuentas;
    private final CuentaValidator validator;
    private final BancoService bancoService;
    
    /**
     * Constructor que inicializa el gestor con los servicios necesarios.
     */
    public CuentaGestor(List<CuentaBancaria> cuentas, CuentaValidator validator, BancoService bancoService) {
        this.cuentas = cuentas;
        this.validator = validator;
        this.bancoService = bancoService;
    }
    
    /**
     * Crea una nueva cuenta bancaria si el numero de cuenta es valido.
     * 
     * @param numeroCuenta Numero de la cuenta a crear
     * @param usuario Usuario propietario de la cuenta
     * @return La cuenta creada o null si el numero de cuenta es invalido
     */
    public CuentaBancaria crearCuenta(String numeroCuenta, Usuario usuario) {
        if (!validator.validarNumeroCuenta(numeroCuenta)) {
            return null;
        }
        
        // Verificar que la cuenta no existe ya
        if (cuentas.stream().anyMatch(c -> c.getNumeroCuenta().equals(numeroCuenta))) {
            return null;
        }
        
        // Crear nueva cuenta con saldo inicial cero
        CuentaBancaria nuevaCuenta = new CuentaBancaria(numeroCuenta, BigDecimal.ZERO, usuario);
        nuevaCuenta.setHistorialDepositos(new ArrayList<>());
        
        // Agregar la cuenta a la lista 
        cuentas.add(nuevaCuenta);
        
        // Agregar cuenta a la lista de cuentas del usuarioS
        if (usuario.getCuentas() == null) {
            usuario.setCuentas(new ArrayList<>());
        }
        usuario.getCuentas().add(nuevaCuenta);
        
        // Asociar la cuenta al banco correspondiente
        String codigoBanco = numeroCuenta.substring(0, 2);
        bancoService.agregarCuentaABanco(codigoBanco, nuevaCuenta);
        
        return nuevaCuenta;
    }
    
    /**
     * Consulta el saldo de una cuenta bancaria.
     * 
     * @param numeroCuenta El numero de cuenta a consultar
     * @return El saldo de la cuenta o null si la cuenta no existe
     */
    public BigDecimal consultarSaldo(String numeroCuenta) {
        return buscarCuenta(numeroCuenta)
                .map(CuentaBancaria::getSaldo)
                .orElse(null);
    }
    
    /**
     * Realiza un deposito en una cuenta bancaria.
     * 
     * @param numeroCuenta El numero de cuenta destino
     * @param monto El monto a depositar
     * @return true si el deposito se realizo correctamente, false en caso contrario
     */
    public boolean realizarDeposito(String numeroCuenta, BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        
        Optional<CuentaBancaria> cuentaOpt = buscarCuenta(numeroCuenta);
        
        if (cuentaOpt.isEmpty()) {
            return false;
        }
        
        CuentaBancaria cuenta = cuentaOpt.get();
        
        // Actualizar saldo
        BigDecimal nuevoSaldo = cuenta.getSaldo().add(monto);
        cuenta.setSaldo(nuevoSaldo);
        
        // Registrar depósito en el historial
        Deposito deposito = new Deposito(
            UUID.randomUUID().toString(),
            new Date(),
            monto,
            cuenta
        );
        
        if (cuenta.getHistorialDepositos() == null) {
            cuenta.setHistorialDepositos(new ArrayList<>());
        }
        
        cuenta.getHistorialDepositos().add(deposito);
        
        return true;
    }
    
    /**
     * Busca una cuenta por su numero
     */
    private Optional<CuentaBancaria> buscarCuenta(String numeroCuenta) {
        return cuentas.stream()
                .filter(c -> c.getNumeroCuenta().equals(numeroCuenta))
                .findFirst();
    }
}
