package com.cn.test.cloud.user.service;

import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.common.model.po.User;
import com.cn.test.cloud.user.service.fallback.UserClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chen Nan
 */
@FeignClient(value = "cloud-user-service-nacos", fallback = UserClientFallback.class)
public interface UserClient {
    String PREFIX = "/user";

    @GetMapping(PREFIX + "/{id}")
    RspBase<User> get(@PathVariable("id") String id);

    @PostMapping(PREFIX + "/get")
    RspBase<User> getByUser(User user);

    @PutMapping(PREFIX + "/{id}/decrease")
    RspBase<String> decrease(@PathVariable("id") Long userId, @RequestParam("price") Double price);
}
