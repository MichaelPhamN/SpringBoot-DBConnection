package com.example.hibernate.service.impl;

import com.example.hibernate.dao.impl.AccountDaoImpl;
import com.example.hibernate.model.Account;
import com.example.hibernate.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    @Qualifier("accountDaoImpl")
    AccountDaoImpl accountDao;

    @Override
    public Account findAccountById(Integer id) {
        return accountDao.findAccountById(id);
    }

    @Override
    public List<Account> findAccounts() {
        return accountDao.findAccounts();
    }

    @Override
    public boolean addAccount(Account account) {
        return accountDao.addAccount(account);
    }

    @Override
    public boolean editAccount(Account account) {
        return accountDao.editAccount(account);
    }

    @Override
    public boolean deleteAccount(Account account) {
        return accountDao.deleteAccount(account);
    }
}
