package com.example.traditional.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.sql.Connection;
import java.sql.DriverManager;
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

    @Bean
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
