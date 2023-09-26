package org.kainos.ea.client;

public class InvalidBandException extends Throwable {
    @Override
    public String getMessage() {
        return "BandID does not exist.";
    }
}
