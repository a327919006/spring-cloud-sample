package com.cn.test.cloud.user.service.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Chen Nan
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserDubboServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserDubboServiceApplication.class, args);
    }
}
