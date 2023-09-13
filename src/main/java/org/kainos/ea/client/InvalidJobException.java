package org.kainos.ea.client;

public class InvalidJobException extends Throwable {
    public InvalidJobException(String error) {
        super(error);
    }
}
