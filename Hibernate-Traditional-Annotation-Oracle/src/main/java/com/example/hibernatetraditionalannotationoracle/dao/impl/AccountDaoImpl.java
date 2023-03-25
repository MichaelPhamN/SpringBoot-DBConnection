package com.example.hibernatetraditionalannotationoracle.dao.impl;

import com.example.hibernatetraditionalannotationoracle.dao.AbstractDao;
import com.example.hibernatetraditionalannotationoracle.dao.AccountDao;
import com.example.hibernatetraditionalannotationoracle.model.Account;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AccountDaoImpl extends AbstractDao<Account> implements AccountDao {
    public AccountDaoImpl() {
        setClazz(Account.class);
    }

    @Override
    @Transactional
    public List<Account> findAccounts() {
        Query query = this.getCurrentSession().createQuery("FROM Account");
        return query.list();
    }

    @Override
    @Transactional
    public Account findAccountById(Integer id) {
        return findById(id);
    }

    @Override
    @Transactional
    public void addAccount(Account account) {
        this.getCurrentSession().save(account);
    }


    @Override
    @Transactional
    public void editAccount(Account account) {
        Account acct = findById(account.getId());
        acct.setEmail(account.getEmail());
        acct.setPassword(account.getPassword());
    }


    @Override
    @Transactional
    public void deleteAccount(Account account) {
        Account acct = findById(account.getId());
        this.getCurrentSession().remove(acct);
    }
}
