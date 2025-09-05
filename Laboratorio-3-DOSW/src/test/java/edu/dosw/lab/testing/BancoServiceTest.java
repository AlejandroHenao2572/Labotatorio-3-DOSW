package edu.dosw.lab.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para BancoService que verifica la funcionalidad
 * de gestion de bancos y cuentas asociadas.
 * 
 * @author David Patacon - Laura Venegas
 * @version 1.0
 */
public class BancoServiceTest {

    private BancoService bancoService;
    private List<Banco> bancosRegistrados;
    private Usuario usuario;
    
    /**
     * Configura el entorno de pruebas antes de cada test.
     */
    @BeforeEach
    public void setUp() {
        // Inicializar lista de bancos
        bancosRegistrados = new ArrayList<>();
        
        // Crear un usuario de prueba
        usuario = new Usuario("U001", "Usuario Test", new Date(), "usuario@test.com");
        
        // Inicializar servicio de bancos
        bancoService = new BancoService(bancosRegistrados);
    }
    
    /**
     * Verifica el registro exitoso de un banco con parametros validos.
     */
    @Test
    @DisplayName("Debe registrar banco correctamente")
    public void debeRegistrarBancoCorrectamente() {
        Banco banco = bancoService.registrarBanco("01", "BANCOLOMBIA");

        assertNotNull(banco, "El banco no debe ser null");
        assertEquals("01", banco.getCodigo(), "El codigo debe coincidir");
        assertEquals("BANCOLOMBIA", banco.getNombre(), "El nombre debe coincidir");
        assertEquals(1, bancosRegistrados.size(), "Debe agregarse a la lista");
        assertNotNull(banco.getCuentas(), "La lista de cuentas no debe ser null");
        assertTrue(banco.getCuentas().isEmpty(), "La lista de cuentas debe estar vacia");
    }
    
    /**
     * Verifica que se lance una excepcion al registrar un banco con codigo invalido.
     */
    @Test
    @DisplayName("Debe lanzar excepcion con codigo invalido")
    public void debeLanzarExcepcionCodigoInvalido() {
        // Act & Assert - Codigo null
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
            bancoService.registrarBanco(null, "BANCO TEST");
        });
        
        assertTrue(exception1.getMessage().contains("exactamente 2 caracteres"), 
                "Debe validar que el codigo no sea null");
        
        // Act & Assert - Codigo con longitud incorrecta
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            bancoService.registrarBanco("1", "BANCO TEST");
        });
        
        assertTrue(exception2.getMessage().contains("exactamente 2 caracteres"), 
                "Debe validar la longitud exacta");
    }
    
    /**
     * Verifica que se lance una excepcion al registrar un banco con codigo duplicado.
     */
    @Test
    @DisplayName("Debe lanzar excepcion con codigo duplicado")
    public void debeLanzarExcepcionCodigoDuplicado() {
        // Arrange - Registrar un banco primero
        bancoService.registrarBanco("01", "BANCOLOMBIA");
        
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bancoService.registrarBanco("01", "OTRO BANCO");
        });
        
        assertTrue(exception.getMessage().contains("Ya existe un banco"));
    }
    
    /**
     * Verifica la correcta adicion de una cuenta a un banco existente.
     */
    @Test
    @DisplayName("Debe agregar cuenta a banco existente")
    public void debeAgregarCuentaABancoExistente() {
        Banco banco = bancoService.registrarBanco("01", "BANCOLOMBIA");
        CuentaBancaria cuenta = new CuentaBancaria("0112345678", BigDecimal.ZERO, usuario);
   
        boolean resultado = bancoService.agregarCuentaABanco("01", cuenta);
 
        assertTrue(resultado, "Debe retornar true si agrego la cuenta correctamente");
        assertEquals(1, banco.getCuentas().size(), "La cuenta debe agregarse a la lista del banco");
        assertSame(cuenta, banco.getCuentas().get(0), "Debe ser la misma instancia de cuenta");
    }
    
    /**
     * Verifica la creacion automatica de la lista de cuentas si es null.
     */
    @Test
    @DisplayName("Debe crear lista de cuentas si es null")
    public void debeCrearListaCuentasSiEsNull() {

        Banco banco = bancoService.registrarBanco("01", "BANCOLOMBIA");
        banco.setCuentas(null); // Forzar que la lista sea null
        CuentaBancaria cuenta = new CuentaBancaria("0112345678", BigDecimal.ZERO, usuario);
        
        boolean resultado = bancoService.agregarCuentaABanco("01", cuenta);

        assertTrue(resultado, "Debe retornar true si agrego la cuenta correctamente");
        assertNotNull(banco.getCuentas(), "Debe crear la lista de cuentas");
        assertEquals(1, banco.getCuentas().size(), "La cuenta debe agregarse a la nueva lista");
    }
    
    /**
     * Verifica que retorne false al intentar agregar una cuenta a un banco inexistente.
     */
    @Test
    @DisplayName("Debe retornar false al agregar cuenta a banco inexistente")
    public void debeRetornarFalseConBancoInexistente() {

        CuentaBancaria cuenta = new CuentaBancaria("0112345678", BigDecimal.ZERO, usuario);
        
        boolean resultado = bancoService.agregarCuentaABanco("01", cuenta);
        
        assertFalse(resultado, "Debe retornar false si el banco no existe");
    }


    /**
     * Verifica que se lance una excepcion al agrear un codigo y cuenta invalida.
     */
    @Test
    @DisplayName("Debe lanzar excepcion al agregar cuenta con codigo y cuenta invalida")
    public void debeLanzarExcepcionCodigoYCuentaInvalida() {
        
        //Codigo invalido
        CuentaBancaria cuenta = new CuentaBancaria("0112345678", BigDecimal.ZERO, usuario);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bancoService.agregarCuentaABanco(null, cuenta);
        });
    
        assertTrue(exception.getMessage().contains("El codigo del banco no puede ser null"));

        //Cuenta invalida
        CuentaBancaria cuenta2 = null;
        
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            bancoService.agregarCuentaABanco("01", cuenta2);
        });
    
        assertTrue(exception2.getMessage().contains("La cuenta no puede ser null"));
    
    }
    
    /**
     * Verifica que se lance una excepcion al obtener un banco inexistente.
     */
    @Test
    @DisplayName("Debe lanzar excepcion al obtener banco inexistente")
    public void debeLanzarExcepcionObtenerBancoInexistente() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bancoService.obtenerBancoPorCodigo("99");
        });
        
        assertTrue(exception.getMessage().contains("No existe un banco"));
    }
    
    /**
     * Verifica la correcta deteccion de existencia de bancos.
     */
    @Test
    @DisplayName("Debe verificar existencia de banco correctamente")
    public void debeVerificarExistenciaBancoCorrectamente() {
        bancoService.registrarBanco("01", "BANCOLOMBIA");

        assertTrue(bancoService.existeBanco("01"), "Debe detectar banco existente");
        assertFalse(bancoService.existeBanco("99"), "Debe detectar banco inexistente");
        assertFalse(bancoService.existeBanco(null), "Debe manejar codigo null");
    }
    
    /**
     * Verifica la correcta obtencion de la lista completa de bancos.
     */
    @Test
    @DisplayName("Debe obtener todos los bancos correctamente")
    public void debeObtenerTodosLosBancosCorrectamente() {
        bancoService.registrarBanco("01", "BANCOLOMBIA");
        bancoService.registrarBanco("02", "DAVIVIENDA");
    
        List<Banco> todos = bancoService.obtenerTodosLosBancos();
      
        assertEquals(2, todos.size(), "Debe retornar todos los bancos registrados");
    }
}