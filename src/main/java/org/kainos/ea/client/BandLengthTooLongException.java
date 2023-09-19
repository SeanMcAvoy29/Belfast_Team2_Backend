package org.kainos.ea.client;

public class BandLengthTooLongException extends Throwable {
    @Override
    public String getMessage() {
        return "Band must be less than 100 characters.";
    }
}
