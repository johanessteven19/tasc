package com.adpro.tasc.controller;

import com.adpro.tasc.appointment.db.dao.CourseDAO;
import com.adpro.tasc.appointment.db.model.Course;
import com.adpro.tasc.user.db.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CreateCoursesController {
  @Autowired
  UserDAO userDAO;

  @Autowired
  CourseDAO courseDAO;

  List<Course> courses = new ArrayList<>();

  @GetMapping(value="/create-course")
  public String createCourseForm(Model model) {
    System.out.println();
    model.addAttribute("courses",courseDAO.getAllCourse());
    return "create_course";
  }

  @PostMapping(value="/create-course/post")
  public String createCourse(
          @RequestParam("name") String name
  ) {
    System.out.println(name);
    Course newCourse = new Course();
    newCourse.setName(name);

//    courses.add(newCourse);
    courseDAO.createCourse(newCourse);

    return "redirect:/create-course";
  }
}