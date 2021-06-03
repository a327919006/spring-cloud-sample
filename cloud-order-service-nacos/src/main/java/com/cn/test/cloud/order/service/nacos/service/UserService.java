package com.cn.test.cloud.order.service.nacos.service;

import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.common.model.po.User;
import com.cn.test.cloud.order.service.nacos.service.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Chen Nan
 */
@FeignClient(value = "cloud-user-service-nacos", fallback = UserServiceFallback.class)
public interface UserService {

    @GetMapping("/user/{id}")
    RspBase<User> get(@PathVariable("id") String id);
}
