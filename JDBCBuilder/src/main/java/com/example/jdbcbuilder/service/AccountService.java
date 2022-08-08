package com.example.jdbcbuilder.service;

import com.example.jdbcbuilder.model.Account;

import java.util.List;

public interface AccountService {
    public int addAccount(Account account);
    public int editAccount(Account account);
    public Account findAccountById(Integer id);
    public List<Account> findAccounts();
    public int deleteAccount(Integer id);
}
