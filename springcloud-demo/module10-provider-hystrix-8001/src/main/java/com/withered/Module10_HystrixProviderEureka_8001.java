package com.withered;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 启动类
 */
@SpringBootApplication
@EnableEurekaClient  // 服务启动后自动注册到 Eureka 中
@EnableDiscoveryClient
@EnableCircuitBreaker()  // 开启服务熔断
public class Module10_HystrixProviderEureka_8001 {
    public static void main (String[] args) {
        SpringApplication.run(Module10_HystrixProviderEureka_8001.class, args);
    }
}
