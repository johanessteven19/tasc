package com.adpro.tasc.controller;

import com.adpro.tasc.user.db.dao.UserDAO;
import com.adpro.tasc.user.db.model.AcademicUser;
import com.adpro.tasc.user.db.model.Role;
import com.adpro.tasc.user.db.model.User;
import com.adpro.tasc.user.db.template.UserWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/add-roles")
public class AddRolesController {

    @Autowired
    private UserDAO userDAO;
    private User[] users = {new User("Michael182", "Michael Smith", "123", null),
            new User("Alfred12", "Alfred Rogers", "345", null),
            new User("Jeff19", "Jeff Stirling", "333", null)};
    List<User> userList = new ArrayList<User>(Arrays.asList(users));

    private UserWrapper userWrapper;


    @GetMapping("/")
    public String addRoles(Model model) {
        model.addAttribute("userList", userDAO.getAllUser());
        return "add_roles";
    }


    @PostMapping(value = "/student")
    public String registerStudent(Model model, @RequestParam ("userName") String userName,
                                  @RequestParam("fullName") String fullName,
                                  @RequestParam("password") String password, @RequestParam("index") int index) {
        userList.set(index, new User(userName, fullName, password, Role.ROLE_STUDENT));
        model.addAttribute("user", new AcademicUser(userName, fullName, password, Role.ROLE_STUDENT));
        return "redirect:/add-roles";
    }

    @PostMapping(value = "/ta")
    public String registerTA(Model model, @RequestParam ("userNameTA") String userName,
                             @RequestParam("fullNameTA") String fullName,
                             @RequestParam("passwordTA") String password, @RequestParam("indexTA") int index) {
        userList.set(index, new User(userName, fullName, password, Role.ROLE_TEACHING_ASSISTANT));
        model.addAttribute("user", new AcademicUser(userName, fullName, password, Role.ROLE_TEACHING_ASSISTANT));
        return "redirect:/add-roles";
    }

}
