package com.cn.test.cloud.order.service.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Spring Boot应用类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderNacosServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderNacosServiceApplication.class, args);
    }
}
