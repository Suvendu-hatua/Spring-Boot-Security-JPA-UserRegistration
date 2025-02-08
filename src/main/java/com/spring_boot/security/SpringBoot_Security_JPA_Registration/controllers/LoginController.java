package com.spring_boot.security.SpringBoot_Security_JPA_Registration.controllers;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.ApplicantDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.StudentDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Applicant;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Student;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.Child;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final StudentDao studentDao;
    private final ApplicantDao applicantDao;

    @GetMapping("/show-login")
    public String showLogin(){
        return "custom-signing";
    }

    @GetMapping("/dashboard")
    public String login(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Extracting username
        String username = auth.getName();
        log.info("Username:{}, Authorities:{}", username, auth.getAuthorities());
        //if the logged-in user is Student
        if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STUDENT"))){
            Student student=studentDao.findByUserUsername(username);
            model.addAttribute("student",student);
            //creating new child instance from userDetails
            Child child=new Child(student.getStudentFirstName(),student.getStudentLastName(),student.getStudentAge(),student.getStudentGender());
            model.addAttribute("child",child);
            return "student/complete-profile";
        }else if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_TEACHER"))){
            //instance of Teacher
            model.addAttribute("teacher",null);
        }
        else{
            //Admin login
            //Getting all the Job Applicant list
            List<Applicant> applicants=applicantDao.findAll();
            model.addAttribute("applicants",applicants);
            return "admin/admin-profile";
        }
        return "error";
    }


    @GetMapping("access-denied")
    public String accessDeniedPage(){
        return "access-denied";
    }
}
