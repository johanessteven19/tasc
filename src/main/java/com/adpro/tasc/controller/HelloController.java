package com.adpro.tasc.controller;

import com.adpro.tasc.user.db.dao.UserDAO;
import com.adpro.tasc.user.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/hello")
public class HelloController {
    private UserDAO userDB;

    @Autowired
    public void setUserDB(UserDAO userDB) {
        this.userDB = userDB;
    }

    @GetMapping
    @ResponseBody
    public List<User> getHello() {
        return userDB.getAllUser();
    }

    @GetMapping(params = "name")
    @ResponseBody
    public String getHelloName(@RequestParam String name) {
        return "Hello " + name + "!";
    }
}
