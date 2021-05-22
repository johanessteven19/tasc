package com.adpro.tasc.controller;

import com.adpro.tasc.user.db.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HelloController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/see")
//    public String seeAppointment() {
//        return "SeeAppointment";
//    }
    public String seeAppointment(Model model) {
        model.addAttribute("userList", userDAO.getAllUser());
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
