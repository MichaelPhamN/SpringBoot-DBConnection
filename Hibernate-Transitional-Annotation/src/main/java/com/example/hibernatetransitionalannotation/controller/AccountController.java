package com.example.hibernatetransitionalannotation.controller;

import com.example.hibernatetransitionalannotation.model.Account;
import com.example.hibernatetransitionalannotation.service.impl.AccountServiceImpl;
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

    @DeleteMapping("")
    public void deleteAccount(@RequestBody Account account) {
        accountService.deleteAccount(account);
    }

    @PostMapping("")
    public void addAccount(@RequestBody Account account) {
        accountService.addAccount(account);

    }

    @PutMapping("")
    public void editAccount(@RequestBody Account account) {
        accountService.editAccount(account);
    }
}
