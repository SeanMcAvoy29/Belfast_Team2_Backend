package org.kainos.ea.client;

public class FailedToUpdateJobRoleException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to update Job Roles.";
    }
}
