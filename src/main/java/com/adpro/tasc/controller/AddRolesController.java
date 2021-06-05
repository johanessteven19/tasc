package com.adpro.tasc.controller;

import com.adpro.tasc.user.db.dao.UserDAO;
import com.adpro.tasc.user.db.model.AcademicUser;
import com.adpro.tasc.user.db.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasRole('ADMIN')")
public class AddRolesController {
    @Autowired
    private UserDAO userDAO;

    @GetMapping("/add-roles")
    public String addRoles(Model model) {
        model.addAttribute("userList", userDAO.getAllUser());
        return "add_roles";
    }

    @PostMapping(value = "/add-roles/student")
    public String registerStudent(Model model, @RequestParam ("userName") String userName,
                                  @RequestParam("fullName") String fullName,
                                  @RequestParam("password") String password, @RequestParam("index") int index) {
        AcademicUser student = new AcademicUser(userDAO.getUser(userName));
        student.setRole(Role.ROLE_STUDENT);

        userDAO.updateUser(userName, student);

        model.addAttribute("user", student);
        return "redirect:/add-roles";
    }

    @PostMapping(value = "/add-roles/ta")
    public String registerTA(Model model, @RequestParam ("userNameTA") String userName,
                             @RequestParam("fullNameTA") String fullName,
                             @RequestParam("passwordTA") String password, @RequestParam("indexTA") int index) {
        AcademicUser ta = new AcademicUser(userDAO.getUser(userName));
        ta.setRole(Role.ROLE_TEACHING_ASSISTANT);

        userDAO.updateUser(userName, ta);

        model.addAttribute("user", ta);
        return "redirect:/add-roles";
    }

}
