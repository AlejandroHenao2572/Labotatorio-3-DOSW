package edu.dosw.lab.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para la clase Deposito.
 * 
 * @author AlejandroHenao2572
 * @version 1.0
 */
public class DepositoTest {

    private Deposito deposito;
    private CuentaBancaria cuenta;
    private Usuario usuario;
    private Date fecha;

    @BeforeEach
    public void setUp() {
        fecha = new Date();
        usuario = new Usuario("U001", "Usuario Test", new Date(), "usuario@test.com");
        cuenta = new CuentaBancaria("0123456789", BigDecimal.ZERO, usuario);
        deposito = new Deposito("DEP001", fecha, new BigDecimal("100.00"), cuenta);
    }

    @Test
    public void testConstructorValido() {
        assertEquals("DEP001", deposito.getIdDeposito());
        assertEquals(fecha, deposito.getFecha());
        assertEquals(new BigDecimal("100.00"), deposito.getMonto());
        assertEquals(cuenta, deposito.getCuentaBancaria());
    }

    @Test
    public void testGetSetIdDeposito() {
        deposito.setIdDeposito("DEP999");
        assertEquals("DEP999", deposito.getIdDeposito());
    }

    @Test
    public void testSetIdDepositoNull() {
        deposito.setIdDeposito(null);
        assertNull(deposito.getIdDeposito());
    }

    @Test
    public void testGetSetFecha() {
        Date nuevaFecha = new Date(System.currentTimeMillis() + 86400000); // +1 dia
        deposito.setFecha(nuevaFecha);
        assertEquals(nuevaFecha, deposito.getFecha());
    }

    @Test
    public void testSetFechaNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deposito.setFecha(null);
        });
        assertTrue(exception.getMessage().contains("La fecha no puede ser null"));
    }

    @Test
    public void testGetSetMonto() {
        BigDecimal nuevoMonto = new BigDecimal("250.75");
        deposito.setMonto(nuevoMonto);
        assertEquals(nuevoMonto, deposito.getMonto());
    }

    @Test
    public void testSetMontoNull() {
        deposito.setMonto(null);
        assertNull(deposito.getMonto());
    }

    @Test
    public void testGetSetCuentaBancaria() {
        CuentaBancaria nuevaCuenta = new CuentaBancaria("0987654321", new BigDecimal("500.00"), usuario);
        deposito.setCuentaBancaria(nuevaCuenta);
        assertEquals(nuevaCuenta, deposito.getCuentaBancaria());
    }

    @Test
    public void testSetCuentaBancariaNull() {
        deposito.setCuentaBancaria(null);
        assertNull(deposito.getCuentaBancaria());
    }

    @Test
    public void testConstructorConParametrosNull() {
        Deposito depositoConNulls = new Deposito(null, null, null, null);
        assertNull(depositoConNulls.getIdDeposito());
        assertNull(depositoConNulls.getFecha());
        assertNull(depositoConNulls.getMonto());
        assertNull(depositoConNulls.getCuentaBancaria());
    }
}