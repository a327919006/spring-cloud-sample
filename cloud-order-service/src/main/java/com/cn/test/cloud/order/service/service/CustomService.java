package com.cn.test.cloud.order.service.service;

import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.common.model.po.User;
import com.cn.test.cloud.order.service.service.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * <p>Description:
 * 1、不走eureka，写死路径
 * 2、不用hystrix，超时抛异常
 * </p>
 *
 * @author Chen Nan
 * @date 2019/1/13.
 */
@FeignClient(value = "custom", url = "http://localhost:10092")
public interface CustomService {
    @GetMapping("/user/{id}")
    RspBase<User> get(@PathVariable("id") String id);
}
