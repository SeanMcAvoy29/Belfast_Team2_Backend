package org.kainos.ea.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kainos.ea.api.AuthService;
import org.kainos.ea.cli.Login;
import org.kainos.ea.cli.Register;
import org.kainos.ea.cli.Role;
import org.kainos.ea.client.FailedToGenerateTokenException;
import org.kainos.ea.client.FailedToLoginException;
import org.kainos.ea.client.FailedToRegisterException;
import org.kainos.ea.core.Validator;
import org.kainos.ea.db.AuthDao;

import java.lang.reflect.Method;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    private AuthDao authDao;
    private Validator validator;
    private AuthService authService;

    @BeforeEach
    public void setUp() {
        authDao = mock(AuthDao.class);
        validator = mock(Validator.class);
        authService = new AuthService(authDao, validator);
    }

    @Test
    public void testLogin() throws FailedToLoginException, FailedToGenerateTokenException {
        Login login = new Login("test@example.com", "password");
        when(validator.isValidLogin(login)).thenReturn(null);
        when(authDao.getHashedPassword(anyString())).thenReturn("hashedPassword");
        when(authDao.generateJwtToken(anyString())).thenReturn("token");

        AuthService authServiceSpy = spy(authService);
        doReturn(true).when(authServiceSpy).verifyPassword(anyString(), anyString());

        String token = authServiceSpy.login(login);

        assertEquals("token", token);
        verify(authDao, times(1)).getHashedPassword(anyString());
        verify(authDao, times(1)).generateJwtToken(anyString());
    }


    @Test
    public void testRegister() throws SQLException, FailedToRegisterException {
        Register register = new Register("test@example.com", "password", Role.Admin);
        when(validator.isValidLogin(register)).thenReturn(null);
        doNothing().when(authDao).register(anyString(), anyString(), any(Role.class));

        authService.register(register);

        verify(authDao, times(1)).register(anyString(), anyString(), any(Role.class));
    }

}

