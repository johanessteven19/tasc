package com.adpro.tasc.controller;

import com.adpro.tasc.user.db.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/home-admin")
    public String adminPage() {
        return "homeAdmin";
    }

    @GetMapping("/home-TA")
    public String TAPage() {
        return "homeTA";
    }

    @GetMapping("/home-student")
    public String StudentPage() {
        return "homeStudent";
    }
}
