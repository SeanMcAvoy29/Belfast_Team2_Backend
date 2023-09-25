package org.kainos.ea.client;

public class JobRoleTooLongException extends Throwable {
    @Override
    public String getMessage() {
        return "JobRole exceeds 60 characters";
    }
}
