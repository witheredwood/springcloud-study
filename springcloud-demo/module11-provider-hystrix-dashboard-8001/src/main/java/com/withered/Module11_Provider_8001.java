package com.withered;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * 启动类
 */
@SpringBootApplication
@EnableEurekaClient  // 服务启动后自动注册到 Eureka 中
@EnableDiscoveryClient
public class Module11_Provider_8001 {
    public static void main (String[] args) {
        SpringApplication.run(Module11_Provider_8001.class, args);
    }

    // 增加一个servlet
    @Bean
    public ServletRegistrationBean getServletBean() {
        ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<>(new HystrixMetricsStreamServlet());
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        return registrationBean;
    }
}
