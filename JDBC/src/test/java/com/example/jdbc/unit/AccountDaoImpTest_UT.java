package com.example.jdbc.unit;

import com.example.jdbc.dao.impl.AccountDaoImpl;
import com.example.jdbc.exception.DataAccessException;
import com.example.jdbc.model.Account;
import com.example.jdbc.rowmapper.AccountRowMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AccountDaoImpTest_UT {
    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Mock
    private MapSqlParameterSource parameterSource;
    @Captor
    private ArgumentCaptor<MapSqlParameterSource> mapSqlParameterSourceArgumentCaptor;
    @Captor
    private ArgumentCaptor<AccountRowMapper> argumentCaptor;
    @InjectMocks
    private AccountDaoImpl accountDao;

    private static final String SELECT_ACCOUNTS = "SELECT acc.id, acc.email, acc.password FROM Account as acc";
    private static final String SELECT_ACCOUNT_BY_ID = "SELECT acc.id, acc.email, acc.password FROM Account as acc WHERE acc.id = :acctId";
    private static final String ADD_ACCOUNT = "INSERT INTO ACCOUNT(email, password) VALUES (:email, :password)";
    private static final String EDIT_ACCOUNT = "UPDATE ACCOUNT as acc SET acc.email = :acctEmail, acc.password = :acctPassword WHERE acc.id = :acctId";
    private static final String DELETE_ACCOUNT = "DELETE FROM ACCOUNT as acc WHERE acc.id = :acctId";

    @Test
    public void testFindAccounts_ReturnEmptyList() {
        when(namedParameterJdbcTemplate.query(eq(SELECT_ACCOUNTS), argumentCaptor.capture())).thenReturn(Collections.emptyList());
        List<Account> actualReturn = accountDao.findAccounts();
        verify(namedParameterJdbcTemplate).query(eq(SELECT_ACCOUNTS), argumentCaptor.capture());
        Assert.assertEquals(0 , actualReturn.size());
    }

    @Test
    public void testFindAccounts_ThrowException() {
        when(namedParameterJdbcTemplate.query(eq(SELECT_ACCOUNTS), argumentCaptor.capture())).thenThrow(DataAccessException.class);
        Exception exception = Assert.assertThrows(DataAccessException.class, () -> {
            accountDao.findAccounts();
        });
        Assert.assertNotNull(exception);
        Assert.assertEquals("Issue when fetching data", exception.getMessage());
    }

    @Test
    public void testFindAccounts() {
        List<Account> accounts = new ArrayList<>();
        when(namedParameterJdbcTemplate.query(eq(SELECT_ACCOUNTS), argumentCaptor.capture())).thenReturn(accounts);
        List<Account> actualReturn = accountDao.findAccounts();
        verify(namedParameterJdbcTemplate).query(eq(SELECT_ACCOUNTS), argumentCaptor.capture());
        Assert.assertNotNull(actualReturn);
        Assert.assertEquals(accounts , actualReturn);
    }

    @Test
    public void testFindAccountById() {
        Account account = new Account();
        when(namedParameterJdbcTemplate.queryForObject(eq(SELECT_ACCOUNT_BY_ID), mapSqlParameterSourceArgumentCaptor.capture(), argumentCaptor.capture()))
                .thenReturn(account);
        Account actualReturn = accountDao.findAccountById(1);
        verify(namedParameterJdbcTemplate).queryForObject(eq(SELECT_ACCOUNT_BY_ID), mapSqlParameterSourceArgumentCaptor.capture(),argumentCaptor.capture());
        Assert.assertNotNull(actualReturn);
        Assert.assertEquals(account, actualReturn);
    }

    @Test
    public void testFindAccountById_ThrowException() {
        when(namedParameterJdbcTemplate.queryForObject(eq(SELECT_ACCOUNT_BY_ID), mapSqlParameterSourceArgumentCaptor.capture(), argumentCaptor.capture())).thenThrow(DataAccessException.class);
        Exception exception = Assert.assertThrows(DataAccessException.class, () -> {
            accountDao.findAccountById(1);
        });
        Assert.assertNotNull(exception);
        Assert.assertEquals("Issue when fetching data", exception.getMessage());
    }

    @Test
    public void testAddAccount() {
        Account account = new Account();
        when(namedParameterJdbcTemplate.update(eq(ADD_ACCOUNT), mapSqlParameterSourceArgumentCaptor.capture())).thenReturn(1);
        int actualReturn = accountDao.addAccount(account);
        verify(namedParameterJdbcTemplate).update(eq(ADD_ACCOUNT), mapSqlParameterSourceArgumentCaptor.capture());
        Assert.assertEquals(1, actualReturn);
    }

    @Test
    public void testAddAccount_ThrowException() {
        Account account = new Account();
        when(namedParameterJdbcTemplate.update(eq(ADD_ACCOUNT), mapSqlParameterSourceArgumentCaptor.capture())).thenThrow(DataAccessException.class);
        Exception exception = Assert.assertThrows(DataAccessException.class, () -> {
            accountDao.addAccount(account);
        });
        Assert.assertNotNull(exception);
        Assert.assertEquals("Data cannot execute", exception.getMessage());
    }

    @Test
    public void testEditAccount() {
        Account account = new Account();
        when(namedParameterJdbcTemplate.update(eq(EDIT_ACCOUNT), mapSqlParameterSourceArgumentCaptor.capture())).thenReturn(1);
        int actualReturn = accountDao.editAccount(account);
        verify(namedParameterJdbcTemplate).update(eq(EDIT_ACCOUNT), mapSqlParameterSourceArgumentCaptor.capture());
        Assert.assertEquals(1, actualReturn);
    }

    @Test
    public void testEditAccount_ThrowException() {
        Account account = new Account();
        when(namedParameterJdbcTemplate.update(eq(EDIT_ACCOUNT), mapSqlParameterSourceArgumentCaptor.capture())).thenThrow(DataAccessException.class);
        Exception exception = Assert.assertThrows(DataAccessException.class, () -> {
            accountDao.editAccount(account);
        });
        Assert.assertNotNull(exception);
        Assert.assertEquals("Data cannot execute", exception.getMessage());
    }

    @Test
    public void testDeleteAccount() {
        when(namedParameterJdbcTemplate.update(eq(DELETE_ACCOUNT), mapSqlParameterSourceArgumentCaptor.capture())).thenReturn(1);
        int actualReturn = accountDao.deleteAccount(1);
        verify(namedParameterJdbcTemplate).update(eq(DELETE_ACCOUNT), mapSqlParameterSourceArgumentCaptor.capture());
        Assert.assertEquals(1, actualReturn);
    }

    @Test
    public void testDeleteAccount_ThrowException() {
        when(namedParameterJdbcTemplate.update(eq(DELETE_ACCOUNT), mapSqlParameterSourceArgumentCaptor.capture())).thenThrow(DataAccessException.class);
        Exception exception = Assert.assertThrows(DataAccessException.class, () -> {
            accountDao.deleteAccount(1);
        });
        Assert.assertNotNull(exception);
        Assert.assertEquals("Data cannot execute", exception.getMessage());
    }
}
