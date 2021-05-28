package com.adpro.tasc.controller;

import com.adpro.tasc.appointment.db.dao.AppointmentDAO;
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
    private AppointmentDAO appointmentDAO;
    private List<AppointmentRequest> users = new ArrayList<>();
//    private User[] appointmentReq = {new AppointmentRequest()};
//    List<AppointmentRequest> appointmentList = new ArrayList(Arrays.asList(appointmentReq));

    @GetMapping("/see-appointment")
    public String seeAppointment(Model model) {
//        model.addAttribute("newAppointment", users);
        model.addAttribute("newAppointment", appointmentDAO.getAllAppointment());
        return "SeeAppointment";
    }

    @GetMapping("/accept_reject")
    public String acceptPage(Model model) {
        AppointmentRequest test = new AppointmentRequest();
        test.setStudent(new User("student1","student1","123", Role.ROLE_STUDENT));
        test.setRequestTime(20);
        test.setStatus(AppointmentRequest.Status.PENDING);
        test.setAdminHasPermission(true);

        Appointment appointment = new Appointment();
        Course course = new Course();
        course.setName("Softeng");
        appointment.setCourse(course);
        appointment.setDuration(20);
        appointment.setDate(System.currentTimeMillis());
        test.setAppointment(appointment);
        users.add(test);

//        AppointmentRequest test2 = new AppointmentRequest();
//        test2.setStudent(new User("student2","student2","124", Role.STUDENT));
//        test2.setRequestTime(30);
//        test2.setStatus(AppointmentRequest.Status.PENDING);
//        test2.setAdminHasPermission(true);
//
//        Appointment appointment2 = new Appointment();
//        Course course2 = new Course();
//        course2.setName("Adpro");
//        appointment.setCourse(course2);
//        appointment.setDuration(30);
//        appointment.setDate(System.currentTimeMillis());
//        test.setAppointment(appointment2);
//        users.add(test2);
//
//        AppointmentRequest test3 = new AppointmentRequest();
//        test3.setStudent(new User("student3","student3","125", Role.STUDENT));
//        test3.setRequestTime(40);
//        test3.setStatus(AppointmentRequest.Status.PENDING);
//        test3.setAdminHasPermission(true);
//
//        Appointment appointment3 = new Appointment();
//        Course course3 = new Course();
//        course3.setName("AIDS");
//        appointment.setCourse(course3);
//        appointment.setDuration(40);
//        appointment.setDate(System.currentTimeMillis());
//        test.setAppointment(appointment3);
//        users.add(test3);
//
//        System.out.println(users);
        model.addAttribute("userList",users);
        return "accept_reject";
    }

    @PostMapping("/accepted")
    public String accPage(@RequestParam("fullName") String fullName,
                          @RequestParam("course") String course,
                          @RequestParam("date") String date,
                          @RequestParam("duration") String duration,
                          @RequestParam("indexAcc") int index) {
//        AppointmentRequest test2 = new AppointmentRequest();
//        test2.setStatus(AppointmentRequest.Status.ACCEPTED);
//        users.set(index, new AppointmentRequest(fullName,course,date,duration, AppointmentRequest.Status.ACCEPTED));
        return "redirect:/see-appointment";
    }

    @PostMapping("/rejected")
    public String rejPage(@RequestParam("fullName") String fullName,
                          @RequestParam("course") String course,
                          @RequestParam("date") String date,
                          @RequestParam("duration") String duration,
                          @RequestParam("indexReject") int index) {
//        AppointmentRequest test3 = new AppointmentRequest();
//        test3.setStatus(AppointmentRequest.Status.REJECTED);
//        users.set(index, test3);
        return "redirect:/accept_reject";
    }
}
