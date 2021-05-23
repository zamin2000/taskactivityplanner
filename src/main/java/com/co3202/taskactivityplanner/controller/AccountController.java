package com.co3202.taskactivityplanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
    @GetMapping("/account")
    String showAccount() {
        return "account";
    }
}
