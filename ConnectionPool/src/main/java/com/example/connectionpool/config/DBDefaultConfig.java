package com.example.connectionpool.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:application.properties")
public class DBDefaultConfig {
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

//    @Bean
//    public Connection getConnection() throws SQLException {
//        HikariConfig config  = new HikariConfig();
//        config.setJdbcUrl(url);
//        config.setUsername(username);
//        config.setPassword(password);
//        config.addDataSourceProperty("cachePrepStmts", "true");
//        config.addDataSourceProperty("prepStmtCacheSize", "250");
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//        return new HikariDataSource(config).getConnection();
//    }
}
