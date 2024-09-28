package com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImp implements  UserDao{
    private final EntityManager entityManager;

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
}
