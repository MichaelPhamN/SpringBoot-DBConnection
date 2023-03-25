package com.example.inmemorydbh2.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:application.properties")
public class DBDefaultConfig {
    @Value("${spring.jdbc.driver}")
    private String driver;

    @Value("${spring.jdbc.url}")
    private String url;

    @Value("${spring.jdbc.username}")
    private String username;

    @Value("${spring.jdbc.password}")
    private String password;

    @Value("${spring.jdbc.minIdle}")
    private Integer minIdle;

    @Value("${spring.jdbc.maxIdle}")
    private Integer maxIdle;

    @Value("${spring.jdbc.maxOpenPreparedStatements}")
    private Integer maxOpenPreparedStatements;

    @Bean
    public Connection getConnection() throws SQLException {
        HikariConfig config  = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        config.setMinimumIdle(minIdle);
        config.setMaximumPoolSize(maxOpenPreparedStatements);
                config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config).getConnection();
    }
}
