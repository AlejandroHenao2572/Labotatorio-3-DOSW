package edu.dosw.lab.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para la clase Banco.
 * 
 * @author AlejandroHenao2572
 * @version 1.0
 */
public class BancoTest {

    private Banco banco;
    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        banco = new Banco("01", "Banco Test");
        usuario = new Usuario("U001", "Usuario Test", new Date(), "usuario@test.com");
    }

    @Test
    public void testConstructorValido() {
        assertEquals("01", banco.getCodigo());
        assertEquals("Banco Test", banco.getNombre());
        assertNull(banco.getCuentas());
    }

    @Test
    public void testConstructorCodigoNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Banco(null, "Banco");
        });
        assertTrue(exception.getMessage().contains("codigo del banco no puede ser null o vacio"));
    }

    @Test
    public void testConstructorCodigoVacio() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Banco("", "Banco");
        });
        assertTrue(exception.getMessage().contains("codigo del banco no puede ser null o vacio"));
    }

    @Test
    public void testConstructorNombreNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Banco("01", null);
        });
        assertTrue(exception.getMessage().contains("nombre del banco no puede ser null o vacio"));
    }

    @Test
    public void testConstructorNombreVacio() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Banco("01", "");
        });
        assertTrue(exception.getMessage().contains("nombre del banco no puede ser null o vacio"));
    }

    @Test
    public void testGetSetCodigo() {
        banco.setCodigo("99");
        assertEquals("99", banco.getCodigo());
    }

    @Test
    public void testGetSetNombre() {
        banco.setNombre("Nuevo Banco");
        assertEquals("Nuevo Banco", banco.getNombre());
    }

    @Test
    public void testGetSetCuentas() {
        List<CuentaBancaria> cuentas = new ArrayList<>();
        CuentaBancaria cuenta = new CuentaBancaria("0123456789", BigDecimal.ZERO, usuario);
        cuentas.add(cuenta);
        
        banco.setCuentas(cuentas);
        assertEquals(1, banco.getCuentas().size());
        assertTrue(banco.getCuentas().contains(cuenta));
    }

    @Test
    public void testSetCuentasNull() {
        banco.setCuentas(null);
        assertNull(banco.getCuentas());
    }

    @Test
    public void testSetCuentasVacia() {
        List<CuentaBancaria> cuentasVacias = new ArrayList<>();
        banco.setCuentas(cuentasVacias);
        assertTrue(banco.getCuentas().isEmpty());
    }
}