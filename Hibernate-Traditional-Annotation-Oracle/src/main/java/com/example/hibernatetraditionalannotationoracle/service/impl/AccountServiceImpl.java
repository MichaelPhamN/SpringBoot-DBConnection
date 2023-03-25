package com.example.hibernatetraditionalannotationoracle.service.impl;

import com.example.hibernatetraditionalannotationoracle.dao.impl.AccountDaoImpl;
import com.example.hibernatetraditionalannotationoracle.model.Account;
import com.example.hibernatetraditionalannotationoracle.service.AccountService;
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
    public void addAccount(Account account) {
        accountDao.addAccount(account);
    }

    @Override
    public void editAccount(Account account) {
        accountDao.editAccount(account);
    }

    @Override
    public void deleteAccount(Account account) {
        accountDao.deleteAccount(account);
    }
}
