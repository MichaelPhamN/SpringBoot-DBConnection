package com.example.connectionpool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootApplication
public class ConnectionPoolApplication {
    @Autowired
    private Connection getConnection;
    @PostConstruct
    void postConstruct(){
        try {
            String sql = "CREATE TABLE IF NOT EXISTS Account (\n" +
                    "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    username VARCHAR(255),\n" +
                    "    password VARCHAR(255)\n" +
                    ")";
            PreparedStatement preparedStatement = getConnection.prepareStatement(sql);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ConnectionPoolApplication.class, args);
    }

}
