package org.kainos.ea.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kainos.ea.cli.Login;
import org.kainos.ea.core.LoginValidator;

import static org.junit.jupiter.api.Assertions.*;

public class LoginValidatorTest {
    private LoginValidator loginValidator;

    @BeforeEach
    public void setUp() {
        loginValidator = new LoginValidator();
    }

    @Test
    public void testIsValidLogin_EmailTooLong() {
        Login login = new Login("ssssssssssssssssssandjbfsdhjbfdhsjbfhjsdbfhjdsbfjhbsdhfjbsdhjfbsdhjfbdsjhfbsdjhbfdshjfbjhsdbf@example.com", "password123");
        String result = loginValidator.isValidLogin(login);
        assertEquals("Email greater than 64 characters", result);
    }

    @Test
    public void testIsValidLogin_EmailMissingAtSymbol() {
        Login login = new Login("noAtSymbol.com", "password123");
        String result = loginValidator.isValidLogin(login);
        assertEquals("Email must contain an '@' symbol", result);
    }

    @Test
    public void testIsValidLogin_PasswordTooShort() {
        Login login = new Login("test@example.com", "123");
        String result = loginValidator.isValidLogin(login);
        assertEquals("Password length not between 8 and 64 characters", result);
    }

    @Test
    public void testIsValidLogin_ValidLogin() {
        Login login = new Login("sucess@example.com", "password123");
        String result = loginValidator.isValidLogin(login);
        assertNull(result);
    }
}

