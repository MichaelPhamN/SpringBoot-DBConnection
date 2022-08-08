package com.example.hibernate.service;

import com.example.hibernate.model.Account;

import java.util.List;

public interface AccountService {
    public Account findAccountById(Integer id);
    public List<Account> findAccounts();
    public boolean addAccount(Account account);
    public boolean editAccount(Account account);
    public boolean deleteAccount(Account account);
}
