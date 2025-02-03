package com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Entity
@Table(name = "student")
@Setter
@Getter
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "student_firstname",nullable = false)
    private String studentFirstName;

    @Column(name = "student_lastname")
    private String studentLastName;

    @Column(name = "guardian_name")
    private  String guardianName;

    @Column(name = "guadian_email",nullable = false)
    private String guadianEmail;

    @Column(name = "mobile_no",length = 10)
    private String mobileNumber;

    @Column(name = "student_gender")
    private String studentGender;

    @Column(name = "student_age")
    private int studentAge;

    @Column(name = "address")
    private String address;

    @Column(name = "country")
    private String country;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false,referencedColumnName = "id")
    private User user;

    //Many-to-many relationship with courses.
    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
}
