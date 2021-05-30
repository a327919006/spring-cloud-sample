package com.cn.test.cloud.order.service.nacos.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cn.test.cloud.common.model.Constants;
import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.order.service.nacos.aop.SentinelBlockHandler;
import com.cn.test.cloud.order.service.nacos.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope
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
//    @SentinelResource(value = "getOrder", fallback = "fallback", blockHandler = "defaultBlock")
    @SentinelResource(value = "getOrder", defaultFallback = "defaultFail", blockHandler = "defaultBlock")
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
//    @SentinelResource(value = "getConfig", blockHandler = "defaultBlock")
    @SentinelResource(value = "getConfig", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "rateLimit")
    public RspBase getAge() {
        log.info("【配置】开始获取age");
        log.info("【配置】获取成功age");
        return new RspBase().data(age);
    }

    @GetMapping("/test/error")
    public String testError() {
        log.info("【测试】发生业务异常，上报prometheus");
        int i = 1 / 0;
        return age;
    }

    /**
     * 公用的fallback方法
     *
     * @return
     */
    public RspBase defaultFail() {
        log.info("【订单】失败");
        return new RspBase(Constants.CODE_FAILURE, "失败");
    }

    /**
     * 指定方法的fallback
     */
    public RspBase fallback(String id, String myHeader) {
        log.info("【订单】失败fallback");
        return new RspBase(Constants.CODE_FAILURE, "失败fallback");
    }

    /**
     * 指定方法的限流异常处理方法
     */
    public String defaultBlock(BlockException exception) {
        return "自定义BLOCK信息";
    }
}
