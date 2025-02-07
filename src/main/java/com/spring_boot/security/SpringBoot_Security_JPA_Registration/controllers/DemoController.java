package com.spring_boot.security.SpringBoot_Security_JPA_Registration.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/about")
    public String aboutPage(){
        return "about";
    }

    @GetMapping("/classes")
    public String classesPage(){
        return "classes";
    }

    @GetMapping("/contact")
    public String contactPage(){
        return "contact";
    }

    @GetMapping("/school-facility")
    public String schoolFacilityPage(){
        return "facility";
    }

    @GetMapping("/popular-teachers")
    public String popularTeachersPage(){
        return "team";
    }

    @GetMapping("/become-teacher")
    public String becomeTeacherPage(){
        return "teacher/become-teacher";
    }

@GetMapping("/testimonial")
    public String testimonialPage(){
        return "testimonial";
    }

    @GetMapping("/security-meeting")
    public String securityAwarenessMeeting(){
        return "meetings/security-awareness";
    }

}
