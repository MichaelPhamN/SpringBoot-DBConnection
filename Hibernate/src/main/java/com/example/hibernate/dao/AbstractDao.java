package com.example.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractDao<T extends Serializable> {
    @Autowired
    protected SessionFactory sessionFactory;

    protected Class<T> entity;

    public AbstractDao(Class<T> entity) {
        this.entity = entity;
    }

    public List<T> findAll() {
        List<T> result = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            result = session.createQuery("from " + entity.getName()).list();
            transaction.commit();
        } catch (Exception e) {
            result = null;
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    public T findById(Object id) {
        T result = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            result = (T) session.get(entity, (Serializable) id);
            transaction.commit();
        } catch (Exception e) {
            result = null;
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    public boolean add(T entity) {
        boolean result = true;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            result = false;
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    public boolean edit(T entity) {
        boolean result = true;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            result = false;
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    public boolean delete(T entity) {
        boolean result = true;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            result = false;
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
