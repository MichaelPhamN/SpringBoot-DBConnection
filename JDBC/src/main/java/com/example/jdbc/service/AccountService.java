package com.example.jdbc.service;

import com.example.jdbc.model.Account;

import java.util.List;

public interface AccountService {
    public int addAccount(Account account);
    public int editAccount(Account account);
    public Account findAccountById(Integer id);
    public List<Account> findAccounts();
    public int deleteAccount(Integer id);
}
