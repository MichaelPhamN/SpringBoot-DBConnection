package com.example.inmemorydbh2.dao.impl;


import com.example.inmemorydbh2.config.DBDefaultConfig;
import com.example.inmemorydbh2.model.Account;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountDaoImplTest {
    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private AccountDaoImpl accountDao;

    private static final String SELECT_ACCOUNT_BY_ID = "SELECT acc.id, acc.email, acc.password FROM ACCOUNT as acc WHERE acc.id = ?";
    private static final String SELECT_ACCOUNTS_QUERY = "SELECT acc.id, acc.email, acc.password FROM Account as acc";
    private static final String ADD_ACCOUNT_QUERY = "INSERT INTO ACCOUNT(email, password) VALUES (?,?)";
    private static final String UPDATE_ACCOUNT_QUERY = "UPDATE ACCOUNT as acc SET acc.email = ?, acc.password = ? WHERE acc.id = ?";
    private static final String DELETE_ACCOUNT_QUERY = "DELETE FROM ACCOUNT as acc WHERE acc.id = ?";

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
        Assert.assertNotNull(connection);
    }

    @Test
    public void testFindAccountById() throws SQLException {
        when(connection.prepareStatement(SELECT_ACCOUNT_BY_ID)).thenReturn(preparedStatement);
        preparedStatement.setInt(eq(1), anyInt());
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);;
        when(resultSet.getInt("id")).thenReturn(accounts.get(9).getId());
        when(resultSet.getString("email")).thenReturn(accounts.get(9).getEmail());
        when(resultSet.getString("password")).thenReturn(accounts.get(9).getPassword());

        Account acc = accountDao.findAccountById(accounts.get(9).getId());
        assertNotNull(acc);
        assertEquals(accounts.get(9).getId(), acc.getId());
        assertEquals(accounts.get(9).getEmail(), acc.getEmail());
        assertEquals(accounts.get(9).getPassword(), acc.getPassword());
        verify(connection, times(1)).prepareStatement(SELECT_ACCOUNT_BY_ID);
        verify(preparedStatement, times(1)).setInt(eq(1), anyInt());
        verify(resultSet).close();
        verify(preparedStatement).close();
    }

    @Test
    public void testFindAccounts() throws SQLException {
        when(connection.prepareStatement(SELECT_ACCOUNTS_QUERY)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);;
        when(resultSet.getInt("id")).thenReturn(accounts.get(9).getId());
        when(resultSet.getString("email")).thenReturn(accounts.get(9).getEmail());
        when(resultSet.getString("password")).thenReturn(accounts.get(9).getPassword());

        List<Account> accountList = accountDao.findAccounts();
        assertNotNull(accountList);
        assertEquals(1, accountList.size());
        verify(connection, times(1)).prepareStatement(SELECT_ACCOUNTS_QUERY);
        verify(resultSet).close();
        verify(preparedStatement).close();
    }

    @Test
    public void testAddAccount() throws SQLException {
        when(connection.prepareStatement(ADD_ACCOUNT_QUERY)).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        int expectedResponse = 1;
        Assert.assertEquals(expectedResponse, accountDao.addAccount(accounts.get(9)));
        verify(connection, times(1)).setAutoCommit(false);
        verify(connection, times(1)).prepareStatement(ADD_ACCOUNT_QUERY);
        verify(preparedStatement, times(1)).setString(eq(1), anyString());
        verify(preparedStatement, times(1)).setString(eq(2), anyString());
        verify(connection).commit();
        verify(preparedStatement).close();
    }

    @Test
    public void testEditAccount() throws SQLException {
        when(connection.prepareStatement(UPDATE_ACCOUNT_QUERY)).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        int expectedResponse = 1;
        Assert.assertEquals(expectedResponse, accountDao.editAccount(accounts.get(9)));
        verify(connection, times(1)).prepareStatement(UPDATE_ACCOUNT_QUERY);
        verify(preparedStatement, times(1)).setString(eq(1), anyString());
        verify(preparedStatement, times(1)).setString(eq(2), anyString());
        verify(preparedStatement, times(1)).setInt(eq(3), anyInt());
        verify(connection).commit();
        verify(preparedStatement).close();
    }

    @Test
    public void testDeleteAccount() throws SQLException {
        when(connection.prepareStatement(DELETE_ACCOUNT_QUERY)).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        int expectedResponse = 1;
        Assert.assertEquals(expectedResponse, accountDao.deleteAccount(accounts.get(9).getId()));
        verify(connection, times(1)).prepareStatement(DELETE_ACCOUNT_QUERY);
        verify(preparedStatement, times(1)).setInt(eq(1), anyInt());
        verify(connection).commit();
        verify(preparedStatement).close();
    }

}