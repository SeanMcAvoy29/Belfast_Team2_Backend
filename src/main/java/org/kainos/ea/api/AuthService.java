package org.kainos.ea.api;

import com.password4j.Hash;
import com.password4j.Password;
import org.kainos.ea.cli.Login;
import org.kainos.ea.cli.Register;
import org.kainos.ea.client.FailedToGenerateTokenException;
import org.kainos.ea.client.FailedToLoginException;
import org.kainos.ea.client.FailedToRegisterException;
import org.kainos.ea.core.Validator;
import org.kainos.ea.db.AuthDao;

import java.sql.SQLException;

public class AuthService {

    private AuthDao authDao;
    private Validator validator;
    public AuthService(AuthDao authDao, Validator validator) {
        this.authDao = authDao;
        this.validator = validator;
    }

    public String login (Login login) throws FailedToLoginException, FailedToGenerateTokenException {

        String validationError = validator.isValidLogin(login);
        if (validationError != null) {
            throw new FailedToLoginException(validationError);
        }

       String storedHashedPassword = authDao.getHashedPassword(login.getEmail());

        if(verifyPassword(login.getPassword(), storedHashedPassword)) {
            return authDao.generateJwtToken((login.getEmail()));
        }
        throw new FailedToGenerateTokenException();
    }

    public void register(Register register) throws FailedToRegisterException, SQLException {
        String validationError = validator.isValidLogin(register);
        if (validationError != null) {
            throw new FailedToRegisterException(validationError);
        }

        String hashedPassword = hashPassword(register.getPassword());
        authDao.register(register.getEmail(), hashedPassword, register.getRole());
    }

    private String hashPassword(String password) {
        Hash hash = Password.hash(password).addRandomSalt().withArgon2();
        return hash.getResult();
    }

        public boolean verifyPassword (String password, String hashedPassword){
            return Password.check(password, hashedPassword).withArgon2();
        }
    }
