package com.cn.test.cloud.user.service.controller;

import cn.hutool.core.util.IdUtil;
import com.cn.test.cloud.common.model.dto.ByteArrayDto;
import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.common.model.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

    @GetMapping("")
    public RspBase<List<User>> findList(@ModelAttribute User user) {
        log.info("【用户】开始获取列表" + user);
        List<User> list = new ArrayList<>();
        list.add(user);
        log.info("【用户】获取列表成功");
        return RspBase.data(list);
    }

    @PostMapping("")
    public RspBase<User> add(@RequestBody User user) {
        log.info("【用户】开始添加" + user);
        user.setId(IdUtil.simpleUUID());
        log.info("【用户】添加成功" + user);
        return RspBase.data(user);
    }

    @GetMapping("/byteData")
    public RspBase<ByteArrayDto> getByteData(@RequestParam("data") String data) {
        log.info("【用户】开始获取数据,data={}", data);
        // 如果直接将byte数组返回，在json处理是会乱码
        ByteArrayDto result = new ByteArrayDto(data.getBytes(StandardCharsets.UTF_8));
        log.info("【用户】获取数据成功");
        return RspBase.data(result);
    }

    @GetMapping("/stream")
    public void getStreamData(@RequestParam("data") String data, HttpServletResponse response) throws IOException {
        log.info("【用户】开始获取数据" + data);
        log.info("【用户】获取数据成功");
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }
}
