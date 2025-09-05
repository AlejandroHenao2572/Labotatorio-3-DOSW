package edu.dosw.lab.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para la clase Bankify.
 * Verifica la funcionalidad de gestion de cuentas, usuarios y bancos.
 *
 * @author David Patacon - Laura Venegas
 * @version 1.0
 */
public class BankifyTest {

    private Bankify bankify;
    private Usuario usuario;

    /**
     * Configura el entorno de prueba antes de cada test.
     * Inicializa una instancia de Bankify y un usuario de prueba.
     */
    @BeforeEach
    public void setUp() {
        bankify = new Bankify();
        usuario = new Usuario("U001", "Usuario Prueba", new Date(), "usuario@test.com");
        
        // Registramos un banco para las pruebas de cuentas
        bankify.registrarBanco("01", "Banco de Prueba");
    }

    /**
     * Pruebas para el constructor y estado inicial de Bankify.
     */
    @Nested
    @DisplayName("Pruebas de inicializacion")
    class InicializacionTests {
        
        @Test
        @DisplayName("Constructor debe inicializar colecciones vacias")
        public void testConstructor() {
            assertNotNull(bankify.getUsuarios());
            assertNotNull(bankify.getBancosRegistrados());
            assertNotNull(bankify.getCuentas());
            
            assertTrue(bankify.getUsuarios().isEmpty());
            assertTrue(bankify.getCuentas().isEmpty());
        }
    }

    /**
     * Pruebas para las operaciones de gestion de bancos.
     */
    @Nested
    @DisplayName("Pruebas de gestion de bancos")
    class BancoTests {
        
        @Test
        @DisplayName("Debe registrar un banco correctamente")
        public void testRegistrarBanco() {
            Banco banco = bankify.registrarBanco("02", "Nuevo Banco");
            
            assertNotNull(banco);
            assertEquals("02", banco.getCodigo());
            assertEquals("Nuevo Banco", banco.getNombre());
            assertTrue(bankify.getBancosRegistrados().contains(banco));
        }
        
        @Test
        @DisplayName("Debe lanzar excepcion al registrar banco con codigo duplicado")
        public void testRegistrarBancoDuplicado() {
            bankify.registrarBanco("03", "Banco Tres");
            
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                bankify.registrarBanco("03", "Banco Duplicado");
            });
            
            assertTrue(exception.getMessage().contains("Ya existe un banco"));
        }
        
        @Test
        @DisplayName("Debe lanzar excepcion al registrar banco con codigo invalido")
        public void testRegistrarBancoCodigoInvalido() {
            assertThrows(IllegalArgumentException.class, () -> {
                bankify.registrarBanco("123", "Banco Codigo Largo");
            });
            
            assertThrows(IllegalArgumentException.class, () -> {
                bankify.registrarBanco(null, "Banco Nulo");
            });
        }
    }

    /**
     * Pruebas para las operaciones de gestion de usuarios.
     */
    @Nested
    @DisplayName("Pruebas de gestion de usuarios")
    class UsuarioTests {
        
        @Test
        @DisplayName("Debe registrar un usuario correctamente")
        public void testRegistrarUsuario() {
            bankify.registrarUsuario(usuario);
            
            assertTrue(bankify.getUsuarios().contains(usuario));
            assertEquals(1, bankify.getUsuarios().size());
        }
        
        @Test
        @DisplayName("No debe registrar un usuario duplicado")
        public void testRegistrarUsuarioDuplicado() {
            bankify.registrarUsuario(usuario);
            bankify.registrarUsuario(usuario);
            
            assertEquals(1, bankify.getUsuarios().size());
        }
        
        @Test
        @DisplayName("Debe lanzar excepcion al registrar usuario nulo")
        public void testRegistrarUsuarioNulo() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                bankify.registrarUsuario(null);
            });
            
            assertTrue(exception.getMessage().contains("usuario no puede ser null"));
        }
        
        @Test
        @DisplayName("Debe asignar y obtener lista de usuarios")
        public void testSetGetUsuarios() {
            List<Usuario> nuevosUsuarios = new ArrayList<>();
            nuevosUsuarios.add(usuario);
            nuevosUsuarios.add(new Usuario("U002", "Usuario Dos", new Date(), "usuario2@test.com"));
            
            bankify.setUsuarios(nuevosUsuarios);
            
            assertEquals(nuevosUsuarios.size(), bankify.getUsuarios().size());
            assertEquals(nuevosUsuarios, bankify.getUsuarios());
        }
        
        @Test
        @DisplayName("Debe lanzar excepcion al asignar lista de usuarios nula")
        public void testSetUsuariosNulo() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                bankify.setUsuarios(null);
            });
            
            assertTrue(exception.getMessage().contains("lista de usuarios no puede ser null"));
        }
    }

    /**
     * Pruebas para las operaciones de gestion de cuentas bancarias.
     */
    @Nested
    @DisplayName("Pruebas de gestion de cuentas")
    class CuentaTests {
        
        @Test
        @DisplayName("Debe crear una cuenta correctamente")
        public void testCrearCuenta() {
            CuentaBancaria cuenta = bankify.crearCuenta("0123456789", usuario);
            
            assertNotNull(cuenta);
            assertEquals("0123456789", cuenta.getNumeroCuenta());
            assertEquals(usuario, cuenta.getUsuario());
            assertEquals(BigDecimal.ZERO, cuenta.getSaldo());
            assertTrue(bankify.getCuentas().contains(cuenta));
        }
        
        @Test
        @DisplayName("Debe registrar automaticamente al usuario al crear cuenta")
        public void testCrearCuentaRegistraUsuario() {
            Usuario nuevoUsuario = new Usuario("U003", "Nuevo Usuario", new Date(), "nuevo@test.com");
            CuentaBancaria cuenta = bankify.crearCuenta("0187654321", nuevoUsuario);
            
            assertTrue(bankify.getUsuarios().contains(nuevoUsuario));
            assertEquals(nuevoUsuario, cuenta.getUsuario());
        }
        
        @Test
        @DisplayName("Debe lanzar excepcion al crear cuenta con usuario nulo")
        public void testCrearCuentaUsuarioNulo() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                bankify.crearCuenta("0123456789", null);
            });
            
            assertTrue(exception.getMessage().contains("usuario no puede ser null"));
        }
        
        @Test
        @DisplayName("Debe lanzar excepcion al crear cuenta con numero invalido")
        public void testCrearCuentaNumeroInvalido() {
            assertThrows(IllegalArgumentException.class, () -> {
                bankify.crearCuenta("123", usuario);
            });
            
            assertThrows(IllegalArgumentException.class, () -> {
                bankify.crearCuenta(null, usuario);
            });
        }
        
        @Test
        @DisplayName("Debe validar numero de cuenta correctamente")
        public void testValidarCuenta() {
            assertTrue(bankify.validarCuenta("0123456789"));
            assertFalse(bankify.validarCuenta("1123456789")); // Banco no registrado
            assertFalse(bankify.validarCuenta("01234")); // Longitud incorrecta
            assertFalse(bankify.validarCuenta("01abcdefgh")); // Caracteres no numericos
        }
    }

    /**
     * Pruebas para las operaciones con saldos y depositos.
     */
    @Nested
    class OperacionesTests {
        
        private CuentaBancaria cuenta;
        
        @BeforeEach
        public void setUpCuenta() {
            cuenta = bankify.crearCuenta("0123456789", usuario);
        }
        
        @Test
        @DisplayName("Debe consultar saldo correctamente")
        public void testConsultarSaldo() {
            BigDecimal saldo = bankify.consultarSaldo("0123456789");
            
            assertNotNull(saldo);
            assertEquals(BigDecimal.ZERO, saldo);
        }
        
        @Test
        @DisplayName("Debe lanzar excepcion al consultar saldo de cuenta inexistente")
        public void testConsultarSaldoCuentaInexistente() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                bankify.consultarSaldo("9999999999");
            });
            
            assertTrue(exception.getMessage().contains("No existe una cuenta"));
        }
        
        @Test
        @DisplayName("Debe realizar deposito correctamente")
        public void testRealizarDeposito() {
            boolean resultado = bankify.realizarDeposito("0123456789", new BigDecimal("100.50"));
            
            assertTrue(resultado);
            assertEquals(new BigDecimal("100.50"), bankify.consultarSaldo("0123456789"));
        }
        
        @Test
        @DisplayName("Debe lanzar excepcion al realizar deposito con monto negativo")
        public void testRealizarDepositoMontoNegativo() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                bankify.realizarDeposito("0123456789", new BigDecimal("-50"));
            });
            
            assertTrue(exception.getMessage().contains("monto debe ser positivo"));
        }
        
        @Test
        @DisplayName("Debe lanzar excepcion al realizar deposito con monto nulo")
        public void testRealizarDepositoMontoNulo() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                bankify.realizarDeposito("0123456789", null);
            });
            
            assertTrue(exception.getMessage().contains("monto no puede ser null"));
        }
    }
    
    /**
     * Pruebas para los getters y setters de colecciones.
     */
    @Nested
    @DisplayName("Pruebas de getters y setters")
    class GettersSettersTests {
        
        @Test
        @DisplayName("Debe asignar y obtener lista de bancos")
        public void testSetGetBancosRegistrados() {
            List<Banco> nuevosBancos = new ArrayList<>();
            nuevosBancos.add(new Banco("05", "Banco Cinco"));
            nuevosBancos.add(new Banco("06", "Banco Seis"));
            
            bankify.setBancosRegistrados(nuevosBancos);
            
            assertEquals(nuevosBancos.size(), bankify.getBancosRegistrados().size());
            assertEquals(nuevosBancos, bankify.getBancosRegistrados());
        }
        
        @Test
        @DisplayName("Debe lanzar excepcion al asignar lista de bancos nula")
        public void testSetBancosRegistradosNulo() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                bankify.setBancosRegistrados(null);
            });
            
            assertTrue(exception.getMessage().contains("lista de bancos no puede ser null"));
        }
        
        @Test
        @DisplayName("Debe asignar y obtener lista de cuentas")
        public void testSetGetCuentas() {
            List<CuentaBancaria> nuevasCuentas = new ArrayList<>();
            nuevasCuentas.add(new CuentaBancaria("0111111111", BigDecimal.ZERO, usuario));
            
            bankify.setCuentas(nuevasCuentas);
            
            assertEquals(nuevasCuentas.size(), bankify.getCuentas().size());
            assertEquals(nuevasCuentas, bankify.getCuentas());
        }
        
        @Test
        @DisplayName("Debe lanzar excepcion al asignar lista de cuentas nula")
        public void testSetCuentasNulo() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                bankify.setCuentas(null);
            });
            
            assertTrue(exception.getMessage().contains("lista de cuentas no puede ser null"));
        }
    }
}