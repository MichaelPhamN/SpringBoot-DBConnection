package com.example.connectionpool.dao;

import com.example.connectionpool.model.Account;

import java.util.List;

public interface AccountDao {
    public int addAccount(Account account);
    public int editAccount(Account account);
    public Account findAccountById(Integer id);
    public List<Account> findAccounts();
    public int deleteAccount(Integer id);
}
