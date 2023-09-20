package org.kainos.ea.core;

import org.kainos.ea.cli.Login;

public class LoginValidator {

    public String isValidLogin(Login login) {

        if (login.getEmail().length() > 64) {
            return "Email greater than 64 characters";
        }

        if (!login.getEmail().contains("@")) {
            return "Email must contain an '@' symbol";
        }

        if (login.getPassword().length() < 8 || login.getPassword().length() > 64) {
            return "Password length not between 8 and 64 characters";
        }

        return null;
    }
}
