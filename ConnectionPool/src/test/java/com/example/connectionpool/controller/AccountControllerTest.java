package com.example.connectionpool.controller;

import com.example.connectionpool.model.Account;
import com.example.connectionpool.service.impl.AccountServiceImpl;
import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {
    @Mock
    private AccountServiceImpl accountService;
    @InjectMocks
    private AccountController accountController;

    private List<Account> accounts;

    @Before
    public void setUp() {
        Faker faker = new Faker();
        accounts = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Account account = new Account();
            account.setId(faker.random().nextInt(100));
            account.setEmail(faker.internet().emailAddress());
            account.setPassword(faker.internet().password());
            accounts.add(account);
        }
    }

    @Test
    public void testFindAccounts() {
        when(accountService.findAccounts()).thenReturn(accounts);
        ResponseEntity<List<Account>> accountList = accountController.findAccounts();
        HttpStatus expectedStatus = HttpStatus.OK;
        int expectedCollectionSize = 10;
        assertEquals(expectedStatus, accountList.getStatusCode());
        assertEquals(expectedCollectionSize, accountList.getBody().size());
    }

    @Test
    public void testFindAccountById() {
        when(accountService.findAccountById(accounts.get(0).getId())).thenReturn(accounts.get(0));
        ResponseEntity<Account> account = accountController.findAccountById(accounts.get(0).getId());
        HttpStatus expectedStatus = HttpStatus.OK;
        Account acc = accounts.get(0);
        assertEquals(expectedStatus, account.getStatusCode());
        assertEquals(acc.getId(), account.getBody().getId());
        assertEquals(acc.getEmail(), account.getBody().getEmail());
        assertEquals(acc.getPassword(), account.getBody().getPassword());
    }

    @Test
    public void testAddAccount() throws SQLException {
        when(accountService.addAccount(accounts.get(0))).thenReturn(1);
        int row = accountController.addAccount(accounts.get(0));
        assertEquals(1, row);
    }

    @Test
    public void testEditAccount() throws Exception{
        when(accountService.editAccount(accounts.get(0))).thenReturn(1);
        int row = accountController.editAccount(accounts.get(0));
        assertEquals(1, row);
    }

    @Test
    public void testDeleteAccount() throws Exception{
        when(accountService.deleteAccount(accounts.get(0).getId())).thenReturn(1);
        int row = accountController.deleteAccount(accounts.get(0).getId());
        assertEquals(1, row);
    }
}