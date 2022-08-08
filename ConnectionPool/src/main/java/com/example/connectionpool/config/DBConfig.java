package com.example.connectionpool.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:application.properties")
public class DBConfig {
    @Value("${database.jdbc.driver}")
    private String driver;

    @Value("${database.jdbc.url}")
    private String url;

    @Value("${database.jdbc.username}")
    private String username;

    @Value("${database.jdbc.password}")
    private String password;

    @Value("${database.jdbc.minIdle}")
    private Integer minIdle;

    @Value("${database.jdbc.maxIdle}")
    private Integer maxIdle;

    @Value("${database.jdbc.maxOpenPreparedStatements}")
    private Integer maxOpenPreparedStatements;

    @Bean
    public Connection getConnection() throws SQLException {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setMinIdle(minIdle);
        ds.setMaxIdle(maxIdle);
        ds.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
        return ds.getConnection();
    }
}
