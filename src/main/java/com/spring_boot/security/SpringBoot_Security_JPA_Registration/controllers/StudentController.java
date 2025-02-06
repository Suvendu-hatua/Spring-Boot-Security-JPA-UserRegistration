package com.spring_boot.security.SpringBoot_Security_JPA_Registration.controllers;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.StudentDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Student;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.User;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.service.StudentService;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.service.UserService;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.Child;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.Guardian;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    @Value("${courseList}")
    private List<String> courseList;

    @Value("${countryList}")
    private List<String> countryList;

    private final StudentService studentService;
    private final UserService userService;
    private final StudentDao studentDao;

    @GetMapping("/register/showRegistration")
    public String showStudentRegistrationForm(Model theModel) {
        //creating instance of Guardian to store new entry
        Guardian guardian = new Guardian();
//        Adding instance to model
        theModel.addAttribute("guardian", guardian);
        //Adding countryList and courseList to the Model
        theModel.addAttribute("courseList", courseList);
        theModel.addAttribute("countryList", countryList);

        return "student/show-registration";
    }

    @PostMapping("/register/submit-registration")
    public String submitStudentRegistration(@Valid @ModelAttribute("guardian") Guardian guardian,
                                            BindingResult bindingResult, HttpSession session, Model theModel) {
        String userName = guardian.getUserName();
        log.info("Processing Registration for:", userName);
        System.out.println(guardian);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);

            //Adding countryList and courseList to the Model
            theModel.addAttribute("courseList", courseList);
            theModel.addAttribute("countryList", countryList);
            return "student/show-registration";
        }
        //Checking if the username is already exists in the DB or not.
        User user = userService.findUserByName(userName);
        if (user != null) {
            //Already same username exists in the database.
            theModel.addAttribute("registrationError", true);
            theModel.addAttribute("message", "username already exists. Choose a different username.");
            theModel.addAttribute("guardian", new Guardian());
            log.warn("User name already exists.");
            return "student/show-registration";
        }
        //saving new entry in the database.
        studentService.saveAsStudent(guardian);
        log.info("Successfully created user: ", userName);
        //Placing user in http session for later use.
        session.setAttribute("student", guardian);
        //Adding an attribute of successful register
        theModel.addAttribute("registrationSuccess", true);
        return "custom-signing";
    }

    @PostMapping("/updateAccount")
    public String updateChildDetails(@ModelAttribute("child") Child child,Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        studentService.addChildDetails(child,auth.getName());
        //getting updated student details
        Student student=studentDao.findByUserUsername(auth.getName());
        //Adding to model
        model.addAttribute("student",student);
        Child child1=new Child(student.getStudentFirstName(),student.getStudentLastName(),student.getStudentAge(),student.getStudentGender());
        model.addAttribute("child",child1);
        return "registration/complete-profile";
    }

}
