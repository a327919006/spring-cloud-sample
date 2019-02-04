package com.cn.test.cloud.user.service.zk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Spring Boot应用类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserZkServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserZkServiceApplication.class, args);
    }
}
