package com.cn.test.cloud.user.service.consul.controller;

import cn.hutool.core.util.IdUtil;
import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.common.model.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/1/12.
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/{id}")
    public Object get(@PathVariable("id") String id) {
        log.info(discoveryClient.description());
        log.info("【用户】开始获取" + id);
        User user = new User();
        user.setId(id);
        user.setName("张三");
        user.setAge(11);
        log.info("【用户】获取成功" + user);
        return new RspBase().data(user);
    }

    @GetMapping("")
    public Object findList(@ModelAttribute User user) {
        log.info("【用户】开始获取列表" + user);
        List<User> list = new ArrayList<>();
        list.add(user);
        log.info("【用户】获取列表成功");
        return new RspBase().data(list);
    }

    @PostMapping("")
    public Object add(@RequestBody User user) {
        log.info("【用户】开始添加" + user);
        user.setId(IdUtil.simpleUUID());
        log.info("【用户】添加成功" + user);
        return new RspBase().data(user);
    }
}
