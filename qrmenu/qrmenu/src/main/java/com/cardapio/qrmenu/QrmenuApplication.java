package com.cardapio.qrmenu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cardapio.qrmenu.repository")
public class QrmenuApplication {
    public static void main(String[] args) {
        SpringApplication.run(QrmenuApplication.class, args);
    }
}

