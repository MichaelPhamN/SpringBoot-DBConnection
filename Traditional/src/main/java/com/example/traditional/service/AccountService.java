package com.example.traditional.service;

import com.example.traditional.model.Account;

import java.util.List;

public interface AccountService {
    public int addAccount(Account account);
    public int editAccount(Account account);
    public Account findAccountById(Integer id);
    public List<Account> findAccounts();
    public int deleteAccount(Integer id);
}
