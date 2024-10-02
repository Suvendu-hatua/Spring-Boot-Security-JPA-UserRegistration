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

    @AssertTrue(message = "You must accept terms and conditions.")
    private boolean selected;

    public @NotBlank(message = "is required!") @Size(min = 3, message = "username must be of at least 3 characters.") String getUserName() {
        return userName;
    }

    public void setUserName(@NotBlank(message = "is required!") @Size(min = 3, message = "username must be of at least 3 characters.") String userName) {
        this.userName = userName;
    }

    public @NotBlank(message = "is required!") @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$", message = "Password must be of 6 sizes and have at least 1 char,digit and a special char.") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "is required!") @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$", message = "Password must be of 6 sizes and have at least 1 char,digit and a special char.") String password) {
        this.password = password;
    }

    public @NotBlank(message = "is required!") @Size(min = 2, message = "first name must be of at least 2 characters.") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "is required!") @Size(min = 2, message = "first name must be of at least 2 characters.") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "is required!") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "is required!") String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank(message = "is required!") @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "must be a well formed email address.") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "is required!") @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "must be a well formed email address.") String email) {
        this.email = email;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    //implementing toString() Method -->

    @Override
    public String toString() {
        return "WebUser{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", isAgreed=" + selected +
                '}';
    }
}
