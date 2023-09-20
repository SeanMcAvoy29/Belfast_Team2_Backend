package org.kainos.ea.client;

public class FailedToCreateJobException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to create JobRole.";
    }
}
