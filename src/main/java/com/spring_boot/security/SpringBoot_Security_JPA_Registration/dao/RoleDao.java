package com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Long> {
    Role findByRoleName(String roleStudent);
}
