package com.adpro.tasc.controller;

import com.adpro.tasc.appointment.db.dao.AppointmentDAO;
import com.adpro.tasc.appointment.db.model.AppointmentRequest;
import com.adpro.tasc.user.db.dao.UserDAO;
import com.adpro.tasc.user.db.model.AcademicUser;
import com.adpro.tasc.user.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class SeeAppointmentController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AppointmentDAO appointmentDAO;

    @GetMapping("/see-appointment-admin")
    public String seeAppointmentAdmin(Model model, Principal principal) {
        User currentUser = userDAO.getUser(principal.getName());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("newAppointment", appointmentDAO.getAllAppointment());
        return "see_appointment_admin";
    }

    @GetMapping("/see-appointment-TA")
    public String seeAppointmentTA(Model model, Principal principal) {
        User currentUser = userDAO.getUser(principal.getName());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("newAppointment", appointmentDAO.getAppointmentByUser(currentUser));
        return "see_appointment_TA";
    }

    @GetMapping("/see-appointment-student")
    public String seeAppointmentStudent(Model model, Principal principal) {
        User currentUser = userDAO.getUser(principal.getName());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("newAppointment", appointmentDAO.getAppointmentByUser(currentUser));
        return "see_appointment_student";
    }

    @GetMapping("/accept_reject")
    public String acceptPage(Model model, Principal principal) {
        User currentUser = userDAO.getUser(principal.getName());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("appointmentRequests", appointmentDAO.getAppointmentByUser(currentUser));
        return "accept_reject";
    }

    @PostMapping("/accepted")
    public String accPage(@RequestParam("id") int id) {
        AppointmentRequest acc = new AppointmentRequest();
        acc.setId(id);
        appointmentDAO.updateStatus(acc, AppointmentRequest.Status.ACCEPTED);
        return "redirect:/see-appointment-TA";
    }

    @PostMapping("/rejected")
    public String rejPage(@RequestParam("idX") int id) {
        AppointmentRequest rej = new AppointmentRequest();
        rej.setId(id);
        appointmentDAO.updateStatus(rej, AppointmentRequest.Status.REJECTED);
        return "redirect:/see-appointment-TA";
    }

    @PostMapping("/permission-accepted")
    public String accPermissionPage(@RequestParam("idA") int id) {
        AppointmentRequest acc = new AppointmentRequest();
        acc.setId(id);
        appointmentDAO.updateAdminHasPermission(acc,true);
        return "redirect:/accept_reject";
    }

    @PostMapping("/permission-rejected")
    public String rejPermissionPage(@RequestParam("idB") int id) {
        AppointmentRequest rej = new AppointmentRequest();
        rej.setId(id);
        appointmentDAO.updateAdminHasPermission(rej,false);
        return "redirect:/accept_reject";
    }
}
