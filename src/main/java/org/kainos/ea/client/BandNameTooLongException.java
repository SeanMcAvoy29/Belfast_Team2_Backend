package org.kainos.ea.client;

public class BandNameTooLongException extends Throwable {
    @Override
    public String getMessage() {
        return "Band exceeds 100 characters.";
    }
}
