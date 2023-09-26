package org.kainos.ea.client;

public class FailedToGetBandsException extends Throwable{
    @Override
    public String getMessage(){
        return  "Could not get Bands";
    }
}