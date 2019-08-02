package com.cn.test.cloud.user.service.nacos.service;

import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.user.service.nacos.service.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Chen Nan
 */
@FeignClient(value = "cloud-user-service-nacos", fallback = UserServiceFallback.class)
public interface UserService {

    @GetMapping("/user/{id}")
    RspBase get(@PathVariable("id") String id);
}
