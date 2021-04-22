package com.adpro.tasc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    @ResponseBody
    public String getHello() {
        return "Hello World!";
    }

    @GetMapping(params = "name")
    @ResponseBody
    public String getHelloName(@RequestParam String name) {
        return "Hello " + name + "!";
    }
}
