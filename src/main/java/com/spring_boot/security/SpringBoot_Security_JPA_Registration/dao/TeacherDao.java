package com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherDao extends JpaRepository<Teacher, Long> {

    Teacher findByUserUsername(String username);
}
