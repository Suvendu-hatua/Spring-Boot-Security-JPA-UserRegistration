package com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDaoImp implements StudentDao{
    private EntityManager entityManager;

    @Autowired
    public StudentDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Student> findAllStudents() {
        TypedQuery<Student> query =entityManager.createQuery("from Student",Student.class);
        List<Student> ll=null;
        try {
            ll = query.getResultList();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ll;
    }

    @Override
    @Transactional
    public Student saveStudent(Student student) {
        Student stu=null;
        try{
            System.out.println(student);
            stu =entityManager.merge(student);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return stu;
    }
}
