package com.example.jdbc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

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

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Bean
    public DataSource jdbcDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(getDriver());
        driverManagerDataSource.setUrl(getUrl());
        driverManagerDataSource.setUsername(getUsername());
        driverManagerDataSource.setPassword(getPassword());
        return driverManagerDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(jdbcDataSource());
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(jdbcDataSource());
    }
}
