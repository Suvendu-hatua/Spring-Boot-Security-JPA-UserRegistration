package com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String userName);
}
