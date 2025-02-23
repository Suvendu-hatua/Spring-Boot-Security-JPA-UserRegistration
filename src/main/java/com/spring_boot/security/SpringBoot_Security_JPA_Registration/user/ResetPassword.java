package com.spring_boot.security.SpringBoot_Security_JPA_Registration.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ResetPassword {
    @NotBlank(message = "is required!")
    private  String username;
    @NotBlank(message = "is required!")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
            message = "Password must be of 6 sizes and have at least 1 char,digit and a special char.")
    private String password;

    @NotBlank(message = "is required!")
    private String confirmPassword;
}
