package com.spring_boot.security.SpringBoot_Security_JPA_Registration.controllers;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Teacher;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.User;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.service.UserService;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.TeacherWebUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final UserService userService;

    @Autowired
    public TeacherController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/view")
    public String viewIndividualTeacher(@RequestParam("teacherId")Integer teacherID,Model theModel){
        Teacher teacher=userService.findTeacherById(teacherID);
        System.out.println(teacher);
        theModel.addAttribute("teacher",teacher);
        return "teacher/view-individual-teacher";
    }

}
