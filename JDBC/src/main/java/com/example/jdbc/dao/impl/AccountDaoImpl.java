package com.example.jdbc.dao.impl;

import com.example.jdbc.dao.AccountDao;
import com.example.jdbc.model.Account;
import com.example.jdbc.rowmapper.AccountRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao {
    //Field Injection
//    @Autowired
//    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //Set Injection
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    public void setDBConnection(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    //Constructor Injection
//    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//    @Autowired
//    public AccountDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//    }

    @Override
    public int addAccount(Account account) {
        String sql = "INSERT INTO ACCOUNT(email, password) VALUES (:email, :password);";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("email", account.getEmail());
        parameterSource.addValue("password", account.getPassword());
        return namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    @Override
    public int editAccount(Account account) {
        String sql = "UPDATE ACCOUNT as acc SET acc.email = :acctEmail, acc.password = :acctPassword WHERE acc.id = :acctId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("acctEmail", account.getEmail());
        parameterSource.addValue("acctPassword", account.getPassword());
        parameterSource.addValue("acctId", account.getId());
        return  namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    @Override
    public Account findAccountById(Integer id) {
        String sql = "SELECT acc.id, acc.email, acc.password FROM Account as acc WHERE acc.id = :acctId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("acctId", id);
        return namedParameterJdbcTemplate.queryForObject(sql, parameterSource, new AccountRowMapper());
    }

    @Override
    public List<Account> findAccounts() {
        String sql = "SELECT acc.id, acc.email, acc.password FROM Account as acc";
        return namedParameterJdbcTemplate.query(sql, new AccountRowMapper());
    }

    @Override
    public int deleteAccount(Integer id) {
        String sql = "DELETE FROM ACCOUNT as acc WHERE acc.id = :acctId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("acctId", id);
        return namedParameterJdbcTemplate.update(sql, parameterSource);
    }
}
