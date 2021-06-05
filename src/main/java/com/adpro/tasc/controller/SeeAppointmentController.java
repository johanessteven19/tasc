package com.adpro.tasc.controller;

import com.adpro.tasc.appointment.db.dao.AppointmentDAO;
import com.adpro.tasc.user.db.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SeeAppointmentController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AppointmentDAO appointmentDAO;

    @GetMapping("/see-appointment")
    public String seeAppointment(Model model) {
        model.addAttribute("newAppointment", appointmentDAO.getAllAppointment());
        return "SeeAppointment";
    }

    @GetMapping("/accept_reject")
    public String acceptPage(Model model) {
        model.addAttribute("newAppointment", appointmentDAO.getAllAppointment());
        return "accept_reject";
    }

    @PostMapping("/accepted")
    public String accPage(@RequestParam("fullName") String fullName,
                          @RequestParam("course") String course,
                          @RequestParam("date") String date,
                          @RequestParam("duration") String duration,
                          @RequestParam("indexAcc") int index) {
        return "redirect:/see-appointment";
    }

    @PostMapping("/rejected")
    public String rejPage(@RequestParam("fullName") String fullName,
                          @RequestParam("course") String course,
                          @RequestParam("date") String date,
                          @RequestParam("duration") String duration,
                          @RequestParam("indexReject") int index) {
        return "redirect:/accept_reject";
    }
}
