package org.kainos.ea.client;

public class JobRoleTooLongException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to delete Job Roles.";
    }
}
