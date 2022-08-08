package com.example.hibernatetransitionalannotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class HibernateTransitionalAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateTransitionalAnnotationApplication.class, args);
    }

}
