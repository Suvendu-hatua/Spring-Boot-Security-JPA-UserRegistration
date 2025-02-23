package com.spring_boot.security.SpringBoot_Security_JPA_Registration.controllers;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.TeacherDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Teacher;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.service.TeacherService;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.ApplicantWebUser;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.TeacherReview;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final TeacherDao teacherDao;

    @GetMapping("/teacher/dashboard")
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Extracting username
        String username = auth.getName();
        Teacher teacher = teacherDao.findByUserUsername(username);
        model.addAttribute("teacher", teacher);
        return "teacher/teacher-profile";
    }

    @GetMapping("/teacher-appointment")
    public String bookAppointment(Model model) {
        //Adding Applicant instance
        model.addAttribute("applicant", new ApplicantWebUser());
        model.addAttribute("status", false);
        return "teacher/appointment";
    }

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
        return "teacher-profile";
    }

    @PostMapping("/add-review")
    public String addReview(@ModelAttribute("review") TeacherReview review){
        System.out.println("********************inside Review *******************");
        System.out.println(review);
        return "redirect:/teachers/view?teacherId=" + review.getId();
    }

}
