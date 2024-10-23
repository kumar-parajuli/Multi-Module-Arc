package com.kumar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.kumar.ProductsModule", "com.kumar.CustomerModule", "com.kumar.OrderModule"})
@EnableJpaRepositories(basePackages = {"com.kumar.ProductsModule", "com.kumar.CustomerModule", "com.kumar.OrderModule"})

public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}