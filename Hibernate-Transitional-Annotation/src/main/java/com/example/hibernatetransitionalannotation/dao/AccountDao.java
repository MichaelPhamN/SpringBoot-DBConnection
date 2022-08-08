package com.example.hibernatetransitionalannotation.dao;

import com.example.hibernatetransitionalannotation.model.Account;

import java.util.List;

public interface AccountDao {
    List<Account> findAccounts();
    Account findAccountById(Integer id);
    void addAccount(Account account);
    void editAccount(Account account);
    void deleteAccount(Account account);
}
