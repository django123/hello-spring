package com.django.hellospring;

import com.django.hellospring.entities.Product;
import com.django.hellospring.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository){
        return  args -> {
            productRepository.save(new Product("Tomate",200.00,4));
            productRepository.save(new Product("Ananas",20.00,4));
        };
    }
}
