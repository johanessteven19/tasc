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
public class HelloController {

    @Autowired
    private UserDAO userDAO;
    private User[] users = {new User("Michael182", "Michael Smith", "123", Role.STUDENT),
            new User("Alfred12", "Alfred Rogers", "345", Role.ADMIN),
            new User("Jeff19", "Jeff Stirling", "333", Role.TEACHING_ASSISTANT)};
    List<User> userList = new ArrayList<User>(Arrays.asList(users));

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @PostMapping("/login")
    public String loginUser(Model model, @RequestParam("RegUsername") String userName,
                                  @RequestParam("RegPassword") String password) {

        for(int i=0; i<userList.size(); i++){
            if(userName.equals(userList.get(i).getUserName())){
                if(password.equals(userList.get(i).getPassword())){
                    switch(userList.get(i).getRole()){
                        case ADMIN:
                            return "homeAdmin";
                        case TEACHING_ASSISTANT:
                            return "homeTA";
                        case STUDENT:
                            return "homeStudent";
                    }
                }
            }

        }

        return "redirect:/";
    }


    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping(value = "/register/new")
    public String registerStudent(Model model, @RequestParam("RegUsername") String userName,
                                  @RequestParam("RegFullname") String fullName,
                                  @RequestParam("RegPassword") String password) {
        userList.add(new User(userName, fullName, password, null));
        model.addAttribute("userlist",userList);
        return "redirect:/";
    }

    @GetMapping("/home-admin")
    public String adminPage() {
        return "homeAdmin";
    }

    @GetMapping("/home-TA")
    public String TAPage() {
        return "homeTA";
    }

    @GetMapping("/home-student")
    public String StudentPage() {
        return "homeStudent";
    }
}
