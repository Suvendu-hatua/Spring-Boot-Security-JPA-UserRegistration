package com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.User;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.WebUser;

public interface UserDao {
    public User findUserByName(String userName);

    public void save(User theUser);
}
