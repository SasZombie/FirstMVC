package com.wad.firstmvc.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "profilePage"; // This should match the name of your home.html template
    }

    @GetMapping("/register")
    public String register() {
        return "signUp";
    }

    @GetMapping("/login")
    public String login() {
        return "signIn"; // This should match the name of your login.html template
    }
}
