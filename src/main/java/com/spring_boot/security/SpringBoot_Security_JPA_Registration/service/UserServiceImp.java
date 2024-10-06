package com.spring_boot.security.SpringBoot_Security_JPA_Registration.service;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.RoleDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.StudentDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.TeacherDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao.UserDao;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Role;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Student;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Teacher;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.User;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.StudentWebUser;
import com.spring_boot.security.SpringBoot_Security_JPA_Registration.user.TeacherWebUser;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final StudentDao studentDao;
    private final TeacherDao teacherDao;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserDao userDao, RoleDao roleDao, StudentDao studentDao,
                          TeacherDao teacherDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserByName(String userName) {
        return userDao.findUserByName(userName);
    }

    @Override
    public void saveAsStudent(StudentWebUser studentWebUser) {
        //**************creating a user instance ---> will be stored in the DB. *******************
        User user=new User();
        user.setUsername(studentWebUser.getUserName());
        user.setPassword(passwordEncoder.encode(studentWebUser.getPassword()));
        //Setting user enabled column with TRUE manually.
        user.setEnabled(true);
        //Setting roles as ROLE_EMPLOYEE (Default Role)
        Set<Role> set=new HashSet<>();
        set.add(roleDao.findRoleByName("ROLE_STUDENT"));
        user.setRoles(set);

        //saving user to database.
        user=userDao.save(user);

        //Set User to Student and Save the Student
        Student student = getStudent(studentWebUser, user);

        //saving it to DB.
        studentDao.saveStudent(student);


    }

    private static Student getStudent(StudentWebUser studentWebUser, User user) {
        Student student=new Student();
        student.setFirstName(studentWebUser.getFirstName());
        student.setLastName(studentWebUser.getLastName());
        student.setEmail(studentWebUser.getEmail());
        student.setMobileNumber(studentWebUser.getMobileNo());
        student.setAddress(studentWebUser.getAddress());
        student.setCountry(studentWebUser.getCountry());
        student.setCourse(studentWebUser.getCourse());
        student.setGender(studentWebUser.getGender());
        student.setUser(user);
        return student;
    }

    @Override
    public void saveAsTeacher(TeacherWebUser teacherWebUser) {
        //**************creating a user instance ---> will be stored in the DB. *******************
        User user=new User();
        user.setUsername(teacherWebUser.getUserName());
        user.setPassword(passwordEncoder.encode(teacherWebUser.getPassword()));
        //Setting user enabled column with TRUE manually.
        user.setEnabled(true);
        //Setting roles as ROLE_TEACHER (Default Role)
        Set<Role> set=new HashSet<>();
        set.add(roleDao.findRoleByName("ROLE_TEACHER"));
        user.setRoles(set);

        //saving user to database.
        user=userDao.save(user);

        //Set User to Student and Save the Teacher
        Teacher teacher = getTeacher(teacherWebUser, user);

        //saving it to DB.
        teacherDao.saveTeacher(teacher);

    }

    @Override
    public List<Teacher> findAllTeachers() {
        return teacherDao.findAllTeachers();
    }

    @Override
    public List<Student> findAllStudents() {
        return studentDao.findAllStudents();
    }

    @Override
    public Teacher findTeacherById(Integer id) {
        return teacherDao.findById(id);
    }

    private Teacher getTeacher(TeacherWebUser teacherWebUser, User user) {

        Teacher teacher=new Teacher();
        teacher.setFirstName(teacherWebUser.getFirstName());
        teacher.setLastName(teacherWebUser.getLastName());
        teacher.setEmail(teacherWebUser.getEmail());
        teacher.setMobileNumber(teacherWebUser.getMobileNo());
        teacher.setAddress(teacherWebUser.getAddress());
        teacher.setCountry(teacherWebUser.getCountry());
        teacher.setDegree(teacherWebUser.getDegree());
        teacher.setGender(teacherWebUser.getGender());
        teacher.setReview(teacherWebUser.getReview());
        teacher.setUser(user);
        return teacher;
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
