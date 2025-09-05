package edu.dosw.lab.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CuentaValidatorTest {

    private CuentaValidator cuentaValidator;
    private List<Banco> bancos;

    @BeforeEach
    void setUp() {
        bancos = Arrays.asList(
                new Banco("01", "Banco A"),
                new Banco("02", "Banco B"),
                new Banco("10", "Banco C")
        );
        cuentaValidator = new CuentaValidator(bancos);
    }

    // ===== Constructor =====
    @Test
    void shouldThrowExceptionWhenBancoListIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new CuentaValidator(null));
    }

    @Test
    void shouldCreateValidatorWithEmptyBancoList() {
        assertDoesNotThrow(() -> new CuentaValidator(Collections.emptyList()));
    }

    @Test
    void shouldCreateValidatorWithSingleBanco() {
        assertDoesNotThrow(() -> new CuentaValidator(Collections.singletonList(new Banco("99", "Banco X"))));
    }

    // ===== validarNumeroCuenta =====

    @Test
    void shouldValidateWhenAccountNumberIsValid() {
        assertTrue(cuentaValidator.validarNumeroCuenta("0101234567"));
    }

    @Test
    void shouldNotValidateWhenAccountNumberIsNull() {
        assertFalse(cuentaValidator.validarNumeroCuenta(null));
    }

    @Test
    void shouldNotValidateWhenAccountNumberIsEmpty() {
        assertFalse(cuentaValidator.validarNumeroCuenta(""));
    }

    @Test
    void shouldNotValidateWhenAccountNumberHasLessThanTenDigits() {
        assertFalse(cuentaValidator.validarNumeroCuenta("01234"));
    }

    @Test
    void shouldNotValidateWhenAccountNumberHasMoreThanTenDigits() {
        assertFalse(cuentaValidator.validarNumeroCuenta("010123456789"));
    }

    @Test
    void shouldNotValidateWhenAccountNumberContainsLetters() {
        assertFalse(cuentaValidator.validarNumeroCuenta("01ABC34567"));
    }

    @Test
    void shouldNotValidateWhenAccountNumberContainsSpecialCharacters() {
        assertFalse(cuentaValidator.validarNumeroCuenta("01!2345678"));
    }

    @Test
    void shouldNotValidateWhenAccountNumberContainsSpaces() {
        assertFalse(cuentaValidator.validarNumeroCuenta("01 2345678"));
    }

    @Test
    void shouldValidateWhenAccountNumberStartsWithZerosAndBancoExists() {
        assertTrue(cuentaValidator.validarNumeroCuenta("0100000000"));
    }

    @Test
    void shouldNotValidateWhenBancoCodeDoesNotExist() {
        assertFalse(cuentaValidator.validarNumeroCuenta("9912345678"));
    }

    @Test
    void shouldNotValidateWhenAccountNumberHasValidBankButExtraDigits() {
        assertFalse(cuentaValidator.validarNumeroCuenta("01012345678"));
    }

    @Test
    void shouldNotValidateWhenAccountNumberLengthIsTwoButBankNotExists() {
        CuentaValidator validator = new CuentaValidator(Collections.emptyList());
        assertFalse(validator.validarNumeroCuenta("11"));
    }

    @Test
    void shouldNotValidateWhenAccountNumberLengthIsTwoEvenIfBankExists() {
        CuentaValidator validator = new CuentaValidator(Collections.singletonList(new Banco("01", "Banco Test")));
        // validarNumeroCuenta requiere 10 dígitos -> debe ser false para "01"
        assertFalse(validator.validarNumeroCuenta("01"));
}

    // ===== validarNumeroCuentaConExcepcion =====

    @Test
    void shouldThrowExceptionWhenAccountNumberIsNull() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> cuentaValidator.validarNumeroCuentaConExcepcion(null)
        );
        assertEquals("El numero de cuenta debe tener exactamente 10 digitos", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAccountNumberIsEmpty() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> cuentaValidator.validarNumeroCuentaConExcepcion("")
        );
        assertEquals("El numero de cuenta debe tener exactamente 10 digitos", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAccountNumberTooShort() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> cuentaValidator.validarNumeroCuentaConExcepcion("12345")
        );
        assertEquals("El numero de cuenta debe tener exactamente 10 digitos", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAccountNumberTooLong() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> cuentaValidator.validarNumeroCuentaConExcepcion("010123456789")
        );
        assertEquals("El numero de cuenta debe tener exactamente 10 digitos", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenContainsLetters() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> cuentaValidator.validarNumeroCuentaConExcepcion("01AB345678")
        );
        assertEquals("El numero de cuenta debe contener solo digitos numericos", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenContainsSpecialCharacters() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> cuentaValidator.validarNumeroCuentaConExcepcion("01*2345678")
        );
        assertEquals("El numero de cuenta debe contener solo digitos numericos", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenContainsSpaces() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> cuentaValidator.validarNumeroCuentaConExcepcion("01 2345678")
        );
        assertEquals("El numero de cuenta debe contener solo digitos numericos", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenBancoDoesNotExist() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> cuentaValidator.validarNumeroCuentaConExcepcion("9912345678")
        );
        assertEquals("No existe un banco registrado con el codigo: 99", ex.getMessage());
    }

    @Test
    void shouldNotThrowExceptionWhenAccountNumberIsValid() {
        assertDoesNotThrow(() -> cuentaValidator.validarNumeroCuentaConExcepcion("0201234567"));
    }



    @Test
    void shouldThrowLengthExceptionWhenAccountNumberTooShortEvenIfBankCodeInvalid() {
        CuentaValidator validator = new CuentaValidator(Collections.emptyList());
        // validarNumeroCuentaConExcepcion comprueba la longitud primero -> lanza la excepción de longitud
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validarNumeroCuentaConExcepcion("11")
        );
        assertEquals("El numero de cuenta debe tener exactamente 10 digitos", ex.getMessage());
    }


    @Test
    void shouldThrowExceptionWhenTwoDigitsAndBankExists() {
        CuentaValidator validator = new CuentaValidator(Collections.singletonList(new Banco("01", "Banco Test")));
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validarNumeroCuentaConExcepcion("01")
        );
        assertEquals("El numero de cuenta debe tener exactamente 10 digitos", ex.getMessage());
    }

}
