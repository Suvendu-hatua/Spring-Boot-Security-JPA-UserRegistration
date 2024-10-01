package com.spring_boot.security.SpringBoot_Security_JPA_Registration.service;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.RoleDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.UserDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Role;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.User;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.WebUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImp implements  UserService{
    private final UserDao userDao;
    private final RoleDao roleDao;

    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImp(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public UserServiceImp(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }


    @Override
    public User findUserByName(String userName) {
        return userDao.findUserByName(userName);
    }

    @Override
    public void save(WebUser webUser) {
        //creating an user instance ---> will be stored in the DB.
        User user=new User();
        user.setUsername(webUser.getUserName());
        user.setPassword(passwordEncoder.encode(webUser.getPassword()));
        user.setFirstName(webUser.getFirstName());
        user.setLastName(webUser.getLastName());
        user.setEmail(webUser.getEmail());
        //Setting user enabled column with TRUE manually.
        user.setEnabled(true);

        //Setting roles as ROLE_EMPLOYEE (Default Role)
        Set<Role> set=new HashSet<>(List.of(roleDao.findRoleByName("ROLE_EMPLOYEE")));
        user.setRoles(set);

        //saving user to database.
        userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userDao.findUserByName(username);
        //if user does not exist in the database.
        if(user==null){
            throw new UsernameNotFoundException("Invalid UserName or Password!");
        }
        Collection<SimpleGrantedAuthority> authorities=mapRolesToAuthorities(user.getRoles());
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role tempRole : roles) {
            SimpleGrantedAuthority tempAuthority = new SimpleGrantedAuthority(tempRole.getRoleName());
            authorities.add(tempAuthority);
        }

        return authorities;
    }

}
