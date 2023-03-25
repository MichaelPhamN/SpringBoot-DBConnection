package com.example.connectionpool.service.impl;

import com.example.connectionpool.dao.impl.AccountDaoImpl;
import com.example.connectionpool.model.Account;
import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {
    @InjectMocks
    private AccountServiceImpl accountService;
    @Mock
    private AccountDaoImpl accountDao;
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
    public void testAddAccount() throws SQLException {
        when(accountDao.addAccount(accounts.get(0))).thenReturn(1);
        int expectedResponse = 1;
        assertEquals(expectedResponse, accountService.addAccount(accounts.get(0)));
    }

    @Test
    public void testEditAccount() throws SQLException {
        when(accountDao.editAccount(accounts.get(0))).thenReturn(1);
        int expectedResponse = 1;
        assertEquals(expectedResponse, accountService.editAccount(accounts.get(0)));
    }

    @Test
    public void testFindAccountById() throws SQLException {
        when(accountDao.findAccountById(accounts.get(0).getId())).thenReturn(accounts.get(0));
        assertEquals(accounts.get(0).getId(), accountService.findAccountById(accounts.get(0).getId()).getId());
        assertEquals(accounts.get(0).getEmail(), accountService.findAccountById(accounts.get(0).getId()).getEmail());
        assertEquals(accounts.get(0).getPassword(), accountService.findAccountById(accounts.get(0).getId()).getPassword());
    }

    @Test
    public void findUsers() throws SQLException {
        when(accountDao.findAccounts()).thenReturn(accounts);
        int expectedResponse = 10;
        assertEquals(expectedResponse, accountService.findAccounts().size());
    }

    @Test
    public void deleteUser() throws SQLException {
        when(accountDao.deleteAccount(accounts.get(0).getId())).thenReturn(1);
        int expectedResponse = 1;
        assertEquals(expectedResponse, accountService.deleteAccount(accounts.get(0).getId()));
    }
}