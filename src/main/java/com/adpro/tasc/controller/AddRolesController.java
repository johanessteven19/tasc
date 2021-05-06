package com.adpro.tasc.controller;

import com.adpro.tasc.core.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AddRolesController {
    @GetMapping(value = "/add-roles")
    public String addRoles(@ModelAttribute User user, Model model) {
        return "add_roles";
    }

}
