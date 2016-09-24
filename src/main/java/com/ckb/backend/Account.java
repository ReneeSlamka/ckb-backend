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
    private String email;


    /*@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;*/

    //@NotEmpty


    @NotEmpty
    private String password;

    public Account() {
        super();
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
