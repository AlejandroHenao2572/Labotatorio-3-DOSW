package edu.dosw.lab.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones relacionadas con los bancos.
 */
public class BancoService {
    
    private final List<Banco> bancosRegistrados;
    
    /**
     * Constructor que recibe la lista de bancos registrados.
     */
    public BancoService(List<Banco> bancosRegistrados) {
        this.bancosRegistrados = bancosRegistrados;
    }
    
    /**
     * Registra un nuevo banco en el sistema.
     * 
     * @param codigo Código único del banco (debe ser de 2 dígitos)
     * @param nombre Nombre del banco
     * @return El banco registrado o null si ya existe uno con ese código
     */
    public Banco registrarBanco(String codigo, String nombre) {
        if (codigo == null || codigo.length() != 2 || nombre == null || nombre.isEmpty()) {
            return null;
        }
        
        if (bancosRegistrados.stream().anyMatch(b -> b.getCodigo().equals(codigo))) {
            return null; // Ya existe un banco con ese código
        }
        
        Banco nuevoBanco = new Banco(codigo, nombre);
        nuevoBanco.setUsuarios(new ArrayList<>());
        nuevoBanco.setCuentasUsuarios(new ArrayList<>());
        
        bancosRegistrados.add(nuevoBanco);
        return nuevoBanco;
    }
    
    /**
     * Agrega una cuenta al banco correspondiente según su código.
     * 
     * @param codigoBanco El código del banco
     * @param cuenta La cuenta a agregar
     * @return true si se agregó correctamente, false en caso contrario
     */
    public boolean agregarCuentaABanco(String codigoBanco, CuentaBancaria cuenta) {
        Optional<Banco> bancoOpt = bancosRegistrados.stream()
                .filter(b -> b.getCodigo().equals(codigoBanco))
                .findFirst();
        
        if (bancoOpt.isEmpty()) {
            return false;
        }
        
        Banco banco = bancoOpt.get();
        
        if (banco.getCuentasUsuarios() == null) {
            banco.setCuentasUsuarios(new ArrayList<>());
        }
        
        banco.getCuentasUsuarios().add(cuenta);
        
        // También agregamos el usuario si no está ya registrado en el banco
        Usuario usuario = cuenta.getUsuario();
        if (usuario != null && (banco.getUsuarios() == null || !banco.getUsuarios().contains(usuario))) {
            if (banco.getUsuarios() == null) {
                banco.setUsuarios(new ArrayList<>());
            }
            banco.getUsuarios().add(usuario);
        }
        
        return true;
    }
    
    /**
     * Obtiene un banco por su código.
     * 
     * @param codigo El código del banco a buscar
     * @return El banco encontrado o null si no existe
     */
    public Banco obtenerBancoPorCodigo(String codigo) {
        return bancosRegistrados.stream()
                .filter(b -> b.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }
}