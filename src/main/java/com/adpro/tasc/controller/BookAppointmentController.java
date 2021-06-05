package com.adpro.tasc.controller;

import com.adpro.tasc.appointment.db.dao.AppointmentDAO;
import com.adpro.tasc.appointment.db.dao.ScheduleDAO;
import com.adpro.tasc.appointment.db.model.Course;
import com.adpro.tasc.user.db.dao.UserDAO;
import com.adpro.tasc.user.db.model.AcademicUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class BookAppointmentController {
  @Autowired
  AppointmentDAO appointmentDAO;

  @Autowired
  ScheduleDAO scheduleDAO;

  @Autowired
  UserDAO userDAO;

  public BookAppointmentController(){

  }

  @GetMapping("/book-appointment/home")
  public String bookAppointmentHome(Model model, Principal principal) {
    List<Course> courseList = ((AcademicUser) userDAO.getUser(principal.getName())).getCourses();



    return "book_appointment/pick_course";
  }
}
