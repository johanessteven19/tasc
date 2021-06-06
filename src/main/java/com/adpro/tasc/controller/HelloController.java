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

import java.security.Principal;

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
    public String registerStudent(Model model, @RequestParam("username") String userName,
                                  @RequestParam("fullname") String fullName,
                                  @RequestParam("password") String password) {
        userDAO.createUser(new User(userName, fullName, "{noop}"+password, Role.ROLE_UNASSIGNED));
        return "redirect:/";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/home-admin")
    public String adminPage(Model model, Principal principal) {
        User currentUser = userDAO.getUser(principal.getName());
        model.addAttribute("currentUser", currentUser);
        return "homeAdmin";
    }

    @PreAuthorize("hasRole('TEACHING_ASSISTANT')")
    @GetMapping("/home-TA")
    public String TAPage(Model model, Principal principal) {
        User currentUser = userDAO.getUser(principal.getName());
        model.addAttribute("currentUser", currentUser);
        return "homeTA";
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/home-student")
    public String StudentPage(Model model, Principal principal) {
        User currentUser = userDAO.getUser(principal.getName());
        model.addAttribute("currentUser", currentUser);
        return "homeStudent";
    }

    @PreAuthorize("hasRole('UNASSIGNED')")
    @GetMapping("/waiting")
    public String waitingPage(Model model, Principal principal) {
        User currentUser = userDAO.getUser(principal.getName());
        model.addAttribute("currentUser", currentUser);
        return "waiting";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/nav-admin")
    public String adminNavPage(Model model, Principal principal) {
        User currentUser = userDAO.getUser(principal.getName());
        model.addAttribute("currentUser", currentUser);
        return "fragments/navbar_admin";
    }

    @PreAuthorize("hasRole('TEACHING_ASSISTANT')")
    @GetMapping("/nav-TA")
    public String TANavPage(Model model, Principal principal) {
        User currentUser = userDAO.getUser(principal.getName());
        model.addAttribute("currentUser", currentUser);
        return "fragments/navbar_TA";
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/nav-student")
    public String StudentNavPage(Model model, Principal principal) {
        User currentUser = userDAO.getUser(principal.getName());
        model.addAttribute("currentUser", currentUser);
        return "fragments/navbar_student";
    }
}
