package com.example.inmemorydbh2.dao.impl;

import com.example.inmemorydbh2.config.DBDefaultConfig;
import com.example.inmemorydbh2.dao.AccountDao;
import com.example.inmemorydbh2.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao {
    //Field Injection
    @Autowired
    private Connection getConnection;

    //Set Injection
//    private DBConfig conn;
//    @Autowired
//    public void setDBConnection(DBConfig connection) {
//        this.conn = connection;
//    }

    //Constructor Injection
//    private DBConfig conn;
//    @Autowired
//    public AccountDaoImpl(DBConfig connection) {
//        this.conn = connection;
//    }

    @Override
    public int addAccount(Account account) throws SQLException {
        int executedRow = 0;
        try {
            getConnection.setAutoCommit(false);
            String sql = "INSERT INTO ACCOUNT(email, password) VALUES (?,?)";
            PreparedStatement preparedStatement = getConnection.prepareStatement(sql);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getPassword());
            executedRow = preparedStatement.executeUpdate();
            getConnection.commit();
            preparedStatement.close();
        } catch (SQLException e) {
            getConnection.rollback();
            e.printStackTrace();
        }
        return executedRow;
    }

    @Override
    public int editAccount(Account account) throws SQLException {
        int executedRow = 0;
        try {
            getConnection.setAutoCommit(false);
            String sql = "UPDATE ACCOUNT as acc SET acc.email = ?, acc.password = ? WHERE acc.id = ?";
            PreparedStatement preparedStatement = getConnection.prepareStatement(sql);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setInt(3, account.getId());
            executedRow = preparedStatement.executeUpdate();
            getConnection.commit();
            preparedStatement.close();
        } catch (SQLException e) {
            getConnection.rollback();
            e.printStackTrace();
        }
        return executedRow;
    }

    @Override
    public Account findAccountById(Integer id) {
        Account account = null;
        try {
            String sql = "SELECT acc.id, acc.email, acc.password FROM ACCOUNT as acc WHERE acc.id = ?";
            PreparedStatement preparedStatement = getConnection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                account = new Account();
                account.setId(rs.getInt("id"));
                account.setEmail(rs.getString("email"));
                account.setPassword(rs.getString("password"));
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public List<Account> findAccounts() {
        List<Account> accounts = new ArrayList<>();
        try {
            String sql = "SELECT acc.id, acc.email, acc.password FROM Account as acc";
            PreparedStatement preparedStatement = getConnection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setEmail(rs.getString("email"));
                account.setPassword(rs.getString("password"));
                accounts.add(account);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public int deleteAccount(Integer id) throws SQLException {
        int executedRow = 0;
        try {
            getConnection.setAutoCommit(false);
            String sql = "DELETE FROM ACCOUNT as acc WHERE acc.id = ?";
            PreparedStatement preparedStatement = getConnection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            executedRow = preparedStatement.executeUpdate();
            getConnection.commit();
            preparedStatement.close();
        } catch (SQLException e) {
            getConnection.rollback();
            e.printStackTrace();
        }
        return executedRow;
    }
}
