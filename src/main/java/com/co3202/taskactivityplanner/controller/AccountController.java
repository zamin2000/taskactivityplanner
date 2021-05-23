package com.co3202.taskactivityplanner.controller;

import com.co3202.taskactivityplanner.dto.UserDto;
import com.co3202.taskactivityplanner.model.User;
import com.co3202.taskactivityplanner.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.WebRequest;

import java.security.Principal;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class AccountController {

    private UserService userService;

    public String showAccount(Model model, Principal principal) {
//         Authentication
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
//         Sending task object to thymeleaf, to access user details

        model.addAttribute("user", user);
        return "account";
    }

    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        userService.deleteUser(user.getId());
        return "redirect:/logout";
    }
}
