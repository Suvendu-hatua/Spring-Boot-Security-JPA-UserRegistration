package com.spring_boot.security.SpringBoot_Security_JPA_Registration.controllers;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Applicant;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.service.ApplicantService;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.ApplicantWebUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/resume/{id}")
    public ResponseEntity<byte[]> viewResume(@PathVariable("id") long id){
        Applicant applicant = applicantService.findApplicantById(id);
        if (applicant == null || applicant.getResume() == null) {
            return ResponseEntity.notFound().build();
        }

        String fileName = applicant.getFirstName() + "_Resume.pdf"; // Custom filename
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                .body(applicant.getResume());
    }
}
