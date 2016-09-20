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
    public @ResponseBody Map createAccount(
            @RequestBody String accountSignupParams) throws JSONException {

        JSONObject jsonBody = new JSONObject(accountSignupParams);

        String accountEmail = jsonBody.getString("email");

        if (accountEmail == null) {
            accountEmail = "defaultName";
        }

        Map response = accountService.createAccount(accountEmail);

        return response;
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public  @ResponseBody Map getAccounts () throws IOException, JSONException {

        Map response = accountService.getAllAccounts();
        return response;
    }
}