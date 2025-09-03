package edu.dosw.lab.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones relacionadas con los bancos.
 * Proporciona funcionalidades para registrar bancos, agregar cuentas y consultar informacion.
 * 
 * @author David Patacon - Laurea Venegas
 * @version 1.0
 */
public class BancoService {
    
    private final List<Banco> bancosRegistrados;
    
    /**
     * Constructor que inicializa el servicio con una lista de bancos.
     * 
     * @param bancosRegistrados Lista de bancos previamente registrados
     * @throws IllegalArgumentException si la lista es null
     */
    public BancoService(List<Banco> bancosRegistrados) {
        if (bancosRegistrados == null) {
            throw new IllegalArgumentException("La lista de bancos no puede ser null");
        }
        this.bancosRegistrados = bancosRegistrados;
    }
    
    /**
     * Registra un nuevo banco en el sistema.
     * El codigo debe tener exactamente 2 caracteres y ser unico.
     * 
     * @param codigo Codigo unico del banco (2 caracteres)
     * @param nombre Nombre del banco (no vacio)
     * @return El banco registrado
     * @throws IllegalArgumentException si el codigo no tiene 2 caracteres, el nombre es vacio o el codigo ya existe
     */
    public Banco registrarBanco(String codigo, String nombre) {
        if (codigo == null || codigo.length() != 2) {
            throw new IllegalArgumentException("El codigo del banco debe tener exactamente 2 caracteres");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del banco no puede ser vacio");
        }
        
        if (bancosRegistrados.stream().anyMatch(b -> b.getCodigo().equals(codigo))) {
            throw new IllegalArgumentException("Ya existe un banco con el codigo: " + codigo); 
        }
        
        Banco nuevoBanco = new Banco(codigo, nombre);
        nuevoBanco.setCuentas(new ArrayList<>());
        
        bancosRegistrados.add(nuevoBanco);
        return nuevoBanco;
    }
    
    /**
     * Agrega una cuenta al banco correspondiente segun su codigo.
     * Si el banco no tiene lista de cuentas, se crea automaticamente.
     * 
     * @param codigoBanco El codigo del banco (no null)
     * @param cuenta La cuenta a agregar (no null)
     * @return true si se agrego correctamente, false si el banco no existe
     * @throws IllegalArgumentException si el codigo del banco o la cuenta son null
     */
    public boolean agregarCuentaABanco(String codigoBanco, CuentaBancaria cuenta) {
        if (codigoBanco == null) {
            throw new IllegalArgumentException("El codigo del banco no puede ser null");
        }
        if (cuenta == null) {
            throw new IllegalArgumentException("La cuenta no puede ser null");
        }
        
        Optional<Banco> bancoOpt = bancosRegistrados.stream()
                .filter(b -> b.getCodigo().equals(codigoBanco))
                .findFirst();
        
        if (bancoOpt.isEmpty()) {
            return false;
        }
        
        Banco banco = bancoOpt.get();
        
        if (banco.getCuentas() == null) {
            banco.setCuentas(new ArrayList<>());
        }

        banco.getCuentas().add(cuenta);

        return true;
    }
    
    /**
     * Obtiene un banco por su codigo identificador.
     * 
     * @param codigo El codigo del banco a buscar (no null)
     * @return El banco encontrado
     * @throws IllegalArgumentException si el codigo es null o el banco no existe
     */
    public Banco obtenerBancoPorCodigo(String codigo) {
        if (codigo == null) {
            throw new IllegalArgumentException("El codigo del banco no puede ser null");
        }
        
        return bancosRegistrados.stream()
                .filter(b -> b.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No existe un banco con el codigo: " + codigo));
    }

    /**
     * Obtiene la lista completa de bancos registrados.
     * 
     * @return Lista de bancos registrados
     */
    public List<Banco> obtenerTodosLosBancos() {
        return new ArrayList<>(bancosRegistrados);
    }

    /**
     * Verifica si existe un banco con el codigo especificado.
     * 
     * @param codigo El codigo del banco a verificar
     * @return true si existe un banco con ese codigo, false en caso contrario
     */
    public boolean existeBanco(String codigo) {
        if (codigo == null) {
            return false;
        }
        
        return bancosRegistrados.stream()
                .anyMatch(b -> b.getCodigo().equals(codigo));
    }
}