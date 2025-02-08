package com.spring_boot.security.SpringBoot_Security_JPA_Registration.controllers;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.StudentDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.TeacherDao;
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

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final StudentDao studentDao;
    private final TeacherDao teacherDao;

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
        }else{
            //instance of Teacher
            model.addAttribute("teacher",null);
        }
        return "error";
    }


    @GetMapping("access-denied")
    public String accessDeniedPage(){
        return "access-denied";
    }
}
