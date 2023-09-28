package org.kainos.ea.client;

public class FailedToLoginException extends Throwable {
    private String customMessage;

    public FailedToLoginException(String message) {
        this.customMessage = message;
    }

    public FailedToLoginException() {

    }

    @Override
    public String getMessage() {
        return customMessage != null ? customMessage : "Failed to login from database";
    }
}
