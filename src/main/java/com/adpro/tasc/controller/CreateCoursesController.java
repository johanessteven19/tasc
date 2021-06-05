package com.adpro.tasc.controller;

import com.adpro.tasc.appointment.db.dao.CourseDAO;
import com.adpro.tasc.appointment.db.model.Course;
import com.adpro.tasc.user.db.dao.UserDAO;
import com.adpro.tasc.user.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class CreateCoursesController {
    @Autowired
    UserDAO userDAO;

    @Autowired
    CourseDAO courseDAO;

    @GetMapping(value="/create-course")
    public String createCourseForm(Model model, Principal principal) {
        User currentUser = userDAO.getUser(principal.getName());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("courses", courseDAO.getAllCourse());
        return "create_course";
    }

    @PostMapping(value="/create-course/post")
    public String createCourse(@RequestParam("name") String name) {
        Course newCourse = new Course();
        newCourse.setName(name);
        courseDAO.createCourse(newCourse);
        return "redirect:/create-course";
    }
}