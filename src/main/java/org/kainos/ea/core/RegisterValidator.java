package org.kainos.ea.core;

import org.kainos.ea.cli.Register;

public class RegisterValidator {

    public String isValidRegister(Register register) {

        if (register.getEmail().length() > 64) {
            return "Email greater than 64 characters";
        }

        if (!register.getEmail().contains("@")) {
            return "Email must contain an '@' symbol";
        }

        if (register.getPassword().length() < 8 || register.getPassword().length() > 64) {
            return "Password length not between 8 and 64 characters";
        }

        return null;
    }
}
