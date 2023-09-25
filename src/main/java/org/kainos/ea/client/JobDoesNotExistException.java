package org.kainos.ea.client;

public class JobDoesNotExistException extends Throwable {
    @Override
    public String getMessage() {
        return "Job Does not Exist";
    }
}
