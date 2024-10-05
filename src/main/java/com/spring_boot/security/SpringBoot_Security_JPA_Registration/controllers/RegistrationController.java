package com.spring_boot.security.SpringBoot_Security_JPA_Registration.controllers;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.User;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.service.UserService;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.StudentWebUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    Logger logger=Logger.getLogger(getClass().getName());
    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/showRegistration")
    public String showRegistrationForm(Model theModel){
        //creating instance of studentWebUser to store new entry
        StudentWebUser studentWebUser =new StudentWebUser();
//        Adding instance to model
        theModel.addAttribute("studentWebUser", studentWebUser);
        return "registration/show-registration";
    }

    @PostMapping("/submit-registration")
    public String submitRegistration(@Valid @ModelAttribute("studentWebUser") StudentWebUser studentWebUser,
                                     BindingResult bindingResult, HttpSession session,Model theModel){
        String userName= studentWebUser.getUserName();
        logger.info("Processing Registration for:"+userName);

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return "registration/show-registration";
        }
        //Checking if the username is already exists in the DB or not.
        User user=userService.findUserByName(userName);
        if(user!=null){
            //Already same username exists in the database.
            theModel.addAttribute("registrationError",true);
            theModel.addAttribute("message","username already exists. Choose a different username.");
            theModel.addAttribute("studentWebUser",new StudentWebUser());
            logger.warning("User name already exists.");
            return "registration/show-registration";
        }
        //saving new entry in the database.
        userService.saveAsStudent(studentWebUser);
        logger.info("Successfully created user: " + userName);

        //Placing user in http session for later use.
        session.setAttribute("user", studentWebUser);

        return "registration/registration-confirmation";
    }
}
