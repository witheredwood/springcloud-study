package com.withered;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 启动类
 */
@SpringBootApplication
@EnableEurekaClient  // 服务启动后自动注册到 Eureka 中
@EnableDiscoveryClient
public class Module8_ProviderEureka_8002 {
    public static void main (String[] args) {
        SpringApplication.run(Module8_ProviderEureka_8002.class, args);
    }
}
