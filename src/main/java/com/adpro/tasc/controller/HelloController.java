package com.adpro.tasc.controller;

import com.adpro.tasc.user.db.dao.UserDAO;
import com.adpro.tasc.user.db.model.Role;
import com.adpro.tasc.user.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.adpro.tasc.user.db.model.Role;

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

    @PostMapping(value = "/register/new")
    public String registerStudent(Model model, @RequestParam("RegUsername") String userName,
                                  @RequestParam("RegFullname") String fullName,
                                  @RequestParam("RegPassword") String password) {

        userDAO.createUser(new User(userName, fullName, "{noop}"+password, Role.ROLE_UNASSIGNED));

        return "redirect:/";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/home-admin")
    public String adminPage() {
        return "homeAdmin";
    }

    @PreAuthorize("hasRole('ROLE_TEACHING_ASSISTANT')")
    @GetMapping("/home-TA")
    public String TAPage() {
        return "homeTA";
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping("/home-student")
    public String StudentPage() {
        return "homeStudent";
    }
}
