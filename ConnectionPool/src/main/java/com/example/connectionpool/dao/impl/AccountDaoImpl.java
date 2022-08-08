package com.example.connectionpool.dao.impl;

import com.example.connectionpool.config.DBConfig;
import com.example.connectionpool.config.DBDefaultConfig;
import com.example.connectionpool.dao.AccountDao;
import com.example.connectionpool.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao {
    //Field Injection
    @Autowired
    private DBConfig conn;

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
    public int addAccount(Account account) {
        int executedRow = 0;
        try {
            String sql = "INSERT INTO ACCOUNT(email, password) VALUES (?,?)";
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getPassword());
            executedRow = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return executedRow;
    }

    @Override
    public int editAccount(Account account) {
        int executedRow = 0;
        try {
            String sql = "UPDATE ACCOUNT as acc SET acc.email = ?, acc.password = ? WHERE acc.id = ?";
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setInt(3, account.getId());
            executedRow = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return executedRow;
    }

    @Override
    public Account findAccountById(Integer id) {
        Account account = null;
        try {
            String sql = "SELECT acc.id, acc.email, acc.password FROM ACCOUNT as acc WHERE acc.id = ?";
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                account = new Account();
                account.setId(rs.getInt("id"));
                account.setEmail(rs.getString("email"));
                account.setPassword(rs.getString("password"));
            }
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
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setEmail(rs.getString("email"));
                account.setPassword(rs.getString("password"));
                accounts.add(account);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public int deleteAccount(Integer id) {
        int executedRow = 0;
        try {
            String sql = "DELETE FROM ACCOUNT as acc WHERE acc.id = ?";
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            executedRow = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return executedRow;
    }
}
