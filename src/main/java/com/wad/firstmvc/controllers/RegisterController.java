package com.wad.firstmvc.controllers;

import com.wad.firstmvc.domain.Role;
import com.wad.firstmvc.domain.User;
import com.wad.firstmvc.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    private final UserService userService;
    private PasswordEncoder bcrypt = new BCryptPasswordEncoder();

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("username") String user, @ModelAttribute("password") String password, @ModelAttribute("role") String role) {
        User newUser = new User(user, bcrypt.encode(password));
        newUser.getRoles().add(Role.valueOf(role)); 
        userService.save(newUser);
        return "redirect:/login";
    }
}
