package com.spring_boot.security.SpringBoot_Security_JPA_Registration.service;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.StudentDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.TeacherDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.UserDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Role;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Student;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Teacher;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserDao userDao;
    private final TeacherDao teacherDao;
    private  final StudentDao studentDao;

    public User findUserByName(String userName) {
        return userDao.findByUsername(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userDao.findByUsername(username);
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

    public Object getUserDetailsByUsername(String username) {

        //Getting Teacher details from user's username
        Teacher teacher=teacherDao.findByUserUsername(username);
        if(teacher!=null){
            return teacher;
        }
        //Getting Student details from user's username
        Student student=studentDao.findByUserUsername(username);
        if(student!=null){
            return student;
        }
        return null;
    }

}
