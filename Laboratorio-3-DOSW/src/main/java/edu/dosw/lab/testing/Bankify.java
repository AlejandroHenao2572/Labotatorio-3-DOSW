package edu.dosw.lab.testing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bankify {

    private List<Usuario> usuarios;
    private List<Banco> bancosRegistrados;
    private List<CuentaBancaria> cuentas;
    
    // Servicios
    private CuentaValidator cuentaValidator;
    private CuentaGestor cuentaGestor;
    private BancoService bancoService;
    
    /**
     * Constructor para inicializar Bankify con las listas vacías y los servicios necesarios.
     */
    public Bankify() {
        this.usuarios = new ArrayList<>();
        this.bancosRegistrados = new ArrayList<>();
        this.cuentas = new ArrayList<>();
        
        this.cuentaValidator = new CuentaValidator(bancosRegistrados);
        this.bancoService = new BancoService(bancosRegistrados);
        this.cuentaGestor = new CuentaGestor(cuentas, cuentaValidator, bancoService);
    }

    // Métodos de delegación a los servicios
    
    /**
     * Registra un nuevo banco en el sistema.
     */
    public Banco registrarBanco(String codigo, String nombre) {
        return bancoService.registrarBanco(codigo, nombre);
    }
    
    /**
     * Crea una nueva cuenta bancaria.
     */
    public CuentaBancaria crearCuenta(String numeroCuenta, Usuario usuario) {
        if (!usuarios.contains(usuario)) {
            usuarios.add(usuario);
        }
        return cuentaGestor.crearCuenta(numeroCuenta, usuario);
    }
    
    /**
     * Consulta el saldo de una cuenta.
     */
    public BigDecimal consultarSaldo(String numeroCuenta) {
        return cuentaGestor.consultarSaldo(numeroCuenta);
    }
    
    /**
     * Realiza un depósito en una cuenta.
     */
    public boolean realizarDeposito(String numeroCuenta, BigDecimal monto) {
        return cuentaGestor.realizarDeposito(numeroCuenta, monto);
    }
    
    /**
     * Verifica si un número de cuenta es válido.
     */
    public boolean validarCuenta(String numeroCuenta) {
        return cuentaValidator.validarNumeroCuenta(numeroCuenta);
    }
    
    /**
     * Registra un nuevo usuario en el sistema.
     */
    public void registrarUsuario(Usuario usuario) {
        if (!usuarios.contains(usuario)) {
            usuarios.add(usuario);
        }
    }

    // Getters y Setters
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Banco> getBancosRegistrados() {
        return bancosRegistrados;
    }

    public void setBancosRegistrados(List<Banco> bancosRegistrados) {
        this.bancosRegistrados = bancosRegistrados;
        // Actualizar el validator cuando se cambia la lista de bancos
        this.cuentaValidator = new CuentaValidator(bancosRegistrados);
    }
    
    public List<CuentaBancaria> getCuentas() {
        return cuentas;
    }
    
    public void setCuentas(List<CuentaBancaria> cuentas) {
        this.cuentas = cuentas;
    }
}