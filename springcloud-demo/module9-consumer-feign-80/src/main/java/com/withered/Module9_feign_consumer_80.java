package com.withered;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.withered"})
@ComponentScan("com.withered")
public class Module9_feign_consumer_80 {
    public static void main(String[] args) {
        SpringApplication.run(Module9_feign_consumer_80.class, args);
    }
}
