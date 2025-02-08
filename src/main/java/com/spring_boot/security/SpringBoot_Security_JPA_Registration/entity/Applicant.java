package com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@Entity
@Table(name = "applicant")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String qualification;
    private String appliedPosition;
    private LocalDate appliedDate;
    private String gender;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] resume;  // Storing file as byte array in DB

    private String message;
    private String status;
}
