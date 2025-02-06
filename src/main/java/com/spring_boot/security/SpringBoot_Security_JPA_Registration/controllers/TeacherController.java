package com.spring_boot.security.SpringBoot_Security_JPA_Registration.controllers;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Teacher;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.service.TeacherService;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.service.UserService;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.TeacherReview;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachers")
@Slf4j
@RequiredArgsConstructor
public class TeacherController {

    private final UserService userService;
    private final TeacherService teacherService;

    @GetMapping("/view")
    public String viewIndividualTeacher(@RequestParam("teacherId")Integer teacherID,Model theModel){
        Teacher teacher=teacherService.getTeacherById(teacherID);
        System.out.println(teacher);
        //adding teacher info to model
        theModel.addAttribute("teacher",teacher);
        //adding review instance to model.
        TeacherReview review=new TeacherReview();
        review.setId(teacher.getId());
        theModel.addAttribute("review",review);
        return "teacher/view-individual-teacher";
    }

    @PostMapping("/add-review")
    public String addReview(@ModelAttribute("review") TeacherReview review){
        System.out.println("********************inside Review *******************");
        System.out.println(review);
        return "redirect:/teachers/view?teacherId=" + review.getId();
    }

}
