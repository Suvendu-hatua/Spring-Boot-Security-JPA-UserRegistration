package com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.User;

public interface UserDao {
    public User findUserByName(String userName);

    public User save(User theUser);
}
