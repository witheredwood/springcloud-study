package com.withered.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {
    @Value("${spring.application.name}")
    private String applicationName;  // 应用名

    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaServer;  // eureka 服务端地址

    @Value("${server.port}")
    private String port;  // 端口号

    @GetMapping("/config")
    public String getConfig() {
        return "applicationName: " + applicationName
                + "\neurekaServer: " + eurekaServer
                + "\nport: " + port;
    }
}
