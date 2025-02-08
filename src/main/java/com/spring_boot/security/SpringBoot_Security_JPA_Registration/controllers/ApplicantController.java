package com.spring_boot.security.SpringBoot_Security_JPA_Registration.controllers;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.service.ApplicantService;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.ApplicantWebUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ApplicantController {

    private final ApplicantService applicantService;

    @PostMapping("/submit-job-application")
    public String submitJobApplication(@Valid  @ModelAttribute("applicant") ApplicantWebUser applicantWebUser, BindingResult bindingResult, Model model) throws IOException {

        // Check if the file is empty
        if (applicantWebUser.getResume().isEmpty()) {
            bindingResult.rejectValue("resume", "error.applicantWebUser", "Resume file is required");
        }

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
           log.error("Error in submit-job-application");
            return "teacher/appointment";
        }
        //success
        applicantService.submitApplicant(applicantWebUser);
        model.addAttribute("applicant", new ApplicantWebUser());
        model.addAttribute("status", true);
        return "teacher/appointment";
    }
}
