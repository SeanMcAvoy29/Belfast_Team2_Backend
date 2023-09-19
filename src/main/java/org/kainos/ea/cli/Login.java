package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Login {

    private String email;
    private String password;

    public String getEmail(){return email; }

    public void setEmail(String email){this.email = email; }

    public String getPassword(){ return password; }

    public void  setPassword(String password){this.password =password; }

    @JsonCreator
    public Login(
            @JsonProperty("username") String username,
            @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }


}
