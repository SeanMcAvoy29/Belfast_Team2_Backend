package org.kainos.ea.client;

public class SpecificationTooLongException extends Throwable {
    @Override
    public String getMessage() {
        return "Specifications over 100 characters.";
    }
}
