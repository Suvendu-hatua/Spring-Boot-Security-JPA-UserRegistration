package com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Student;

import java.util.List;

public interface StudentDao {

    public List<Student> findAllStudents();
    public Student saveStudent(Student student);
}
