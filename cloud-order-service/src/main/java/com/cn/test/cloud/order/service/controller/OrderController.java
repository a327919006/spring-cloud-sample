package com.cn.test.cloud.order.service.controller;

import com.cn.test.cloud.common.model.Constants;
import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.common.model.po.User;
import com.cn.test.cloud.order.service.service.CustomService;
import com.cn.test.cloud.order.service.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
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

    @Value("${profile}")
    private String profile;

    @Autowired
    private RestTemplate restTemplate;

    // 用于测试自定义Ribbon自定义负载均衡策略
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomService customService;

    // 用于测试Feign自定义配置
//    @Autowired
//    private UserFeignService userFeignService;

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "fallback")
    public Object get(@PathVariable String id) {
        log.info("【订单】开始获取");
        // cloud-user-service是user服务的虚拟IP，也是定义服务时的服务ID
        ResponseEntity<RspBase> result = restTemplate.getForEntity("http://cloud-user-service/user/" + id, RspBase.class);
        log.info("【订单】获取成功");
        return result;
    }

    public Object fallback(String id) {
        return new RspBase(Constants.CODE_FAILURE, "服务降级" + id);
    }

    // 用于测试自定义Ribbon自定义负载均衡策略
    @GetMapping("/test")
    public Object test() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("cloud-user-service");
        return new RspBase().data(serviceInstance.getPort());
    }

    @GetMapping("/findById/{id}")
    public Object findById(@PathVariable String id) {
        log.info("【订单】Feign开始获取");
        RspBase result = userService.get(id);
        log.info("【订单】Feign获取成功");
        return result;
    }

    @GetMapping("/findList")
    public Object findById(@ModelAttribute User user) {
        log.info("【订单】Feign开始获取列表" + user);
        RspBase result = userService.findList(user.getName(), user.getAge());
        log.info("【订单】Feign获取列表成功");
        return result;
    }

    @GetMapping("/add")
    public Object add(@ModelAttribute User user) {
        log.info("【订单】Feign开始添加" + user);
        RspBase result = userService.add(user);
        log.info("【订单】Feign添加成功");
        return result;
    }

    @GetMapping("/custom/{id}")
    public Object customFindById(@PathVariable String id) {
        log.info("【订单】Custom开始获取");
        RspBase result = customService.get(id);
        log.info("【订单】Custom获取成功");
        return result;
    }

    // 用于测试Feign自定义配置
//    @GetMapping("/feign/findById/{id}")
//    public Object findByIdFeign(@PathVariable String id) {
//        log.info("【订单】Feign开始获取");
//        RspBase result = userFeignService.get(id);
//        log.info("【订单】Feign获取成功");
//        return result;
//    }

    // 获取配置中心配置
    @GetMapping("/getConfig")
    public Object getConfig() {
        return new RspBase().data(profile);
    }
}
