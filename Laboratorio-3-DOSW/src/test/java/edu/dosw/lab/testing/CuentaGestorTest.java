package edu.dosw.lab.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Clase de prueba para CuentaGestor que verifica la funcionalidad
 * de gestión de cuentas bancarias: creacion, consulta de saldo y depositos.
 * 
 * @author David Patacon - Laura Venegas
 * @version 1.0
 */
public class CuentaGestorTest {

    private CuentaGestor cuentaGestor;
    private CuentaValidator validator;
    private BancoService bancoService;
    private List<CuentaBancaria> cuentas;
    private Usuario usuario;
    
    /**
     * Configura el entorno de pruebas antes de cada test.
     * Inicializa mocks y objetos necesarios.
     */
    @BeforeEach
    public void setUp() {
        // Inicializar lista de cuentas y usuario de prueba
        cuentas = new ArrayList<>();
        usuario = new Usuario("U001", "Usuario Test", new Date(), "usuario@test.com");
        
        // Crear validator y banco service
        validator = Mockito.mock(CuentaValidator.class);
        bancoService = Mockito.mock(BancoService.class);
        
        // Configurar comportamiento del validador para permitir cuentas 
        when(validator.validarNumeroCuenta("0112345678")).thenReturn(true);
        when(validator.validarNumeroCuenta("0298765432")).thenReturn(true);
        when(validator.validarNumeroCuenta("INVALIDO")).thenReturn(false);
        
        // Configurar comportamiento del banco service 
        when(bancoService.agregarCuentaABanco(anyString(), Mockito.any(CuentaBancaria.class))).thenReturn(true);

        // Inicializar el gestor de cuentas con los mocks
        cuentaGestor = new CuentaGestor(cuentas, validator, bancoService);
    }
    
 
        /**
         * Verifica la creacion exitosa de una cuenta con parametros validos.
         */
        @Test
        @DisplayName("Debe crear cuenta con numero valido")
        public void debeCrearCuentaConNumeroValido() {
            String numeroCuenta = "0112345678";
            
            CuentaBancaria cuenta = cuentaGestor.crearCuenta(numeroCuenta, usuario);
            
            assertNotNull(cuenta, "La cuenta no deberia ser null");
            assertEquals(numeroCuenta, cuenta.getNumeroCuenta(), "El numero de cuenta debe coincidir");
            assertEquals(usuario, cuenta.getUsuario(), "El usuario debe coincidir");
            assertEquals(BigDecimal.ZERO, cuenta.getSaldo(), "El saldo inicial debe ser cero");
            assertEquals(1, cuentas.size(), "La cuenta debe agregarse a la lista");
            assertNotNull(cuenta.getHistorialDepositos(), "El historial de depositos no deberia ser null");
        }
        
        /**
         * Verifica que se lance una excepcion al intentar crear una cuenta con formato invalido.
         */
        @Test
        @DisplayName("Debe lanzar excepcion cuando el numero de cuenta es invalido")
        public void debeLanzarExcepcionCuandoNumeroEsInvalido() {
            String numeroCuenta = "INVALIDO";
            
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                cuentaGestor.crearCuenta(numeroCuenta, usuario);
            });
            
            assertTrue(exception.getMessage().contains("formato invalido"));
        }
        
        /**
         * Verifica que se lance una excepcion al intentar crear una cuenta con un numero duplicado.
         */
        @Test
        @DisplayName("Debe lanzar excepcion cuando el numero de cuenta ya existe")
        public void debeLanzarExcepcionCuandoNumeroCuentaYaExiste() {
            String numeroCuenta = "0112345678";
            cuentaGestor.crearCuenta(numeroCuenta, usuario);
            
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                cuentaGestor.crearCuenta(numeroCuenta, usuario);
            });
            
            assertTrue(exception.getMessage().contains("Ya existe una cuenta"));
        }
        
        /**
         * Verifica que se lance una excepcion al intentar crear una cuenta con parametros nulos.
         */
        @Test
        @DisplayName("Debe lanzar excepcion con parametros nulos")
        public void debeLanzarExcepcionConParametrosNulos() {
            // Act & Assert - Número null
            Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
                cuentaGestor.crearCuenta(null, usuario);
            });
            
            assertTrue(exception1.getMessage().contains("numero de cuenta no puede ser null"), 
                    "Debe validar que el número no sea null");
            
            // Act & Assert - Usuario null
            Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
                cuentaGestor.crearCuenta("0112345678", null);
            });
            
            assertTrue(exception2.getMessage().contains("usuario no puede ser null"), 
                    "Debe validar que el usuario no sea null");
        }

    
    /**
     * Conjunto de pruebas relacionadas con la consulta de saldos.
     */
    @Nested
    @DisplayName("Pruebas de consulta de saldo")
    class ConsultaSaldoTests {
        
        /**
         * Configura cuentas de prueba antes de cada test.
         */
        @BeforeEach
        public void setUpCuentas() {
            // Crear una cuenta con saldo inicial
            CuentaBancaria cuenta = new CuentaBancaria("0112345678", new BigDecimal("1000.00"), usuario);
            cuentas.add(cuenta);
        }
        
        /**
         * Verifica la consulta exitosa de saldo de una cuenta existente.
         */
        @Test
        @DisplayName("Debe retornar saldo correcto para cuenta existente")
        public void debeRetornarSaldoCorrecto() {
            // Act
            BigDecimal saldo = cuentaGestor.consultarSaldo("0112345678");
            
            // Assert
            assertEquals(new BigDecimal("1000.00"), saldo, "El saldo consultado debe coincidir");
        }
        
        /**
         * Verifica que se lance una excepción al consultar saldo de una cuenta inexistente.
         */
        @Test
        @DisplayName("Debe lanzar excepción al consultar cuenta inexistente")
        public void debeLanzarExcepcionConsultarCuentaInexistente() {
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                cuentaGestor.consultarSaldo("9999999999");
            });
            
            assertTrue(exception.getMessage().contains("No existe una cuenta"), 
                    "El mensaje de error debe indicar que la cuenta no existe");
        }
        
        /**
         * Verifica que se lance una excepción al consultar saldo con número de cuenta null.
         */
        @Test
        @DisplayName("Debe lanzar excepción al consultar con número null")
        public void debeLanzarExcepcionConsultaConNumeroNull() {
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                cuentaGestor.consultarSaldo(null);
            });
            
            assertTrue(exception.getMessage().contains("numero de cuenta no puede ser null"), 
                    "Debe validar que el número no sea null");
        }
    }
    
    /**
     * Conjunto de pruebas relacionadas con la realización de depósitos.
     */
    @Nested
    @DisplayName("Pruebas de realización de depósitos")
    class RealizarDepositoTests {
        
        private CuentaBancaria cuentaPrueba;
        
        /**
         * Configura cuenta de prueba para los tests de depósito.
         */
        @BeforeEach
        public void setUpCuenta() {
            // Crear cuenta con saldo inicial
            cuentaPrueba = new CuentaBancaria("0112345678", new BigDecimal("500.00"), usuario);
            cuentaPrueba.setHistorialDepositos(new ArrayList<>());
            cuentas.add(cuentaPrueba);
        }
        
        /**
         * Verifica la realización exitosa de un depósito.
         */
        @Test
        @DisplayName("Debe realizar depósito exitosamente")
        public void debeRealizarDepositoExitoso() {
            // Arrange
            BigDecimal montoDeposito = new BigDecimal("200.50");
            BigDecimal saldoEsperado = new BigDecimal("700.50");
            
            // Act
            boolean resultado = cuentaGestor.realizarDeposito("0112345678", montoDeposito);
            
            // Assert
            assertTrue(resultado, "El depósito debe ser exitoso");
            assertEquals(saldoEsperado, cuentaPrueba.getSaldo(), "El saldo debe incrementarse correctamente");
            assertEquals(1, cuentaPrueba.getHistorialDepositos().size(), "Debe registrarse el depósito en el historial");
            assertEquals(montoDeposito, cuentaPrueba.getHistorialDepositos().get(0).getMonto(), 
                    "El monto del depósito en el historial debe ser correcto");
        }
        
        /**
         * Verifica que se lance una excepción al intentar realizar un depósito con monto negativo.
         */
        @Test
        @DisplayName("Debe lanzar excepción con monto negativo")
        public void debeLanzarExcepcionMontoNegativo() {
            // Arrange
            BigDecimal montoNegativo = new BigDecimal("-100");
            
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                cuentaGestor.realizarDeposito("0112345678", montoNegativo);
            });
            
            assertTrue(exception.getMessage().contains("monto debe ser positivo"), 
                    "Debe validar que el monto sea positivo");
            // Verificar que el saldo no cambió
            assertEquals(new BigDecimal("500.00"), cuentaPrueba.getSaldo(), "El saldo no debe cambiar");
        }
        
        /**
         * Verifica que se lance una excepción al intentar realizar un depósito en una cuenta inexistente.
         */
        @Test
        @DisplayName("Debe lanzar excepción al depositar en cuenta inexistente")
        public void debeLanzarExcepcionCuentaInexistente() {
            // Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                cuentaGestor.realizarDeposito("9999999999", new BigDecimal("100"));
            });
            
            assertTrue(exception.getMessage().contains("No existe una cuenta"), 
                    "Debe indicar que la cuenta no existe");
        }
        
        /**
         * Verifica que se lance una excepción al realizar un depósito con parámetros nulos.
         */
        @Test
        @DisplayName("Debe lanzar excepción con parámetros nulos")
        public void debeLanzarExcepcionParametrosNulos() {
            // Act & Assert - Número null
            Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
                cuentaGestor.realizarDeposito(null, new BigDecimal("100"));
            });
            
            assertTrue(exception1.getMessage().contains("numero de cuenta no puede ser null"));
            
            // Act & Assert - Monto null
            Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
                cuentaGestor.realizarDeposito("0112345678", null);
            });
            
            assertTrue(exception2.getMessage().contains("monto no puede ser null"));
        }
        
        /**
         * Verifica la creación correcta del historial de depósitos para una cuenta sin historial.
         */
        @Test
        @DisplayName("Debe crear historial si no existe al realizar depósito")
        public void debeCrearHistorialSiNoExiste() {
            // Arrange - Cuenta sin historial
            cuentaPrueba.setHistorialDepositos(null);
            
            // Act
            cuentaGestor.realizarDeposito("0112345678", new BigDecimal("100"));
            
            // Assert
            assertNotNull(cuentaPrueba.getHistorialDepositos(), "Debe crear el historial de depósitos");
            assertEquals(1, cuentaPrueba.getHistorialDepositos().size(), "Debe tener un depósito en el historial");
        }
    }
}