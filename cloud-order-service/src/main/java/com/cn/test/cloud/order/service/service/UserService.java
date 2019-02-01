package com.cn.test.cloud.order.service.service;

import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.common.model.po.User;
import com.cn.test.cloud.order.service.service.fallback.UserServiceFallback;
import com.cn.test.cloud.order.service.service.fallback.UserServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * <p>Description:
 * 1、走eureka，接口地址从eureka上获取
 * 2、用hystrix，超时走服务降级
 * </p>
 *
 * @author Chen Nan
 * @date 2019/1/13.
 */
//@FeignClient(value = "xxx", url = "http://localhost:10092") // 写死url，不走Eureka
//@FeignClient(value = "cloud-user-service", fallbackFactory = UserServiceFallbackFactory.class)
@FeignClient(value = "cloud-user-service", fallback = UserServiceFallback.class)
public interface UserService {
    @GetMapping("/user/{id}")
    RspBase get(@PathVariable("id") String id);

    // 注意，get不能传对象，只能这样传参
    @GetMapping("/user")
    RspBase findList(@RequestParam("name") String name, @RequestParam("age") Integer age);

    @PostMapping("/user")
    RspBase add(@RequestBody User user);
}
