package com.example.jdbc.service.impl;

import com.example.jdbc.dao.impl.AccountDaoImpl;
import com.example.jdbc.model.Account;
import com.example.jdbc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    @Qualifier("accountDaoImpl")
    private AccountDaoImpl accountDao;

    @Override
    public int addAccount(Account account) {
        return accountDao.addAccount(account);
    }

    @Override
    public int editAccount(Account account) {
        return 0;
    }

    @Override
    public Account findAccountById(Integer id) {
        return accountDao.findAccountById(id);
    }

    @Override
    public List<Account> findAccounts() {
        return accountDao.findAccounts();
    }

    @Override
    public int deleteAccount(Integer id) {
        return 0;
    }
}
