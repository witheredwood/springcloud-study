package com.withered.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 注册 Bean 到 Spring 中
 */
@Configuration
public class ConfigBean {
    /**
     * 注册 Bean 的方式一
     *
     * @return RestTemplate对象
     */
    @Bean
    @LoadBalanced  // 配置负载均衡实现 RestTemplate
    public RestTemplate getRestTemplate2(RestTemplateBuilder builder) {
        return builder.build();
    }
}
