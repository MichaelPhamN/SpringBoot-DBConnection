package com.example.hibernatetraditionalannotationoracle.service;


import com.example.hibernatetraditionalannotationoracle.model.Account;

import java.util.List;

public interface AccountService {
    public Account findAccountById(Integer id);
    public List<Account> findAccounts();
    public void addAccount(Account account);
    public void editAccount(Account account);
    public void deleteAccount(Account account);
}
