package com.adpro.tasc.controller;

import com.adpro.tasc.user.db.dao.UserDAO;
import com.adpro.tasc.user.db.model.AcademicUser;
import com.adpro.tasc.user.db.model.Role;
import com.adpro.tasc.user.db.model.User;
import com.adpro.tasc.user.db.template.UserTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AddRolesController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping(value = "/add-roles")
    public String addRoles() {
        return "add_roles";
    }

//    @GetMapping(value = "/add-roles")
//    public String addRoles(Model model) {
//        model.addAttribute("userList", userDAO.getAllUser());
//        return "add_roles";
//    }

    @PostMapping(value = "/add-roles/student")
    public String registerStudent(Model model, @RequestParam ("userName") String userName, @RequestParam("fullName") String fullName, @RequestParam("password") String password) {
        model.addAttribute("user", new User(userName, fullName, password, Role.STUDENT));
        return "redirect:/add_roles";
    }

    @PostMapping(value = "/add-roles/ta")
    public String registerTA(Model model, @RequestParam ("userNameTA") String userName, @RequestParam("fullNameTA") String fullName, @RequestParam("passwordTA") String password) {
        model.addAttribute("user", new AcademicUser(userName, fullName, password, Role.TEACHING_ASSISTANT));
        return "redirect:/add-roles";
    }

}
