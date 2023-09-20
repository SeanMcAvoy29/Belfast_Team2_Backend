package org.kainos.ea.client;

public class CapabilityLeadDoesNotExistException extends Exception {
    @Override
    public String getMessage(){
        return "Failed to get Capability Lead";
    }
}
