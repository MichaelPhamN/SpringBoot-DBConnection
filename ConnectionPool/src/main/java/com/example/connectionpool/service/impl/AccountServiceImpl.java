package com.example.connectionpool.service.impl;

import com.example.connectionpool.dao.AccountDao;
import com.example.connectionpool.model.Account;
import com.example.connectionpool.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    @Qualifier("accountDaoImpl")
    private AccountDao accountDao;

    @Override
    public int addAccount(Account account) throws SQLException {
        return accountDao.addAccount(account);
    }

    @Override
    public int editAccount(Account account) throws SQLException {
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
    public int deleteAccount(Integer id) throws SQLException {
        return accountDao.deleteAccount(id);
    }
}
