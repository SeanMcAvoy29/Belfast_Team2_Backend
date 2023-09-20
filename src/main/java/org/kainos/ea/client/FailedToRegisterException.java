package org.kainos.ea.client;

public class FailedToRegisterException extends Throwable {
    private String customMessage;

    public FailedToRegisterException(String message) {
        this.customMessage = message;
    }

    public FailedToRegisterException() {

    }

    @Override
    public String getMessage() {
        return customMessage != null ? customMessage : "Failed to Register new user";
    }
}
