package com.cn.test.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Spring Boot应用类
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaHaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaHaApplication.class, args);
    }
}
