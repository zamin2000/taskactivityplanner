package com.co3202.taskactivityplanner.controller;

import com.co3202.taskactivityplanner.dto.UserDto;
import com.co3202.taskactivityplanner.model.User;
import com.co3202.taskactivityplanner.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class RegistrationController {
// https://www.baeldung.com/spring-thymeleaf-error-messages
    private UserService userService;

//    @RequestMapping("/")
//    String showHome(Model model) {
//        model.addAttribute("user", new User());
//        return "index";
//    }

    // When the controller receives the request “/”, it creates the new UserDto object that will back the registration form, binds it, and returns.
    @GetMapping("/")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "index";
    }

    @PostMapping("/")
    public String register(@Valid User user, BindingResult result, Model model) {
        // Validate the inputs making sure there are no errors
        if (result.hasErrors()) {
            return "index";
        }
        // Validate the email to ensure it doesn't already exist
        if (userService.existsByEmail(user.getEmail())) {
            return "index";
        }
        // Save user details to database and redirect to login
        userService.createUser(user);
        return "redirect:/login";
    }
}
