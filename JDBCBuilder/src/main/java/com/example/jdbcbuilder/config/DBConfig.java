package com.example.jdbcbuilder.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

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
        DataSourceBuilder factory = DataSourceBuilder
                .create(getClass().getClassLoader())
                .driverClassName(getDriver())
                .url(getUrl())
                .username(getUsername())
                .password(getPassword());
        return factory.build();
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
