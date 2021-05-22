package com.adpro.tasc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HelloController {


    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/see")
    public String seeAppointment() {
        return "SeeAppointment";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
}
