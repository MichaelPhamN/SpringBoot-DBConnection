package com.example.jdbc.unit;

import com.example.jdbc.dao.impl.AccountDaoImpl;
import com.example.jdbc.model.Account;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AccountDaoImpUnitTest {
    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Captor
    private ArgumentCaptor<MapSqlParameterSource> mapSqlParameterSourceArgumentCaptor;

    @Test
    public void whenMockNamedParameterJdbcTemplate_thenReturnAnArrayAccount() {
        AccountDaoImpl accountDao = new AccountDaoImpl();
        ReflectionTestUtils.setField(accountDao, "namedParameterJdbcTemplate", namedParameterJdbcTemplate);
        when(namedParameterJdbcTemplate.query(eq("SELECT acc.id, acc.email, acc.password FROM Account as acc"), any(RowMapper.class))).thenReturn(Collections.emptyList());
        List<Account> actualReturn = accountDao.findAccounts();
        verify(namedParameterJdbcTemplate).query(anyString(), any(RowMapper.class));
        Assert.assertNotNull(actualReturn);
    }
    @Test
    public void testfindAccounts() {
        AccountDaoImpl accountDao = new AccountDaoImpl();
        ReflectionTestUtils.setField(accountDao, "namedParameterJdbcTemplate", namedParameterJdbcTemplate);
        when(namedParameterJdbcTemplate.query(anyString(), any(RowMapper.class)))
                .thenReturn(Collections.emptyList());
        List<Account> actualReturn = accountDao.findAccounts();
        verify(namedParameterJdbcTemplate).query(anyString(), any(RowMapper.class));
        Assert.assertNotNull(actualReturn);
    }

    @Test
    public void testfindAccountById() {
        AccountDaoImpl accountDao = new AccountDaoImpl();
        ReflectionTestUtils.setField(accountDao, "namedParameterJdbcTemplate", namedParameterJdbcTemplate);
        Account account = new Account();
        when(namedParameterJdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), any(RowMapper.class)))
                .thenReturn(account);
        Account actualReturn = accountDao.findAccountById(1);
        verify(namedParameterJdbcTemplate).queryForObject(anyString(), mapSqlParameterSourceArgumentCaptor.capture(),any(RowMapper.class));
        Assert.assertNotNull(actualReturn);
    }

    @Test
    public void testcreateAccount() {
        AccountDaoImpl accountDao = new AccountDaoImpl();
        ReflectionTestUtils.setField(accountDao, "namedParameterJdbcTemplate", namedParameterJdbcTemplate);
        Account account = new Account();
        when(namedParameterJdbcTemplate.update(anyString(), any(MapSqlParameterSource.class)))
                .thenReturn(1);
        int actualReturn = accountDao.addAccount(account);
        verify(namedParameterJdbcTemplate).update(anyString(), any(MapSqlParameterSource.class));
        Assert.assertEquals(1, actualReturn);
    }

    @Test
    public void testupdateAccount() {
        AccountDaoImpl accountDao = new AccountDaoImpl();
        ReflectionTestUtils.setField(accountDao, "namedParameterJdbcTemplate", namedParameterJdbcTemplate);
        Account account = new Account();
        when(namedParameterJdbcTemplate.update(anyString(), any(MapSqlParameterSource.class)))
                .thenReturn(1);
        int actualReturn = accountDao.editAccount(account);
        verify(namedParameterJdbcTemplate).update(anyString(), any(MapSqlParameterSource.class));
        Assert.assertEquals(1, actualReturn);
    }

    @Test
    public void testdeleteAccount() {
        AccountDaoImpl accountDao = new AccountDaoImpl();
        ReflectionTestUtils.setField(accountDao, "namedParameterJdbcTemplate", namedParameterJdbcTemplate);
        when(namedParameterJdbcTemplate.update(anyString(), any(MapSqlParameterSource.class)))
                .thenReturn(1);
        int actualReturn = accountDao.deleteAccount(1);
        verify(namedParameterJdbcTemplate).update(anyString(), any(MapSqlParameterSource.class));
        Assert.assertEquals(1, actualReturn);
    }
}
