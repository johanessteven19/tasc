package com.adpro.tasc.controller;

import com.adpro.tasc.appointment.db.model.Appointment;
import com.adpro.tasc.appointment.db.model.AppointmentRequest;
import com.adpro.tasc.appointment.db.model.Course;
import com.adpro.tasc.user.db.dao.UserDAO;
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
    private List<AppointmentRequest> users = new ArrayList<>();

    @GetMapping("/see-appointment")
    public String seeAppointment(Model model) {
        model.addAttribute("newAppointment", users);
        return "SeeAppointment";
    }

    @GetMapping("/accept_reject")
    public String acceptPage(Model model) {
        AppointmentRequest test = new AppointmentRequest();
        test.setStudent(new User("student1","student1","123", Role.STUDENT));
        test.setRequestTime(20);
        test.setStatus(AppointmentRequest.Status.PENDING);
        test.setAdminHasPermission(true);

        Appointment appointment = new Appointment();
        Course course1 = new Course();
        course1.setName("Softeng");
        appointment.setCourse(course1);
        appointment.setDuration(20);
        appointment.setDate(System.currentTimeMillis());
        test.setAppointment(appointment);
        users.add(test);

        model.addAttribute("userList",users);
        return "accept_reject";
    }

    @PostMapping("/accepted")
    public String accPage(@RequestParam("fullName") String fullName,
                          @RequestParam("course") String course,
                          @RequestParam("date") String date,
                          @RequestParam("duration") String duration) {
        return "redirect:/see-appointment";
    }
}
