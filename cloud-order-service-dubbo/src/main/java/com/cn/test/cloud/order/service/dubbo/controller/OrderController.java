package com.cn.test.cloud.order.service.dubbo.controller;

import com.cn.test.cloud.common.model.po.User;
import com.cn.test.cloud.common.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chen Nan
 */
@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {

    @DubboReference
    private UserService userService;

    @GetMapping("/{name}")
    public User test(@PathVariable String name) {
        log.info("开始测试");
        User user = new User();
        user.setName(name);
        user.setAge(RandomUtils.nextInt(10, 30));

        int result = userService.insert(user);
        log.info("测试成功,result={}", result);
        return user;
    }
}
