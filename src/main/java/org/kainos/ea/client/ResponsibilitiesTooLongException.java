package org.kainos.ea.client;

public class ResponsibilitiesTooLongException extends Throwable {
    @Override
    public String getMessage() {
        return "Responsibilities exceeds 100 characters.";
    }
}
