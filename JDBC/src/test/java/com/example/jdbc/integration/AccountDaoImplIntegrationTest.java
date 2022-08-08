package com.example.jdbc.integration;

import com.example.jdbc.dao.impl.AccountDaoImpl;
import com.example.jdbc.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@JdbcTest

public class AccountDaoImplIntegrationTest {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    @Sql(scripts = {"/scripts/schema.sql", "/scripts/insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"/scripts/cleanup.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void whenInjectInMemoryDataSource_thenReturnCorrectEmployeeCount() {
        AccountDaoImpl accountDao = new AccountDaoImpl();
        accountDao.setDBConnection(namedParameterJdbcTemplate);

        List<Account> accounts = accountDao.findAccounts();
    }
}
