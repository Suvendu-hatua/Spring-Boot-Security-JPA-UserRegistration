package com.spring_boot.security.SpringBoot_Security_JPA_Registration.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Child {
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
}
