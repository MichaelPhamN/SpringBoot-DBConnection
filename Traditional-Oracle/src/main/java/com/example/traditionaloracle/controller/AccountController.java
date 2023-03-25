package com.example.traditionaloracle.controller;

import com.example.traditionaloracle.model.Account;
import com.example.traditionaloracle.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public int deleteAccount(@PathVariable int id) {
        return accountService.deleteAccount(id);
    }

    @PostMapping("")
    public int addAccount(@RequestBody Account account) {
        int executedRow = accountService.addAccount(account);
        return executedRow;
    }

    @PutMapping("")
    public int editAccount(@RequestBody Account account) {
        int executedRow = accountService.editAccount(account);
        return executedRow;
    }
}
