package org.kainos.ea.client;

public class SpecificationsTooLongException extends Throwable {
    @Override
    public String getMessage() {
        return "Specifications must be less than 100 characters.";
    }
}
