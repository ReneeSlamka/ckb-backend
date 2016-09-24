package com.ckb.backend;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Session {

    @Id
    private String accountEmail;
    
    private String token;

    private String timeStamp;

    public String getAccountEmail() {
        return this.accountEmail;
    }

    public void setAccountEmail(String email) {
        this.accountEmail = email;
    }

    public String getSessionToken() {
        return this.token;
    }

    public void setSessionToken(String token) {
        this.token = token;
    }

    public String getTimeStamp() { return this.timeStamp; }

    public void setTimeStamp(String timeStamp) { this.timeStamp = timeStamp; }

    public String generateSessionToken() {
        /* Todo: Replace this later, just for testing control flow */
        return this.accountEmail + "123";
    }
}
