package com.cn.test.cloud.user.service.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Spring Boot应用类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserConsulServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserConsulServiceApplication.class, args);
    }
}
