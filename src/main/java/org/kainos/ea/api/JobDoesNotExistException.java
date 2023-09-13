package org.kainos.ea.api;

public class JobDoesNotExistException extends Throwable {
    @Override
    public String getMessage() {
        return "Job Does not Exist";
    }
}

