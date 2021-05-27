package com.adpro.tasc.controller;

import com.adpro.tasc.appointment.db.dao.AppointmentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookAppointmentController {
  @Autowired
  AppointmentDAO appointmentDAO;

  public BookAppointmentController(){

  }

  @GetMapping("/book-appointments")
  public String bookAppointmentUI(Model model) {
    return "book_appointments";
  }


}
