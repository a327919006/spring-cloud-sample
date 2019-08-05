package com.cn.test.cloud.order.service.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Chen Nan
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderDubboServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderDubboServiceApplication.class, args);
    }
}
