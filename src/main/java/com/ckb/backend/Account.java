package com.ckb.backend;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
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
