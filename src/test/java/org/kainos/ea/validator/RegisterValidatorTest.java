package org.kainos.ea.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kainos.ea.cli.Register;
import org.kainos.ea.core.Validator;

import static org.junit.jupiter.api.Assertions.*;
import static org.kainos.ea.cli.Role.Admin;
import static org.kainos.ea.cli.Role.Employee;

public class RegisterValidatorTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = new Validator();
    }

    @Test
    public void testIsValidRegister_EmailTooLong() {
        Register register = new Register("ytvgvjyvygvcfucvfcfcvhfgchfgcfhgcfghcfhghchfghchfgchfchfgcgfh@example.com", "password123", Employee);
        String result = validator.isValidLogin(register);
        assertEquals("Email greater than 64 characters", result);
    }

    @Test
    public void testIsValidRegister_EmailMissingAtSymbol() {
        Register register = new Register("noAtSymbol.com", "password123", Employee);
        String result = validator.isValidLogin(register);
        assertEquals("Email must contain an '@' symbol", result);
    }

    @Test
    public void testIsValidRegister_PasswordTooShort() {
        Register register = new Register("test@example.com", "123", Admin);
        String result = validator.isValidLogin(register);
        assertEquals("Password length not between 8 and 64 characters", result);
    }

    @Test
    public void testIsValidRegister_ValidRegister() {
        Register register = new Register("sucess@example.com", "password123", Admin);
        String result = validator.isValidLogin(register);
        assertNull(result);
    }
}
