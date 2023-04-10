package com.cn.test.cloud.user.service.nacos.controller;

import cn.hutool.core.util.IdUtil;
import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.common.model.po.User;
import com.cn.test.cloud.common.service.UserService;
import com.cn.test.cloud.user.service.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController implements UserClient {

    @Autowired
    private UserService userService;

    @Value("${user.age}")
    private String age;

    @Override
    @GetMapping("/{id}")
    public RspBase<User> get(@PathVariable("id") String id) {
        log.info("【用户】开始获取" + id);
        User user = new User();
        user.setId(id);
        user.setName("张三");
        user.setAge(11);
        log.info("【用户】获取成功" + user);
        return RspBase.data(user);
    }

    @Override
    @PostMapping("/get")
    public RspBase<User> getByUser(User param) {
        log.info("【用户】开始获取" + param.getId());
        User user = new User();
        user.setId(param.getId());
        user.setName("张三");
        user.setAge(11);
        log.info("【用户】获取成功" + user);
        return RspBase.data(user);
    }

    @Override
    @PutMapping("/{id}/decrease")
    public RspBase<String> decrease(@PathVariable("id") Long userId, Double price) {
        log.info("【用户】开始扣款,ID={}, price={}", userId, price);
        userService.decrease(userId, price);
        log.info("【用户】扣款成功");
        return RspBase.success();
    }

    @GetMapping("")
    public Object findList(@ModelAttribute User user) {
        log.info("【用户】开始获取列表" + user);
        List<User> list = new ArrayList<>();
        list.add(user);
        log.info("【用户】获取列表成功");
        return RspBase.data(list);
    }

    @PostMapping("")
    public Object add(@RequestBody User user) {
        log.info("【用户】开始添加" + user);
        user.setId(IdUtil.simpleUUID());
        log.info("【用户】添加成功" + user);
        return RspBase.data(user);
    }

    @GetMapping("/config/age")
    public RspBase<String> getAge() {
        log.info("【配置】开始获取age");
        log.info("【配置】获取成功age");
        return RspBase.data(age);
    }
}
