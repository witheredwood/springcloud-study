package com.withered;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class module3_consumer {
    public static void main(String[] args) {
        SpringApplication.run(module3_consumer.class, args);
    }
}
