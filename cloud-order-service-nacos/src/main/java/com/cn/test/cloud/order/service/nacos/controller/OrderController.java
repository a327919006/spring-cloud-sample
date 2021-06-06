package com.cn.test.cloud.order.service.nacos.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.common.model.po.User;
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
@Slf4j
@RefreshScope
@RestController
@RequestMapping("order")
//@SentinelResource(defaultFallback = "defaultFallback")
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

    /**
     * blockHandlerClass：限流处理类
     * blockHandler：指定被限流时的处理方法，默认限流响应信息：Blocked by Sentinel (flow limiting)
     * fallback：指定发生异常时的处理方法
     */
    @GetMapping("/{id}")
//    @SentinelResource(value = "getOrder", fallback = "fallback")
//    @SentinelResource(value = "getOrder", blockHandler = "defaultBlock")
    @SentinelResource(value = "getOrder", blockHandlerClass = SentinelBlockHandler.class, blockHandler = "rateLimit")
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
    public RspBase<User> feignGet(@PathVariable String id) {
        log.info("【订单】开始获取");
        RspBase<User> rspBase = userService.get(id);
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
    public RspBase<String> getAge() {
        log.info("【配置】开始获取age");
        log.info("【配置】获取成功age");
        return RspBase.data(age);
    }

    @GetMapping("/test/error")
    public RspBase<String> testError() {
        log.info("【测试】发生业务异常，上报prometheus");
        int i = 1 / 0;
        return RspBase.data(age);
    }

    @GetMapping("/test/timeout")
    public RspBase<String> testTimeout(@RequestParam(required = false, defaultValue = "1000") int timeout) {
        log.info("【测试】发生业务超时");
        ThreadUtil.sleep(timeout);
        return RspBase.data(age);
    }

    /**
     * 默认异常处理方法
     * 请求参数需为空，响应参数与原方法一致
     * 允许在最后增加一个参数Throwable，用来获取异常信息
     */
    public RspBase defaultFallback(Throwable e) {
        return RspBase.fail("失败默认fallback");
    }

    /**
     * 方法发生异常时的处理方法
     * 请求、响应需与原方法保持一致
     * 允许在最后增加一个参数Throwable，用来获取异常信息
     */
    public RspBase fallback(String id, String myHeader, Throwable e) {
        return RspBase.fail("失败fallback");
    }

    /**
     * 被限流时的处理方法
     * 请求、响应需与原方法保持一致，并在最后增加一个参数BlockException
     */
    public RspBase handleBlock(String id, String myHeader, BlockException exception) {
        return RspBase.fail("自定义BLOCK信息");
    }
}
