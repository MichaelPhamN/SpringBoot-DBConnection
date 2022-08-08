package com.example.traditional.service.impl;

import com.example.traditional.dao.impl.AccountDaoImpl;
import com.example.traditional.model.Account;
import com.example.traditional.service.AccountService;
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
        return accountDao.editAccount(account);
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
        return accountDao.deleteAccount(id);
    }
}
