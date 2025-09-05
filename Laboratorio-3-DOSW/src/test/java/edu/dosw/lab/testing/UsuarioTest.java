package edu.dosw.lab.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para la clase Usuario.
 * 
 * @author AlejandroHenao2572
 * @version 1.0
 */
public class UsuarioTest {

    private Usuario usuario;
    private Date fechaNacimiento;

    @BeforeEach
    public void setUp() {
        fechaNacimiento = new Date();
        usuario = new Usuario("U001", "Juan Perez", fechaNacimiento, "juan@test.com");
    }

    @Test
    public void testConstructorValido() {
        assertEquals("U001", usuario.getIdUsuario());
        assertEquals("Juan Perez", usuario.getNombre());
        assertEquals(fechaNacimiento, usuario.getFechaNacimiento());
        assertEquals("juan@test.com", usuario.getCorreoElectronico());
        assertNull(usuario.getCuentas());
    }

    @Test
    public void testGetSetIdUsuario() {
        usuario.setIdUsuario("U999");
        assertEquals("U999", usuario.getIdUsuario());
    }

    @Test
    public void testSetIdUsuarioNull() {
        usuario.setIdUsuario(null);
        assertNull(usuario.getIdUsuario());
    }

    @Test
    public void testGetSetNombre() {
        usuario.setNombre("Maria Garcia");
        assertEquals("Maria Garcia", usuario.getNombre());
    }

    @Test
    public void testSetNombreNull() {
        usuario.setNombre(null);
        assertNull(usuario.getNombre());
    }

    @Test
    public void testGetSetFechaNacimiento() {
        Date nuevaFecha = new Date(System.currentTimeMillis() - 86400000); // -1 dia
        usuario.setFechaNacimiento(nuevaFecha);
        assertEquals(nuevaFecha, usuario.getFechaNacimiento());
    }

    @Test
    public void testSetFechaNacimientoNull() {
        usuario.setFechaNacimiento(null);
        assertNull(usuario.getFechaNacimiento());
    }

    @Test
    public void testGetSetCorreoElectronico() {
        usuario.setCorreoElectronico("nuevo@email.com");
        assertEquals("nuevo@email.com", usuario.getCorreoElectronico());
    }

    @Test
    public void testSetCorreoElectronicoNull() {
        usuario.setCorreoElectronico(null);
        assertNull(usuario.getCorreoElectronico());
    }

    @Test
    public void testGetSetCuentas() {
        List<CuentaBancaria> cuentas = new ArrayList<>();
        CuentaBancaria cuenta = new CuentaBancaria("0123456789", BigDecimal.ZERO, usuario);
        cuentas.add(cuenta);
        
        usuario.setCuentas(cuentas);
        assertEquals(1, usuario.getCuentas().size());
        assertTrue(usuario.getCuentas().contains(cuenta));
    }

    @Test
    public void testSetCuentasNull() {
        usuario.setCuentas(null);
        assertNull(usuario.getCuentas());
    }

    @Test
    public void testSetCuentasVacia() {
        List<CuentaBancaria> cuentasVacias = new ArrayList<>();
        usuario.setCuentas(cuentasVacias);
        assertTrue(usuario.getCuentas().isEmpty());
    }

    @Test
    public void testConstructorConParametrosNull() {
        Usuario usuarioConNulls = new Usuario(null, null, null, null);
        assertNull(usuarioConNulls.getIdUsuario());
        assertNull(usuarioConNulls.getNombre());
        assertNull(usuarioConNulls.getFechaNacimiento());
        assertNull(usuarioConNulls.getCorreoElectronico());
        assertNull(usuarioConNulls.getCuentas());
    }
}