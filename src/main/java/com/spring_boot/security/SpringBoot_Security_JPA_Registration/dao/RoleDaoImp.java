package com.spring_boot.security.SpringBoot_Security_JPA_Registration.dao;

import com.spring_boot.security.SpringBoot_Security_JPA_Registration.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImp implements RoleDao{

    private final EntityManager entityManager;

    @Autowired
    public RoleDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role findRoleByName(String roleName) {
        TypedQuery<Role> theQuery =entityManager.createQuery("from Role where roleName=:rName",Role.class);

        theQuery.setParameter("rName",roleName);

        Role role=null;
        try{
            role=theQuery.getSingleResult();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return role;
    }
}
