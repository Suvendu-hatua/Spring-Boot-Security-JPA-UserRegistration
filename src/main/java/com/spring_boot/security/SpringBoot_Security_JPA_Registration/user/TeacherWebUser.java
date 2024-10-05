package com.spring_boot.security.SpringBoot_Security_JPA_Registration.user;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class TeacherWebUser {
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

    @NotBlank(message = "is required!")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "must be of 10 digits only.")
    private String mobileNo;

    @NotBlank(message = "is required!")
    private String gender;

    @NotBlank(message = "is required!")
    private String degree;

    private String address;

    private String country;

    private String review;

    @AssertTrue(message = "You must accept terms and conditions.")
    private boolean selected;

    //setter and getter

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

    public @NotBlank(message = "is required!") @Pattern(regexp = "^[6-9]\\d{9}$", message = "must be of 10 digits only.") String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(@NotBlank(message = "is required!") @Pattern(regexp = "^[6-9]\\d{9}$", message = "must be of 10 digits only.") String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public @NotBlank(message = "is required!") String getGender() {
        return gender;
    }

    public void setGender(@NotBlank(message = "is required!") String gender) {
        this.gender = gender;
    }

    public @NotBlank(message = "is required!") String getDegree() {
        return degree;
    }

    public void setDegree(@NotBlank(message = "is required!") String degree) {
        this.degree = degree;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @AssertTrue(message = "You must accept terms and conditions.")
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(@AssertTrue(message = "You must accept terms and conditions.") boolean selected) {
        this.selected = selected;
    }

    //toString()

    @Override
    public String toString() {
        return "TeacherWebUser{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", gender='" + gender + '\'' +
                ", degree='" + degree + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", review='" + review + '\'' +
                ", selected=" + selected +
                '}';
    }
}
