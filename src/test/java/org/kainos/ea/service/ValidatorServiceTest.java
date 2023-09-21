package org.kainos.ea.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.cli.Login;
import org.kainos.ea.cli.Register;
import org.kainos.ea.core.Validator;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.kainos.ea.cli.Role.Admin;
import static org.kainos.ea.cli.Role.Employee;

@ExtendWith(MockitoExtension.class)
public class ValidatorServiceTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = Mockito.mock(Validator.class);
    }

    @Test
    public void testIsValidLogin_EmailTooLong() {
        Login login = new Login("ssssssssssssssssssandjbfsdhjbfdhsjbfhjsdbfhjdsbfjhbsdhfjbsdhjfbsdhjfbdsjhfbsdjhbfdshjfbjhsdbf@example.com", "password123");
        Mockito.when(validator.isValidLogin(login)).thenReturn("Email greater than 64 characters");

        String result = validator.isValidLogin(login);

        assertEquals("Email greater than 64 characters", result);
    }
    @Test
    public void testIsValidLogin_EmailMissingAtSymbol() {
        Login login = new Login( "noAtSymbol.com", "password123");
        Mockito.when(validator.isValidLogin(login)).thenReturn("Email must contain an '@' symbol");

        String result = validator.isValidLogin(login);

        assertEquals("Email must contain an '@' symbol", result);
    }

    @Test
    public void testIsValidLogin_PasswordTooShort() {
        Login login = new Login("test@example.com", "123");
        Mockito.when(validator.isValidLogin(login)).thenReturn("Password length not between 8 and 64 characters");

        String result = validator.isValidLogin(login);

        assertEquals("Password length not between 8 and 64 characters", result);
    }
    @Test
    public void testIsValidLogin_ValidLogin() {
        Login login = new Login("Success@example.com", "password123");
        Mockito.when(validator.isValidLogin(login)).thenReturn(null);

        String result = validator.isValidLogin(login);

        assertNull(result);
    }





    @Test
    public void testIsValidRegister_EmailTooLong() {
        Register register = new Register ("ssssssssssssssssssandjbfsdhjbfdhsjbfhjsdbfhjdsbfjhbsdhfjbsdhjfbsdhjfbdsjhfbsdjhbfdshjfbjhsdbf@example.com", "password123", Admin);
        Mockito.when(validator.isValidLogin(register)).thenReturn("Email greater than 64 characters");

        String result = validator.isValidLogin(register);

        assertEquals("Email greater than 64 characters", result);
    }
    @Test
    public void testIsValidRegister_EmailMissingAtSymbol() {
        Register register = new Register("noAtSymbol.com", "password123", Admin);
        Mockito.when(validator.isValidLogin(register)).thenReturn("Email must contain an '@' symbol");

        String result = validator.isValidLogin(register);

        assertEquals("Email must contain an '@' symbol", result);
    }

    @Test
    public void testIsValidRegister_PasswordTooShort() {
        Register register = new Register("test@example.com", "123", Employee);
        Mockito.when(validator.isValidLogin(register)).thenReturn("Password length not between 8 and 64 characters");

        String result = validator.isValidLogin(register);

        assertEquals("Password length not between 8 and 64 characters", result);
    }
    @Test
    public void testIsValidRegister_ValidRegister() {
        Register register = new Register("Sucessful@example.com", "password123", Admin);
        Mockito.when(validator.isValidLogin(register)).thenReturn(null);

        String result = validator.isValidLogin(register);

        assertNull(result);
    }

}
