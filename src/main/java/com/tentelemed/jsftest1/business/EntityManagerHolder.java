package com.tentelemed.jsftest1.business;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 21/02/13
 * Time: 00:05
 */
//@Component(value = "emholder")
public class EntityManagerHolder {

    @PersistenceContext
    EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
