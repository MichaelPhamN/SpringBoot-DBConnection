package com.example.jdbc.rowmapper;

import com.example.jdbc.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account acct = new Account();
        acct.setId(rs.getInt("id"));
        acct.setEmail(rs.getString("email"));
        acct.setPassword(rs.getString("password"));
        return acct;
    }
}
