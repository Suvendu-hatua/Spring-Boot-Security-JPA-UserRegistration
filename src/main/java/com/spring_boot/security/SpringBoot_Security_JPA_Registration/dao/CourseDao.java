package com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDao extends JpaRepository<Course, Long> {
}
