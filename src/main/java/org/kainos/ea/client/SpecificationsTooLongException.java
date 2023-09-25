package org.kainos.ea.client;

public class SpecificationsTooLongException extends Throwable {
    @Override
    public String getMessage() {
        return "Specifications exceeds 100 characters.";
    }
}
