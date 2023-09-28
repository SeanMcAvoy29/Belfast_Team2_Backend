package org.kainos.ea.client;

public class JobRoleDoesNotExistException extends Throwable {
    @Override
    public String getMessage() {
        return "JobRole Does not Exist";
    }
}
