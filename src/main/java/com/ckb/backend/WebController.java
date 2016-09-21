package com.ckb.backend;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/")
    @ResponseBody
    String home() {

        return "Hello World test yo!";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public @ResponseBody Response createAccount(
            @RequestBody String createAccountParams) throws JSONException {

        JSONObject jsonBody = new JSONObject(createAccountParams);

        String email = jsonBody.getString("email");
        String password = jsonBody.getString("password");

        return accountService.createAccount(email, password);
    }

    @RequestMapping(value = "/account", method = RequestMethod.PUT)
    public @ResponseBody Response changePassword(
            @RequestBody String changePasswordParams) throws JSONException {

        JSONObject jsonBody = new JSONObject(changePasswordParams);

        String email = jsonBody.getString("email");
        String oldPassword = jsonBody.getString("oldPassword");
        String newPassword = jsonBody.getString("newPassword");

        return accountService.changePassword(email, oldPassword, newPassword);
    }

    @RequestMapping(value = "/account", method = RequestMethod.DELETE)
    public @ResponseBody Response deleteAccount(
            @RequestBody String deleteAccountParams) throws JSONException {

        JSONObject jsonBody = new JSONObject(deleteAccountParams);

        String email = jsonBody.getString("email");
        String password = jsonBody.getString("password");

        return accountService.deleteAccount(email, password);
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public  @ResponseBody Map getAccounts () throws IOException, JSONException {

        Map response = accountService.getAllAccounts();
        return response;
    }
}