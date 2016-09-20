package com.ckb.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepo;

    public Map createAccount(String email) {
        Map response = new HashMap<>();

        if (accountRepo.findByEmail(email) == null) {
            Account newAccount = new Account(email);
            Account savedAccount = accountRepo.save(newAccount);

            if (savedAccount == null) {
                response.put("Status", "Account creation failed");
            } else {
                response.put("Status", "Account created successfully");
            }
        } else {
            response.put("Status", "Account creation failed, please retry with different email");
        }

        return response;
    }

    public Map getAllAccounts() {

        Map response = new HashMap<>();

        Iterable<Account> accounts = accountRepo.findAll();
        List<Account> listAccounts = new ArrayList<>();

        for (Account account : accounts) {
            listAccounts.add(account);
        }

        response.put("Accounts", listAccounts);
        response.put("Status", "Accounts returned");
        return response;
    }
}
