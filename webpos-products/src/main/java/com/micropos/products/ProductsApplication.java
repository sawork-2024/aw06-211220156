package com.micropos.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableJpaRepositories(basePackages = "com.micropos.products.jpa")
//@EntityScan(basePackages = "com.micropos.products.model.Product")
public class ProductsApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "products");
        SpringApplication.run(ProductsApplication.class, args);
    }
}
