package com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImp implements  UserDao{
    private final EntityManager entityManager;

    @Autowired
    public UserDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public User findUserByName(String userName) {
        TypedQuery<User> theQuery =entityManager.createQuery("from User where username=:uname and enabled=true",User.class);
        theQuery.setParameter("uname",userName);

        User user=null;
        try{
            user=theQuery.getSingleResult();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    @Transactional
    public User save(User theUser) {
        User user=null;
        try{
            user=entityManager.merge(theUser);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return user;
    }
}
