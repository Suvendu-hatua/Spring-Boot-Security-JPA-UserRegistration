package com.spring_boot.security.SpringBoot_Security_JPA_Registration.service;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Teacher;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.User;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.StudentWebUser;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.TeacherWebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User findUserByName(String userName);
    public void saveAsStudent(StudentWebUser studentWebUser);
    public void saveAsTeacher(TeacherWebUser teacherWebUser);
}
