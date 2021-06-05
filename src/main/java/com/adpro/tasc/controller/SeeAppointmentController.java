package com.adpro.tasc.controller;

import com.adpro.tasc.appointment.db.dao.AppointmentDAO;
import com.adpro.tasc.appointment.db.model.Appointment;
import com.adpro.tasc.appointment.db.model.AppointmentRequest;
import com.adpro.tasc.appointment.db.model.Course;
import com.adpro.tasc.user.db.dao.UserDAO;
import com.adpro.tasc.user.db.model.AcademicUser;
import com.adpro.tasc.user.db.model.Role;
import com.adpro.tasc.user.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

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
    public String accPage(Model model, @RequestParam("userName") String userName,
                          @RequestParam("fullName") String fullName,
                          @RequestParam("course") String course,
                          @RequestParam("date") String date,
                          @RequestParam("duration") String duration) {
//        AcademicUser currentUser1 = new AcademicUser(userDAO.getUser(userName));
//        List<AppointmentRequest> user1 = appointmentDAO.getAppointmentByUser(currentUser1);
//        AppointmentRequest acc = new AppointmentRequest(appointmentDAO.getAppointmentByUser(user1));
//
//        appointmentDAO.updateStatus(acc, AppointmentRequest.Status.ACCEPTED);
//        model.addAttribute("user", acc);
        return "redirect:/see-appointment";
    }

    @PostMapping("/rejected")
    public String rejPage(Model model, @RequestParam("userNameX") String userName,
                          @RequestParam("fullNameX") String fullName,
                          @RequestParam("courseX") String course,
                          @RequestParam("dateX") String date,
                          @RequestParam("durationX") String duration) {
//        AcademicUser currentUser2 = new AcademicUser(userDAO.getUser(userName));
//        List<AppointmentRequest> user2 = appointmentDAO.getAppointmentByUser(currentUser2);
//        AppointmentRequest rej = new AppointmentRequest(appointmentDAO.getAppointmentByUser(user2));
//
//        appointmentDAO.updateStatus(rej, AppointmentRequest.Status.ACCEPTED);
//        model.addAttribute("user", rej);
        return "redirect:/accept_reject";
    }
}
