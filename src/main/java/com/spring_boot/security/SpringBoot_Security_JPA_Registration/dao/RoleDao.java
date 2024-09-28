package com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Role;

public interface RoleDao {
    public Role findRoleByName(String roleName);
}
