package com.example.hibernatetransitionalannotation.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.io.Serializable;

public abstract class AbstractDao<T extends Serializable> {
    @Autowired
    protected SessionFactory sessionFactory;
    protected Class<T> clazz;
    protected final void setClazz(final Class<T> clazzToSet) {
        clazz = clazzToSet;
    }
    public T findById(final Integer id) {
        return getCurrentSession().find(clazz, id);
    }
    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
