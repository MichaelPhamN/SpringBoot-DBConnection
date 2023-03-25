package com.example.jpaoracle.service;


import com.example.jpaoracle.entity.Account;

import java.util.List;

public interface AccountService {
    public Account findAccountById(Integer id);
    public List<Account> findAccounts();
    public Account addAccount(Account account);
    public Account editAccount(Account account);
    public void deleteAccount(Account account);
}
