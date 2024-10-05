package com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TeacherDaoImp implements  TeacherDao{
    private EntityManager entityManager;

    @Autowired
    public TeacherDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Teacher> findAllTeachers() {
        TypedQuery<Teacher> query=entityManager.createQuery("from Teacher",Teacher.class);
        List<Teacher>ll=null;
        try{
            ll=query.getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ll;
    }

    @Override
    @Transactional
    public Teacher saveTeacher(Teacher teacher) {
        Teacher tec=null;
        try{
            tec=entityManager.merge(teacher);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return tec;
    }
}
