package com.cn.test.cloud.user.service.nacos.controller;

import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.user.service.nacos.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        log.info("【订单】开始获取");
        ServiceInstance serviceInstance = loadBalancerClient.choose("cloud-user-service-nacos");
        String url = String.format("http://%s:%s/user/%s", serviceInstance.getHost(), serviceInstance.getPort(), id);
        RspBase result = restTemplate.getForObject(url, RspBase.class);
        log.info("【订单】获取成功");
        return result;
    }

    @GetMapping("/feign/{id}")
    public Object feignGet(@PathVariable String id) {
        log.info("【订单】开始获取");
        RspBase rspBase = userService.get(id);
        log.info("【订单】获取成功");
        return rspBase;
    }
}
