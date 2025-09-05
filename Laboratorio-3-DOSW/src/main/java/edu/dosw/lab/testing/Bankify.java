package edu.dosw.lab.testing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal del sistema Bankify que coordina todas las operaciones bancarias.
 * Actua como facade proporcionando una interfaz simplificada para gestionar 
 * usuarios, bancos y cuentas bancarias.
 * 
 * @author David Patacon - Laurea Venegas
 * @version 1.0
 */
public class Bankify {

    private List<Usuario> usuarios;
    private List<Banco> bancosRegistrados;
    private List<CuentaBancaria> cuentas;
    
    // Servicios
    private CuentaValidator cuentaValidator;
    private CuentaGestor cuentaGestor;
    private BancoService bancoService;
    
    /**
     * Constructor para inicializar Bankify.
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
     * 
     * @param codigo Codigo unico del banco (2 caracteres)
     * @param nombre Nombre del banco (no vacio)
     * @return El banco registrado
     * @throws IllegalArgumentException si los parametros son invalidos o el codigo ya existe
     */
    public Banco registrarBanco(String codigo, String nombre) {
        return bancoService.registrarBanco(codigo, nombre);
    }
    
    /**
     * Crea una nueva cuenta bancaria y registra el usuario si no existe.
     * 
     * @param numeroCuenta Numero de cuenta (no null ni vacio)
     * @param usuario Usuario propietario de la cuenta (no null)
     * @return La cuenta bancaria creada
     * @throws IllegalArgumentException si los parametros son invalidos
     */
    public CuentaBancaria crearCuenta(String numeroCuenta, Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser null");
        }
        
        if (!usuarios.contains(usuario)) {
            usuarios.add(usuario);
        }
        return cuentaGestor.crearCuenta(numeroCuenta, usuario);
    }
    
    /**
     * Consulta el saldo actual de una cuenta bancaria.
     * 
     * @param numeroCuenta Numero de cuenta a consultar (no null)
     * @return El saldo actual de la cuenta
     * @throws IllegalArgumentException si el numero de cuenta es invalido o no existe
     */
    public BigDecimal consultarSaldo(String numeroCuenta) {
        return cuentaGestor.consultarSaldo(numeroCuenta);
    }
    
    /**
     * Realiza un deposito en una cuenta bancaria.
     * 
     * @param numeroCuenta Numero de cuenta destino (no null)
     * @param monto Cantidad a depositar (positivo)
     * @return true si el deposito fue exitoso
     * @throws IllegalArgumentException si los parametros son invalidos
     */
    public boolean realizarDeposito(String numeroCuenta, BigDecimal monto) {
        return cuentaGestor.realizarDeposito(numeroCuenta, monto);
    }
    
    /**
     * Verifica si un numero de cuenta tiene formato valido.
     * 
     * @param numeroCuenta Numero de cuenta a validar
     * @return true si el formato es valido, false en caso contrario
     */
    public boolean validarCuenta(String numeroCuenta) {
        return cuentaValidator.validarNumeroCuenta(numeroCuenta);
    }
    
    /**
     * Registra un nuevo usuario en el sistema.
     * No registra usuarios duplicados.
     * 
     * @param usuario Usuario a registrar (no null)
     * @throws IllegalArgumentException si el usuario es null
     */
    public void registrarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser null");
        }
        
        if (!usuarios.contains(usuario)) {
            usuarios.add(usuario);
        }
    }

    /**
     * Obtiene la lista de usuarios registrados.
     * 
     * @return Lista de usuarios (nunca null)
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Establece la lista de usuarios.
     * 
     * @param usuarios Nueva lista de usuarios (no null)
     * @throws IllegalArgumentException si la lista es null
     */
    public void setUsuarios(List<Usuario> usuarios) {
        if (usuarios == null) {
            throw new IllegalArgumentException("La lista de usuarios no puede ser null");
        }
        this.usuarios = usuarios;
    }

    /**
     * Obtiene la lista de bancos registrados.
     * 
     * @return Lista de bancos registrados (nunca null)
     */
    public List<Banco> getBancosRegistrados() {
        return bancosRegistrados;
    }

    /**
     * Establece la lista de bancos registrados y actualiza los servicios dependientes.
     * 
     * @param bancosRegistrados Nueva lista de bancos (no null)
     * @throws IllegalArgumentException si la lista es null
     */
    public void setBancosRegistrados(List<Banco> bancosRegistrados) {
        if (bancosRegistrados == null) {
            throw new IllegalArgumentException("La lista de bancos no puede ser null");
        }
        
        this.bancosRegistrados = bancosRegistrados;
        this.cuentaValidator = new CuentaValidator(bancosRegistrados);
        this.bancoService = new BancoService(bancosRegistrados);
    }
    
    /**
     * Obtiene la lista de cuentas bancarias.
     * 
     * @return Lista de cuentas bancarias 
     */
    public List<CuentaBancaria> getCuentas() {
        return cuentas;
    }
    
    /**
     * Establece la lista de cuentas bancarias.
     * 
     * @param cuentas Nueva lista de cuentas 
     * @throws IllegalArgumentException si la lista es null
     */
    public void setCuentas(List<CuentaBancaria> cuentas) {
        if (cuentas == null) {
            throw new IllegalArgumentException("La lista de cuentas no puede ser null");
        }
        this.cuentas = cuentas;
    }
}