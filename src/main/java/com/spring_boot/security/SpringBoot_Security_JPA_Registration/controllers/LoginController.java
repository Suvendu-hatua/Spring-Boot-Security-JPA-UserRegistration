package com.spring_boot.security.SpringBoot_Security_JPA_Registration.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/show-login")
    public String showLogin(){
        return "custom-signing";
    }

    @GetMapping("access-denied")
    public String accessDeniedPage(){
        return "access-denied";
    }
}
