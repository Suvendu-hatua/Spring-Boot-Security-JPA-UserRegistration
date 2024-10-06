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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/systems")
public class AdminController {

    @Value("${degreeList}")
    private List<String> degreeList;

    @Value("${countryList}")
    private List<String> countryList;

    Logger logger=Logger.getLogger(getClass().getName());
    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
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
    public String submitTeacherRegistration(@Valid @ModelAttribute("teacherWebUser") TeacherWebUser teacherWebUser,
                                            BindingResult bindingResult, Model theModel){
        String userName=teacherWebUser.getUserName();
        logger.info("Processing registration for "+userName);
        if(bindingResult.hasErrors()){
//            System.out.println(bindingResult);
            theModel.addAttribute("degreeList",degreeList);
            theModel.addAttribute("countryList",countryList);
            return "teacher/show-registration";
        }
        //checking is the username is already exists in the DB or not
        User user=userService.findUserByName(userName);
        if(user!=null){
            //******************* already exists in the DB *********************
            //Adding error param.
            theModel.addAttribute("registrationError",true);
            //return a fresh teacherWebUser...
            theModel.addAttribute("teacherWebUser",new TeacherWebUser());
            theModel.addAttribute("degreeList",degreeList);
            theModel.addAttribute("countryList",countryList);
            logger.warning("Username already exists in the Database.");
            return "teacher/show-registration";
        }

        //saving new entry in the database.
        userService.saveAsTeacher(teacherWebUser);
        logger.info("Successfully created user: " + userName);

        //Placing user in http session for later use.

        return "teacher/registration-confirmation";
    }

    @GetMapping("/teacher-list")
    public String showAllTeachers(Model theModel){
        //Retrieving all the Teachers from Database.
        List<Teacher> teacherList=userService.findAllTeachers();
        //adding the list to the model.
        theModel.addAttribute("teacherList",teacherList);
        return "teacher/teacher-list";
    }

    @GetMapping("/it-meeting")
    public String itSystemMeeting(){
        return "meetings/it-system";
    }
}
