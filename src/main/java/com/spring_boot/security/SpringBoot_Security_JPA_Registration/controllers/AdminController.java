package com.spring_boot.security.SpringBoot_Security_JPA_Registration.controllers;


import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.ApplicantDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Applicant;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Notice;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.service.ApplicantService;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.service.NoticeService;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ApplicantService applicantService;
    private final TeacherService teacherService;
    private final ApplicantDao applicantDao;
    private final NoticeService noticeService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        //getting all the notices
        List<Notice> notices=noticeService.getAllNotices();
        model.addAttribute("notices", notices);
        return "admin/admin-profile";
    }

    @GetMapping("/dashboard/job-applications")
    public String jobApplications(Model model,
                                  @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        //Getting all the Job Applicant list
        List<Applicant> applicants = applicantDao.findByStatus("ACTIVE");
        //  If it's an AJAX request, return only the fragment
        if ("XMLHttpRequest".equals(requestedWith)) {
            model.addAttribute("activeApplicants", true);
            model.addAttribute("applicants", applicants);
            return "admin/job-applicantions :: job-applicants";
        }
//        If it's a normal page request, return the full dashboard page
        return "admin/admin-profile";
    }

    @GetMapping("/active-applicants")
    public String showActiveApplicants(Model model) {
        List<Applicant> activeApplicants = applicantDao.findByStatus("ACTIVE");
        model.addAttribute("activeApplicants", true);
        model.addAttribute("applicants", activeApplicants);
        return "admin/job-applicantions :: job-applicants";
    }

    @GetMapping("/selected-applicants")
    public String showSelectedApplicants(Model model) {
        List<Applicant> selectedApplicants = applicantDao.findByStatus("SELECTED");
        model.addAttribute("selectedApplicants", true);
        model.addAttribute("applicants", selectedApplicants);
        return "admin/job-applicantions :: job-applicants";
    }

    @GetMapping("/rejected-applicants")
    public String showRejectedApplicants(Model model) {
        List<Applicant> rejectedApplicants = applicantDao.findByStatus("REJECTED");
        model.addAttribute("rejectedApplicants", true);
        model.addAttribute("applicants", rejectedApplicants);
        return "admin/job-applicantions :: job-applicants";
    }

    @GetMapping("/select/{id}")
    public String selectApplicantProfile(@PathVariable("id") long id) {
        Applicant applicant = applicantService.findApplicantById(id);
        //Saving applicant as Teacher
        teacherService.saveAsTeacher(applicant);
        //Update applicant status
        applicantService.changeApplicantStatus(applicant, "SELECTED");
        log.info("Successfully saved as Teacher");
        return "redirect:/dashboard";
    }

    @GetMapping("/reject/{id}")
    public String rejectApplicantProfile(@PathVariable("id") long id) {
        Applicant applicant = applicantService.findApplicantById(id);
        //Update applicant status
        applicantService.changeApplicantStatus(applicant, "REJECTED");
        log.info("Rejected Applicant");
        return "redirect:/dashboard";
    }

}
