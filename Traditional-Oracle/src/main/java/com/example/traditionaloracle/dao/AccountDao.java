package com.example.traditionaloracle.dao;


import com.example.traditionaloracle.model.Account;

import java.util.List;

public interface AccountDao {
    public int addAccount(Account account);
    public int editAccount(Account account);
    public Account findAccountById(Integer id);
    public List<Account> findAccounts();
    public int deleteAccount(Integer id);
}
