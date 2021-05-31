package com.adpro.tasc.controller;

import com.adpro.tasc.appointment.db.dao.CourseDAO;
import com.adpro.tasc.appointment.db.model.Course;
import com.adpro.tasc.user.db.dao.UserDAO;
import com.adpro.tasc.user.db.model.AcademicUser;
import com.adpro.tasc.user.db.model.Role;
import com.adpro.tasc.user.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserListCourses {

    @Autowired
    UserDAO userDAO;

    @Autowired
    CourseDAO courseDAO;

    @GetMapping("/userlist-courses")
    public String userListCourses (Model model, Principal principal) {
        User currentUser = userDAO.getUser(principal.getName());

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("courseList", courseDAO.getAllCourse());

        return "userList_Courses";
    }

    @PostMapping("/userlist-courses/assign")
    public String assignCourse (Model model, @RequestParam("userName") String userName, @RequestParam("courseName") String courseName) {
        AcademicUser user1 = (AcademicUser) userDAO.getUser(userName);
        Course course1 = courseDAO.getCourseByName(courseName);
        courseDAO.addUserCourse(user1, course1);
        return "redirect:/userlist-courses";
    }
}
