package com.spring_boot.security.SpringBoot_Security_JPA_Registration.service;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.RoleDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.StudentDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Role;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Student;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.User;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.Child;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.Guardian;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {
    private final PasswordEncoder passwordEncoder;
    private  final RoleDao roleDao;
    private final StudentDao studentDao;

    @Transactional
    public void saveAsStudent(Guardian guardian) {
        //**************creating a Student and User instance ---> will be stored in the DB. *******************
        Student student = new Student();
        User user = new User();
        user.setUsername(guardian.getUserName());
        user.setPassword(passwordEncoder.encode(guardian.getPassword()));
        //Setting user enabled column with TRUE manually.
        user.setEnabled(true);
        //Setting roles as ROLE_EMPLOYEE (Default Role)
        Set<Role> set = new HashSet<>();
        set.add(roleDao.findByRoleName("ROLE_STUDENT"));
        user.setRoles(set);

        //setting  student data
        student.setGuardianName(guardian.getGuardianName());
        student.setGuardianEmail(guardian.getGuardianEmail());
        student.setAddress(guardian.getAddress());
        student.setMobileNumber(guardian.getMobileNo());
        student.setCountry(guardian.getCountry());

        //Adding user instance to student instance
        student.setUser(user);
        //saving it to DB.
        studentDao.save(student);
    }

    @Transactional
    public void addChildDetails(Child child,String username) {
        //finding student details from username
        Student student=studentDao.findByUserUsername(username);
        //updating student instance with child information
        student.setStudentFirstName(child.getFirstName());
        student.setStudentLastName(child.getLastName());
        student.setStudentAge(child.getAge());
        student.setStudentGender(child.getGender());
        //saving changes
        studentDao.save(student);
    }
}
