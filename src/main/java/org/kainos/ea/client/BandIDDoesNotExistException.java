package org.kainos.ea.client;

public class BandIDDoesNotExistException extends Throwable {
    @Override
    public String getMessage() {
        return "BandID does not exist.";
    }
}
