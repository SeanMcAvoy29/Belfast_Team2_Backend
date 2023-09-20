package org.kainos.ea.client;

public class BandNameTooLongException extends Throwable {
    @Override
    public String getMessage() {
        return "Band name over 100 characters.";
    }
}
