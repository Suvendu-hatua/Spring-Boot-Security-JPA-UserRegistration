package com.spring_boot.security.SpringBoot_Security_JPA_Registration.controllers;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.service.UserService;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.TeacherWebUser;
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
@RequestMapping("/systems")
public class TeacherController {
    @Value("${degreeList}")
    private List<String> degreeList;

    @Value("${countryList}")
    private List<String> countryList;

    Logger logger=Logger.getLogger(getClass().getName());
    private UserService userService;

    @Autowired
    public TeacherController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add-teacher")
    public String showTeacherRegistrationForm(Model theModel){
        theModel.addAttribute("teacherWebUser",new TeacherWebUser());
        theModel.addAttribute("degreeList",degreeList);
        theModel.addAttribute("countryList",countryList);
        return "teacher/show-registration";
    }

    @PostMapping("/submit-registration")
    public String submitTeacherRegistration(@Valid  @ModelAttribute("teacherWebUser") TeacherWebUser teacherWebUser,
                                            BindingResult bindingResult,Model theModel){
        String userName=teacherWebUser.getUserName();
        logger.info("Processing registration for "+userName);
        if(bindingResult.hasErrors()){
//            System.out.println(bindingResult);
            theModel.addAttribute("degreeList",degreeList);
            theModel.addAttribute("countryList",countryList);
            return "teacher/show-registration";
        }
        return "teacher/registration-confirmation";
    }

}
