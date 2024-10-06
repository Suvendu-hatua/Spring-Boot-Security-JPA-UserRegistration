package com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Teacher;

import java.util.List;

public interface TeacherDao {

    public List<Teacher> findAllTeachers();
    public Teacher saveTeacher(Teacher teacher);

    public Teacher findById(Integer id);
}
