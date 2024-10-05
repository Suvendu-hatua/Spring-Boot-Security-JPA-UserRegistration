package com.spring_boot.security.SpringBoot_Security_JPA_Registration.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/systems")
public class TeacherController {

    @GetMapping("/add-teacher")
    public String showTeacherRegistrationForm(){
        return "teacher/show-registration";
    }

}
