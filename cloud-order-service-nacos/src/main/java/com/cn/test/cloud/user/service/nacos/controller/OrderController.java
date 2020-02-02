package com.cn.test.cloud.user.service.nacos.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cn.test.cloud.common.model.Constants;
import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.user.service.nacos.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/1/12.
 */
@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private UserService userService;

    @Value("${user.age}")
    private String age;

    @Autowired
    private ConfigurableApplicationContext config;

    @GetMapping("/{id}")
    @SentinelResource(value = "getOrder", defaultFallback = "defaultFail")
    public RspBase get(@PathVariable String id, @RequestHeader(required = false) String myHeader) {
        log.info("【订单】开始获取,id={},myHeader={}", id, myHeader);
        int i = Integer.parseInt(id);
        ServiceInstance serviceInstance = loadBalancerClient.choose("cloud-user-service-nacos");
        String url = String.format("http://%s:%s/user/%s", serviceInstance.getHost(), serviceInstance.getPort(), id);
        RspBase result = restTemplate.getForObject(url, RspBase.class);
        log.info("【订单】获取成功");
        return result;
    }

    @GetMapping("/feign/{id}")
    public RspBase feignGet(@PathVariable String id) {
        log.info("【订单】开始获取");
        RspBase rspBase = userService.get(id);
        log.info("【订单】获取成功");
        return rspBase;
    }

    @GetMapping("/config/{key}")
    public String getConfig(@PathVariable String key) {
        log.info("【配置】开始获取,key={}", key);
        String name = config.getEnvironment().getProperty(key);
        log.info("【配置】获取成功");
        return name;
    }

    @GetMapping("/config/age")
    public String getAge() {
        log.info("【配置】开始获取age");
        log.info("【配置】获取成功age");
        return age;
    }

    public RspBase defaultFail() {
        log.info("【订单】失败");
        return new RspBase(Constants.CODE_FAILURE, "失败");
    }
}
