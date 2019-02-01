package com.cn.test.cloud.order.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Spring Boot应用类
 */
@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
// 自定义Ribbon负载均衡策略
//@RibbonClient(name = "cloud-user-service", configuration = CustomRibbonConfig.class)
// 自定义负载均衡策略时，如果不配置这个，所有服务都会走自定义负载均衡
//@ComponentScan(excludeFilters = @ComponentScan.Filter(value = ExtExcludeComponentScan.class))
@EnableFeignClients
@EnableCircuitBreaker
public class OrderServiceApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
