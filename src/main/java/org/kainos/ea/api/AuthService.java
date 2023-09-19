package org.kainos.ea.api;

import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Argon2;
import org.eclipse.jetty.util.security.Credential;
import org.kainos.ea.cli.Login;
import org.kainos.ea.cli.Register;
import org.kainos.ea.client.FailedToGenerateTokenException;
import org.kainos.ea.client.FailedToLoginException;
import org.kainos.ea.client.FailedToRegisterException;
import org.kainos.ea.db.AuthDao;

import javax.crypto.SecretKeyFactory;
import java.security.Security;
import java.sql.SQLException;

public class AuthService {
    private AuthDao authDao = new AuthDao();

    public String login (Login login) throws FailedToLoginException, FailedToGenerateTokenException {
        // Retrieve the hashed password from the database for the given username
        String storedHashedPassword = authDao.getHashedPassword(login.getEmail());

        // Verify the entered password with the stored hashed password
        if(verifyPassword(login.getPassword(), storedHashedPassword)) {
            try{
                return authDao.generateJwtToken((login.getEmail()));
            }catch (SQLException e){
                throw new FailedToGenerateTokenException();
            }
        }
        throw new FailedToLoginException();
    }

    public void register(Register register) throws FailedToRegisterException {

        try {
            String hashedPassword = hashPassword(register.getPassword());
            authDao.register(register.getEmail(), hashedPassword, register.getRole());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FailedToRegisterException();
        }
    }

    private String hashPassword(String password) {
        Hash hash = Password.hash(password).addRandomSalt().withArgon2();
        System.out.println(hash.getResult());
        return hash.getResult();
    }

    public boolean verifyPassword(String password, String hashedPassword) {
        return Password.check(password, hashedPassword).withArgon2();
    }
}
