package com.cn.test.cloud.order.service.controller;

import cn.hutool.core.io.IoUtil;
import com.cn.test.cloud.common.model.Constants;
import com.cn.test.cloud.common.model.dto.ByteArrayDto;
import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.common.model.po.User;
import com.cn.test.cloud.order.service.service.CustomService;
import com.cn.test.cloud.order.service.service.UserService;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
//import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;

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

    //    @Value("${profile}")
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
//    @HystrixCommand(fallbackMethod = "fallback", commandProperties = {
//            @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "3000"),
//            @HystrixProperty(name = HystrixPropertiesManager.METRICS_ROLLING_STATS_TIME_IN_MILLISECONDS, value = "5000"),
//            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "5"),
//            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "5000"),
//            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "50"),
//    })
    public RspBase get(@PathVariable String id) {
        log.info("【订单】开始获取");
        // cloud-user-service是user服务的虚拟IP，也是定义服务时的服务ID
        ResponseEntity<RspBase> result = restTemplate.getForEntity("http://cloud-user-service/user/" + id, RspBase.class);
        log.info("【订单】获取成功");
        return result.getBody();
    }

    public RspBase<String> fallback(String id) {
        return RspBase.fail(Constants.CODE_FAILURE, "服务降级" + id);
    }

    // 用于测试自定义Ribbon自定义负载均衡策略
    @GetMapping("/test")
    public RspBase<Integer> test() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("cloud-user-service");
        return RspBase.data(serviceInstance.getPort());
    }

    @GetMapping("/findById/{id}")
    public RspBase<User> findById(@PathVariable String id) {
        log.info("【订单】Feign开始获取");
        RspBase<User> result = userService.get(id);
        log.info("【订单】Feign获取成功");
        return result;
    }

    @GetMapping("/findList")
    public RspBase<List<User>> findById(@ModelAttribute User user) {
        log.info("【订单】Feign开始获取列表" + user);
        RspBase<List<User>> result = userService.findList(user.getName(), user.getAge());
        log.info("【订单】Feign获取列表成功");
        return result;
    }

    @GetMapping("/add")
    public RspBase<User> add(@ModelAttribute User user) {
        log.info("【订单】Feign开始添加" + user);
        RspBase<User> result = userService.add(user);
        log.info("【订单】Feign添加成功");
        return result;
    }

    @GetMapping("/custom/{id}")
    public RspBase<User> customFindById(@PathVariable String id) {
        log.info("【订单】Custom开始获取");
        RspBase<User> result = customService.get(id);
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
    public RspBase<String> getConfig() {
        return RspBase.data(profile);
    }

    /**
     * 调用feign接口接收byte数组
     */
    @GetMapping("/byteData")
    public RspBase<String> getByteData(String data) {
        log.info("【订单】开始获取Byte{}", data);
        RspBase<ByteArrayDto> result = userService.getByteData(data);
        ByteArrayDto dto = result.getData();
        String str = new String(dto.getData(), StandardCharsets.UTF_8);
        log.info("【订单】获取Byte成功,str={}", str);
        return RspBase.data(str);
    }

    /**
     * 调用feign接口接收字节流数据
     */
    @GetMapping("/stream")
    public RspBase<String> getStreamData(String data) throws IOException {
        log.info("【订单】开始获取stream{}", data);
        Response result = userService.getStreamData(data);
        byte[] bytes = IoUtil.readBytes(result.body().asInputStream());
        log.info("【订单】获取Byte成功");
        return RspBase.data(new String(bytes, StandardCharsets.UTF_8));
    }
}
