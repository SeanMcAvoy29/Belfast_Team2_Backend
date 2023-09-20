package org.kainos.ea.client;

public class JobRoleTooLongException extends Throwable {
    @Override
    public String getMessage() {
        return "JobRole name over 60 characters.";
    }
}
