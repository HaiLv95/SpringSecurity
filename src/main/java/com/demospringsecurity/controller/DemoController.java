package com.demospringsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {
    @RequestMapping({"/home", "/"})
    public String home(){
        return "home";
    }

    @RequestMapping("/user/hello")
    public String hello(){
        return "hello";
    }
    @RequestMapping("/admin/hi")
    public String hi(){
        return "hi";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/403")
    public String error(){
        return "errors";
    }
    @RequestMapping("/demo")
    public String demo(){
        return "demo";
    }
}
