package com.spring_boot.security.SpringBoot_Security_JPA_Registration.service;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.ApplicantDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Applicant;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.ApplicantWebUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplicantService {
    private final ApplicantDao applicantDao;

    @Transactional
    public void submitApplicant(ApplicantWebUser applicantWebUser) throws IOException {
        //creating an instance of Applicant entity
        Applicant applicant = new Applicant();
        //setting instance members
        applicant.setFirstName(applicantWebUser.getFirstName());
        applicant.setLastName(applicantWebUser.getLastName());
        applicant.setEmail(applicantWebUser.getEmail());
        applicant.setPhone(applicantWebUser.getMobileNo());
        applicant.setAddress(applicantWebUser.getAddress());
        applicant.setGender(applicantWebUser.getGender());
        applicant.setMessage(applicantWebUser.getMessage());
        applicant.setQualification(applicantWebUser.getQualification());
        applicant.setAppliedPosition(applicantWebUser.getApplyingFor());
        applicant.setAppliedDate(LocalDate.now());
        applicant.setStatus("ACTIVE");
        //handling with resume
        MultipartFile file=applicantWebUser.getResume();
        if(file!=null){
            applicant.setResume(file.getBytes());
        }
        //saving applicant into DB
        applicantDao.save(applicant);
    }

    public Applicant findApplicantById(long id) {
        return applicantDao.findById(id).orElse(null);
    }
}
