package com.spring_boot.security.SpringBoot_Security_JPA_Registration.controllers;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.StudentDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.UserDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Student;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.User;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.service.UserService;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.Child;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.ResetPassword;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final StudentDao studentDao;
    private final UserDao userDao;
    private final UserService userService;

    @GetMapping("/show-login")
    public String showLogin() {
        return "custom-signing";
    }

    @GetMapping("/reset-password")
    public String resetPassword(Model model) {
        ResetPassword resetPassword = new ResetPassword();
        //adding resetPassword instance to model
        model.addAttribute("resetPassword", resetPassword);
        return "reset-password";
    }

    @PostMapping("/reset-user-password")
    public String resetPasswordProcessing(@Valid @ModelAttribute("resetPassword") ResetPassword resetPassword,
                                          BindingResult bindingResult, Model model) {
        if (!resetPassword.getPassword().equals(resetPassword.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.confirmPasswordEmpty", "Password must be same");
        }
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "reset-password";
        }
        //Username validation
        User  user = userDao.findByUsername(resetPassword.getUsername());
        if(user==null){
            log.info("user not found");
            model.addAttribute("userNotFound","User not found with username");
            model.addAttribute("resetPassword",new ResetPassword());
            return "reset-password";
        }
        //changing password
        userService.changeUserPassword(user, resetPassword.getConfirmPassword());
        //success
        model.addAttribute("resetPassword", new ResetPassword());
        model.addAttribute("successMessage", "Password Reset Successful");
        return "reset-password";
    }

    @GetMapping("/dashboard")
    public String login(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Extracting username
        String username = auth.getName();
        log.info("Username:{}, Authorities:{}", username, auth.getAuthorities());
        //if the logged-in user is Student
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STUDENT"))) {
            Student student = studentDao.findByUserUsername(username);
            model.addAttribute("student", student);
            //creating new child instance from userDetails
            Child child = new Child(student.getStudentFirstName(), student.getStudentLastName(), student.getStudentAge(), student.getStudentGender());
            model.addAttribute("child", child);
            return "student/complete-profile";
        } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_TEACHER"))) {
            //instance of Teacher
            model.addAttribute("teacher", null);
        } else {
            //redirect to admin dashboard
            return "redirect:/admin/dashboard";
        }
        return "error";
    }


    @GetMapping("access-denied")
    public String accessDeniedPage() {
        return "access-denied";
    }
}
