package com.adpro.tasc.controller;

import com.adpro.tasc.appointment.db.dao.CourseDAO;
import com.adpro.tasc.appointment.db.model.Course;
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
import java.util.List;

@Controller
public class UserListCoursesController {

    @Autowired
    UserDAO userDAO;

    @Autowired
    CourseDAO courseDAO;

    @GetMapping("/userlist-courses")
    public String userListCourses (Model model, Principal principal) {
        User currentUser = userDAO.getUser(principal.getName());
        List<Course> courseList = courseDAO.getAllCourse();
        List<Course> userCourseList = courseDAO.getUserCourseList((AcademicUser) currentUser);

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("courseList", courseList);
        model.addAttribute("userCourseList",userCourseList);

        return "userList_Courses";
    }

    @PostMapping("/userlist-courses/assign")
    public String assignCourse (Model model, @RequestParam("userName") String userName, @RequestParam("courseName") String courseName) {
        AcademicUser currentUser = (AcademicUser) userDAO.getUser(userName);
        List<Course> userCourseList = courseDAO.getUserCourseList(currentUser);

        for (int i = 0; i < userCourseList.size(); i++) {
            if (userCourseList.get(i).getName().equals(courseName)) {
                return "redirect:/userlist-courses";
            }
        }

        Course course = courseDAO.getCourseByName(courseName);
        courseDAO.addUserCourse(currentUser, course);
        return "redirect:/userlist-courses";
    }

    @PostMapping("/userlist-courses/leave")
    public String leaveCourse (@RequestParam("userName") String userName, @RequestParam("courseName") String courseName) {
        AcademicUser currentUser = (AcademicUser) userDAO.getUser(userName);
        List<Course> userCourseList = courseDAO.getUserCourseList(currentUser);

        for (int i = 0; i < userCourseList.size(); i++) {
            if (userCourseList.get(i).getName().equals(courseName)) {
                courseDAO.deleteUserCourse(currentUser,userCourseList.get(i));
                return "redirect:/userlist-courses";
            }
        }

        return "redirect:/userlist-courses";
    }
}
