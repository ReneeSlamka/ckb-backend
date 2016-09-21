package com.ckb.backend;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@Service
public class AccountService {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    private AccountRepository accountRepo;

    public Response createAccount(String email, String password) {
        Response response = new Response();

        /* check for illegal characters and empty inputs */
        /* add check for password */
        Matcher emailMatcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);

        if (!emailMatcher.find() || email.length() == 0 || password.length() < 6) {
            response.setSucceeded(false);
            response.setMessage(Response.INVALID_CHARS);
        } else {
            /* only one account can be tied to a given email address */
            if (accountRepo.findByEmail(email) == null) {
                /* create new account and save it */
                Account newAccount = new Account(email, password);
                Account savedAccount = accountRepo.save(newAccount);

                /* ensure saved correctly */
                if (savedAccount != null) {
                    response.setSucceeded(true);
                    response.setMessage(Response.ACCOUNT_CREATED);
                } else {
                    response.setSucceeded(false);
                    response.setMessage(Response.DATABASE_ERROR);
                }

            } else {
                response.setSucceeded(false);
                response.setMessage(Response.DUPLICATE_EMAIL);
            }
        }

        return response;
    }

    public Response changePassword(String email, String oldPassword, String newPassword) {
        Response response = new Response();

        /* check for illegal characters */
        /* add check for password */
        Matcher emailMatcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (!emailMatcher.find()) {
            response.setSucceeded(false);
            response.setMessage(Response.INVALID_CHARS);
        } else {
            /* check that account with that email exists */
            if (accountRepo.findByEmail(email) == null) {
                response.setSucceeded(false);
                response.setMessage(Response.ACCOUNT_DNE);
            } else {
                /* check that password is correct */
                Account targetAccount = accountRepo.findByEmail(email);
                if (targetAccount.getPassword().equals(oldPassword)) {
                    targetAccount.setPassword(newPassword);
                    Account savedAccount = accountRepo.save(targetAccount);

                    /* check that changes saved correctly */
                    if (savedAccount != null) {
                        response.setSucceeded(true);
                        response.setMessage(Response.PW_CHANGED);
                    } else {
                        response.setSucceeded(false);
                        response.setMessage(Response.DATABASE_ERROR);
                    }
                } else {
                    response.setSucceeded(false);
                    response.setMessage(Response.PW_CHANGE_FAILED);
                }
            }
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
