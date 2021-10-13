package com.alexbig.reactiveprogramming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
public class ReactiveProgrammingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveProgrammingApplication.class, args);
    }

}
