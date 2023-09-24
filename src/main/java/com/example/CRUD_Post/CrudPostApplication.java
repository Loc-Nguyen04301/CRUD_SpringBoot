package com.example.CRUD_Post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication

public class CrudPostApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrudPostApplication.class, args);
    }
}
