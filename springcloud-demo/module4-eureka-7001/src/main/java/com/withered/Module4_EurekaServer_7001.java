package com.withered;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Module4_EurekaServer_7001 {
    public static void main(String[] args) {
        SpringApplication.run(Module4_EurekaServer_7001.class, args);
    }
}
