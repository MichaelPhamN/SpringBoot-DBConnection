package com.example.hibernate.dao.impl;

import com.example.hibernate.dao.AbstractDao;
import com.example.hibernate.dao.AccountDao;
import com.example.hibernate.model.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDaoImpl extends AbstractDao<Account> implements AccountDao {
    public AccountDaoImpl() {
        super(Account.class);
    }

    @Override
    public List<Account> findAccounts() {
        return findAll();
    }

    @Override
    public Account findAccountById(Integer id) {
        return findById(id);
    }

    @Override
    public boolean addAccount(Account account) {
        return add(account);
    }

    @Override
    public boolean editAccount(Account account) {
        return edit(account);
    }

    @Override
    public boolean deleteAccount(Account account) {
        return delete(account);
    }
}
