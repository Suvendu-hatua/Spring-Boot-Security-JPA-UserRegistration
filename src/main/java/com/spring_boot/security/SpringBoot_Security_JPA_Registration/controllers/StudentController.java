package com.spring_boot.security.SpringBoot_Security_JPA_Registration.controllers;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.User;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.service.UserService;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.Guardian;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class StudentController {

    @Value("${courseList}")
    private List<String> courseList;

    @Value("${countryList}")
    private List<String> countryList;

    Logger logger=Logger.getLogger(getClass().getName());
    private UserService userService;

    @Autowired
    public StudentController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/showRegistration")
    public String showStudentRegistrationForm(Model theModel){
        //creating instance of Guardian to store new entry
        Guardian guardian =new Guardian();
//        Adding instance to model
        theModel.addAttribute("guardian", guardian);
        //Adding countryList and courseList to the Model
        theModel.addAttribute("courseList",courseList);
        theModel.addAttribute("countryList",countryList);

        return "student/show-registration";
    }

    @PostMapping("/submit-registration")
    public String submitStudentRegistration(@Valid @ModelAttribute("guardian") Guardian guardian,
                                     BindingResult bindingResult, HttpSession session,Model theModel){
        String userName= guardian.getUserName();
        logger.info("Processing Registration for:"+userName);

        System.out.println(guardian);

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult);

            //Adding countryList and courseList to the Model
            theModel.addAttribute("courseList",courseList);
            theModel.addAttribute("countryList",countryList);
            return "student/show-registration";
        }
        //Checking if the username is already exists in the DB or not.
        User user=userService.findUserByName(userName);
        if(user!=null){
            //Already same username exists in the database.
            theModel.addAttribute("registrationError",true);
            theModel.addAttribute("message","username already exists. Choose a different username.");
            theModel.addAttribute("guardian",new Guardian());
            logger.warning("User name already exists.");
            return "student/show-registration";
        }
        //saving new entry in the database.
        userService.saveAsStudent(guardian);
        logger.info("Successfully created user: " + userName);

        //Placing user in http session for later use.
        session.setAttribute("student", guardian);

        //Adding an attribute of successful register
        theModel.addAttribute("registrationSuccess",true);
        return "custom-signing";
    }
}
