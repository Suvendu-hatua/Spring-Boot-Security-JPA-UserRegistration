package com.spring_boot.security.SpringBoot_Security_JPA_Registration.controllers;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Student;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.service.UserService;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.Child;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @GetMapping("/show-login")
    public String showLogin(){
        return "custom-signing";
    }

    @GetMapping("/dashboard")
    public String login(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Extracting username
        String username = auth.getName();
        log.info(username, auth.getAuthorities());
        Object userDetails=userService.getUserDetailsByUsername(username);
        log.info(userDetails.toString());
        if(userDetails instanceof Student){
            model.addAttribute("student",userDetails);
            //creating new child instance from userDetails
            Student student = (Student) userDetails;
            Child child=new Child(student.getStudentFirstName(),student.getStudentLastName(),student.getStudentAge(),student.getStudentGender());
            model.addAttribute("child",child);
            return "registration/complete-profile";
        }else{
            //instance of Teacher
            model.addAttribute("teacher",userDetails);
        }
        return "error";
    }


    @GetMapping("access-denied")
    public String accessDeniedPage(){
        return "access-denied";
    }
}
