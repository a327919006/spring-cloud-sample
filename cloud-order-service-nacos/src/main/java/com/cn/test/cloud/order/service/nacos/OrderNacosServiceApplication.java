package com.cn.test.cloud.order.service.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Spring Boot应用类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.cn.test.cloud"})
@ComponentScan(basePackages = {"com.cn.test.cloud"})
@MapperScan({"com.cn.test.cloud.dal.mapper"})
public class OrderNacosServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderNacosServiceApplication.class, args);
    }
}
