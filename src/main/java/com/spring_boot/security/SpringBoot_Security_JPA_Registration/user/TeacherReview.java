package com.spring_boot.security.SpringBoot_Security_JPA_Registration.user;

public class TeacherReview {
    private Long id;
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TeacherReview{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
