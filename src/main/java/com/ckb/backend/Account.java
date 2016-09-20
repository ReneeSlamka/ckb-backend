package com.ckb.backend;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name="ACCOUNT")

public class Account {

    @Id
    @Column(name="EMAIL")
    private String email;

    public Account() {
        super();
    }

    public Account(String email) {
        this.email = email;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
}
