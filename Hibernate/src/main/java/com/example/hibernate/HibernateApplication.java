package com.example.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootApplication
public class HibernateApplication {
    @Autowired
    private DataSource dataSource;
    @PostConstruct
    void postConstruct(){
        try {
            String sql = "CREATE TABLE IF NOT EXISTS Account (\n" +
                    "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    username VARCHAR(255),\n" +
                    "    password VARCHAR(255)\n" +
                    ")";

            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sql);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(HibernateApplication.class, args);
    }

}
