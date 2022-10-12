package com.example.mvc_thymleaf.securite.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginControler {

    @GetMapping("/login")
    public  String  login(){
        return "login";
    }

    @GetMapping("/register")
    public  String  register(){
        return "register";
    }


}
