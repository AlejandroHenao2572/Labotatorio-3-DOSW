package edu.dosw.lab.testing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Clase responsable de gestionar las operaciones con cuentas bancarias.
 * Maneja la creacion, consulta de saldo y depositos en cuentas.
 * 
 * @author David Patacon - Laurea Venegas
 * @version 1.0
 */
public class CuentaGestor {
    
    private final List<CuentaBancaria> cuentas;
    private final CuentaValidator validator;
    private final BancoService bancoService;
    
    /**
     * Constructor que inicializa el gestor con los servicios necesarios.
     * 
     * @param cuentas Lista de cuentas bancarias (no null)
     * @param validator Validador de cuentas (no null)
     * @param bancoService Servicio de bancos (no null)
     * @throws IllegalArgumentException si algun parametro es null
     */
    public CuentaGestor(List<CuentaBancaria> cuentas, CuentaValidator validator, BancoService bancoService) {
        if (cuentas == null) {
            throw new IllegalArgumentException("La lista de cuentas no puede ser null");
        }
        if (validator == null) {
            throw new IllegalArgumentException("El validator no puede ser null");
        }
        if (bancoService == null) {
            throw new IllegalArgumentException("El bancoService no puede ser null");
        }
        
        this.cuentas = cuentas;
        this.validator = validator;
        this.bancoService = bancoService;
    }
    
    /**
     * Crea una nueva cuenta bancaria con saldo inicial cero.
     * Valida el formato y unicidad del numero de cuenta.
     * 
     * @param numeroCuenta Numero de la cuenta a crear (no null)
     * @param usuario Usuario propietario de la cuenta (no null)
     * @return La cuenta creada
     * @throws IllegalArgumentException si los parametros son invalidos, el numero es invalido o ya existe
     */
    public CuentaBancaria crearCuenta(String numeroCuenta, Usuario usuario) {
        if (numeroCuenta == null) {
            throw new IllegalArgumentException("El numero de cuenta no puede ser null");
        }
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser null");
        }
        
        if (!validator.validarNumeroCuenta(numeroCuenta)) {
            throw new IllegalArgumentException("El numero de cuenta tiene formato invalido: " + numeroCuenta);
        }

        if (cuentas.stream().anyMatch(c -> c.getNumeroCuenta().equals(numeroCuenta))) {
            throw new IllegalArgumentException("Ya existe una cuenta con el numero: " + numeroCuenta);
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
     * Consulta el saldo actual de una cuenta bancaria.
     * 
     * @param numeroCuenta El numero de cuenta a consultar (no null)
     * @return El saldo de la cuenta
     * @throws IllegalArgumentException si el numero de cuenta es null o la cuenta no existe
     */
    public BigDecimal consultarSaldo(String numeroCuenta) {
        if (numeroCuenta == null) {
            throw new IllegalArgumentException("El numero de cuenta no puede ser null");
        }
        
        return buscarCuenta(numeroCuenta)
                .map(CuentaBancaria::getSaldo)
                .orElseThrow(() -> new IllegalArgumentException("No existe una cuenta con el numero: " + numeroCuenta));
    }
    
    /**
     * Realiza un deposito en una cuenta bancaria.
     * Actualiza el saldo y registra la transaccion en el historial.
     * 
     * @param numeroCuenta El numero de cuenta destino (no null)
     * @param monto El monto a depositar (positivo)
     * @return true si el deposito se realizo correctamente
     * @throws IllegalArgumentException si los parametros son invalidos o la cuenta no existe
     */
    public boolean realizarDeposito(String numeroCuenta, BigDecimal monto) {
        if (numeroCuenta == null) {
            throw new IllegalArgumentException("El numero de cuenta no puede ser null");
        }
        if (monto == null) {
            throw new IllegalArgumentException("El monto no puede ser null");
        }
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        
        Optional<CuentaBancaria> cuentaOpt = buscarCuenta(numeroCuenta);
        
        if (cuentaOpt.isEmpty()) {
            throw new IllegalArgumentException("No existe una cuenta con el numero: " + numeroCuenta);
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
     * Busca una cuenta por su numero en la lista de cuentas.
     * 
     * @param numeroCuenta Numero de cuenta a buscar
     * @return Optional con la cuenta encontrada o vacio si no existe
     */
    private Optional<CuentaBancaria> buscarCuenta(String numeroCuenta) {
        return cuentas.stream()
                .filter(c -> c.getNumeroCuenta().equals(numeroCuenta))
                .findFirst();
    }
}
