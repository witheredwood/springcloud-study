package com.withered;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Module7_RibbonConsumer_80 {
    public static void main(String[] args) {
        SpringApplication.run(Module7_RibbonConsumer_80.class, args);
    }
}
