package org.kainos.ea.client;

public class ResponsibilitiesTooLongException extends Throwable {
    @Override
    public String getMessage() {
        return "Responsibilities must be less than 100 characters..";
    }
}
