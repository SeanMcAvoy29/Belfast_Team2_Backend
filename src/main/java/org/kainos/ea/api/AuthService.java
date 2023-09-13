package org.kainos.ea.api;

import org.kainos.ea.cli.Login;
import org.kainos.ea.cli.Register;
import org.kainos.ea.client.FailedToGenerateTokenException;
import org.kainos.ea.client.FailedToLoginException;
import org.kainos.ea.client.FailedToRegisterException;
import org.kainos.ea.db.AuthDao;

import java.sql.SQLException;

public class AuthService {
    private AuthDao authDao = new AuthDao();

    public String login (Login login) throws FailedToLoginException, FailedToGenerateTokenException {
        if(authDao.validLogin(login)) {
            try{
                return authDao.generateToken((login.getUsername()));
            }catch (SQLException e){
                throw new FailedToGenerateTokenException();
            }
        }
        throw new FailedToLoginException();
    }

    public void register(Register register) throws FailedToRegisterException {

        try {
            authDao.register(register.getUsername(), register.getPassword(), register.getRole());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FailedToRegisterException();
        }
    }
}
