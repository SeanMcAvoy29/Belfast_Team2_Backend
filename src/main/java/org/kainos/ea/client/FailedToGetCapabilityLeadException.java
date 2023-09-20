package org.kainos.ea.client;

public class FailedToGetCapabilityLeadException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to get Job Specification";
    }
}
