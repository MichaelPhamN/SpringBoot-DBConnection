package com.example.hibernate.dao;

import com.example.hibernate.model.Account;

import java.util.List;

public interface AccountDao {
    List<Account> findAccounts();
    Account findAccountById(Integer id);
    boolean addAccount(Account account);
    boolean editAccount(Account account);
    boolean deleteAccount(Account account);
}
