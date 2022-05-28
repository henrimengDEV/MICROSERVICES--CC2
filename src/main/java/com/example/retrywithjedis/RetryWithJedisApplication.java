package com.example.retrywithjedis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RetryWithJedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetryWithJedisApplication.class, args);
    }
}

// TODO
// implem le test du retry coté client et server, rajouter quelques use cases en fonction des consignes.
// doc OpenAPI
// Schema des use-cases

