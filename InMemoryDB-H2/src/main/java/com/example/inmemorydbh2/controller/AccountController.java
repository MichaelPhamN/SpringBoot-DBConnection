package com.example.inmemorydbh2.controller;

import com.example.inmemorydbh2.model.Account;
import com.example.inmemorydbh2.service.impl.AccountServiceImpl;
import com.example.inmemorydbh2.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    @Qualifier("accountServiceImpl")
    private AccountServiceImpl accountService;

    @GetMapping("")
    public ResponseEntity<List<Account>> findAccounts() {
        List<Account> accounts = accountService.findAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> findAccountById(@PathVariable int id) {
        Account account = accountService.findAccountById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public int deleteAccount(@PathVariable int id) throws SQLException {
        return accountService.deleteAccount(id);
    }

    @PostMapping("")
    public int addAccount(@RequestBody Account account) throws SQLException {
        int executedRow = accountService.addAccount(account);
        return executedRow;
    }

    @PutMapping("")
    public int editAccount(@RequestBody Account account) throws SQLException {
        int executedRow = accountService.editAccount(account);
        return executedRow;
    }
}
