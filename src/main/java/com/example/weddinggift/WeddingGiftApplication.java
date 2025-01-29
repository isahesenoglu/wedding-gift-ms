package com.example.weddinggift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeddingGiftApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeddingGiftApplication.class, args);
    }

}
