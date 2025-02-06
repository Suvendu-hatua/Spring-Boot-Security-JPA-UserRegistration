package com.spring_boot.security.SpringBoot_Security_JPA_Registration.service;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.RoleDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.TeacherDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Role;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Teacher;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.User;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.TeacherWebUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {
    private final PasswordEncoder passwordEncoder;
    private final TeacherDao teacherDao;
    private final RoleDao roleDao;

    @Transactional
    public void saveAsTeacher(TeacherWebUser teacherWebUser) {
        //**************creating a user instance ---> will be stored in the DB. *******************
        User user = new User();
        Teacher teacher = new Teacher();
        user.setUsername(teacherWebUser.getUserName());
        user.setPassword(passwordEncoder.encode(teacherWebUser.getPassword()));
        //Setting user enabled column with TRUE manually.
        user.setEnabled(true);
        //Setting roles as ROLE_TEACHER (Default Role)
        Set<Role> set = new HashSet<>();
        set.add(roleDao.findByRoleName("ROLE_TEACHER"));
        user.setRoles(set);
        //saving it to DB.
        teacherDao.save(teacher);
    }
    public List<Teacher> getAllTeachers() {
        return teacherDao.findAll();
    }

    public Teacher getTeacherById(long teacherId) {
        return teacherDao.findById(teacherId).orElseThrow(()->new RuntimeException("teacher not found with id " + teacherId));
    }

}
