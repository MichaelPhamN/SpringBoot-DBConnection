package com.example.connectionpool.service;

import com.example.connectionpool.model.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountService {
    public int addAccount(Account account) throws SQLException;
    public int editAccount(Account account) throws SQLException;
    public Account findAccountById(Integer id);
    public List<Account> findAccounts();
    public int deleteAccount(Integer id) throws SQLException;
}
