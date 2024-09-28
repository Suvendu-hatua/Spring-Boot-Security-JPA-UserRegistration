package com.spring_boot.security.SpringBoot_Security_JPA_Registration.user;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class WebUser {
    @NotBlank(message = "is required!")
    @Size(min = 3,message = "username must be of at least 3 characters.")
    private String userName;

    @NotBlank(message = "is required!")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",message = "Password must be of 6 sizes and have at least 1 char,digit and a special char.")
    private String password;

    @NotBlank(message = "is required!")
    @Size(min = 2,message = "first name must be of at least 2 characters.")
    private String firstName;

    @NotBlank(message = "is required!")
    private String lastName;

    @NotBlank(message = "is required!")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "must be a well formed email address.")
    private String email;

    @AssertTrue(message = "you must agree trams and conditions.")
    private boolean isAgreed;
}
