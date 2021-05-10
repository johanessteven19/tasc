package com.adpro.tasc.controller;

import com.adpro.tasc.user.db.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


public class SeeAppointmentController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/see-appointment")
    public String seeAppointment(Model model) {
        model.addAttribute("userList", userDAO.getAllUser());
        return "SeeAppointment";
    }
}
