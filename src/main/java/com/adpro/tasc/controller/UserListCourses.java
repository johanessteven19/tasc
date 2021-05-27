package com.adpro.tasc.controller;

import com.adpro.tasc.user.db.dao.UserDAO;
import com.adpro.tasc.user.db.model.Role;
import com.adpro.tasc.user.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserListCourses {

    @Autowired
    UserDAO userDAO;
    private User[] users = {new User("Michael182", "Michael Smith", "123", null),
            new User("Alfred12", "Alfred Rogers", "345", null),
            new User("Jeff19", "Jeff Stirling", "333", null)};
    List<User> userList = new ArrayList<User>(Arrays.asList(users));

    @GetMapping("/userlist-courses")
    public String userListCourses (Model model) {
        model.addAttribute("userList", userList);
        return "userList_Courses";
    }
}
