package com.spring_boot.security.SpringBoot_Security_JPA_Registration.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ApplicantWebUser {

    @NotBlank(message = "is required!")
    private String firstName;

    @NotBlank(message = "is required!")
    private String lastName;

    @NotBlank(message = "is required!")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "must be a well formed email address.")
    private String email;

    @NotBlank(message = "is required!")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "must be of 10 digits only.")
    private String mobileNo;

    private String gender;

    @NotBlank(message = "is required!")
    private String qualification;

    private String applyingFor;

    private String address;

//    @NotBlank(message = "is required!")
    private String resume;

    private String message;

}
