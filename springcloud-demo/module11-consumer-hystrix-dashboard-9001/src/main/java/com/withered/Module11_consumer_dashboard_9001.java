package com.withered;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;


@SpringBootApplication
@EnableHystrixDashboard
public class Module11_consumer_dashboard_9001 {
    public static void main(String[] args) {
        SpringApplication.run(Module11_consumer_dashboard_9001.class, args);
    }
}
