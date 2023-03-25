package com.example.jdbc.controller;

import com.example.jdbc.exception.DataNotFoundException;
import com.example.jdbc.model.Account;
import com.example.jdbc.service.impl.AccountServiceImpl;
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
//    @Qualifier("accountServiceImpl")
    private AccountServiceImpl accountService;

    @GetMapping("")
    public ResponseEntity<List<Account>> findAccounts() {
        List<Account> accounts = accountService.findAccounts();
        if(accounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> findAccountById(@PathVariable int id) {
        Account account = accountService.findAccountById(id);
        if(account == null) {
            throw new DataNotFoundException("Data not found");
        }
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable int id) {
        Account account = accountService.findAccountById(id);
        if(account == null) {
            throw new DataNotFoundException("Data not found");
        }
        int executed = accountService.deleteAccount(id);
        return executed != 0 ? new ResponseEntity<>("Delete account successful", HttpStatus.NO_CONTENT)
                : new ResponseEntity<>("Delete account failure", HttpStatus.BAD_REQUEST);

    }

    @PostMapping("")
    public ResponseEntity<String> addAccount(@RequestBody Account account) {
        int executed = accountService.addAccount(account);
        return executed != 0 ? new ResponseEntity<>("Add account successful", HttpStatus.CREATED)
                : new ResponseEntity<>("Add account failure", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("")
    public ResponseEntity<String> editAccount(@RequestBody Account acct) {
        Account account = accountService.findAccountById(acct.getId());
        if(account == null) {
            throw new DataNotFoundException("Data not found");
        }
        account.setId(acct.getId());
        account.setEmail(acct.getEmail());
        account.setPassword(acct.getPassword());
        int executed = accountService.editAccount(account);
        return executed != 0 ? new ResponseEntity<>("Edit account successful", HttpStatus.OK)
                : new ResponseEntity<>("Edit account failure", HttpStatus.BAD_REQUEST);
    }
}
