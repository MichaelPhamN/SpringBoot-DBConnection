package com.example.jpaoracle.controller;


import com.example.jpaoracle.entity.Account;
import com.example.jpaoracle.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
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

    @DeleteMapping("")
    public void deleteAccount(@RequestBody Account account) {
        accountService.deleteAccount(account);
    }

    @PostMapping("")
    public Account addAccount(@RequestBody Account account) {
        return accountService.addAccount(account);
    }

    @PutMapping("")
    public Account editAccount(@RequestBody Account account) {
        return accountService.editAccount(account);
    }
}
