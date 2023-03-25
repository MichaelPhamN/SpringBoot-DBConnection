package com.example.traditional.config;

import com.example.traditional.model.Account;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DBPoolConfigTest {

    @Test
    public void whenCalledgetConnection_thenCorrect() throws SQLException {
        DBPoolConfig dbPoolConfig = DBPoolConfig
                .create("jdbc:mysql://localhost:3306/spring_db", "root", "123456");

        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT acc.id, acc.email, acc.password FROM Account as acc";
        Connection conn = dbPoolConfig.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Account account = new Account();
            account.setId(rs.getInt("id"));
            account.setEmail(rs.getString("email"));
            account.setPassword(rs.getString("password"));
            accounts.add(account);
        }
        preparedStatement.close();
        dbPoolConfig.releaseConnection(conn);
        assertTrue(dbPoolConfig.getConnection().isValid(1));
    }
}