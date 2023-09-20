package org.kainos.ea.client;

public class ResponsibilitiesTooLongException extends Throwable {
    @Override
    public String getMessage() {
        return "Responsibilities over 100 characters.";
    }
}
