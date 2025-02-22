package com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "teacher")
@Setter
@Getter
@ToString
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname",nullable = false)
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_no",length = 10)
    private String mobileNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "country")
    private String country;

    @Column(name = "qualification")
    private String qualification;

    private String position;

    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",nullable = false,referencedColumnName = "id")
    private User user;

  public void setTeacher(Applicant applicant) {
      this.firstName = applicant.getFirstName();
      this.lastName = applicant.getLastName();
      this.email = applicant.getEmail();
      this.mobileNumber=applicant.getPhone();
      this.gender = applicant.getGender();
      this.address = applicant.getAddress();
      this.qualification = applicant.getQualification();
      this.position=applicant.getAppliedPosition();
  }


}
