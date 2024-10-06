package com.spring_boot.security.SpringBoot_Security_JPA_Registration.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/security-meeting")
    public String securityAwarenessMeeting(){
        return "meetings/security-awareness";
    }

}
