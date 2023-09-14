package org.kainos.ea.client;

public class FailedToGetJobException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to get Job.";
    }
}
