package org.kainos.ea.client;
public class FailedToGenerateTokenException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to get generate tokens from database";
    }
}
