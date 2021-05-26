package com.cn.test.cloud.stream.controller;

import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.stream.service.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/1/12.
 */
@RestController
@RequestMapping("/message")
@Slf4j
public class MessageController {

    @Autowired
    private IMessageService messageService;

    @GetMapping("/send/{msg}")
    public Object send(@PathVariable("msg") String msg) {
        log.info("【消息】开始发送" + msg);
        messageService.send(msg);
        log.info("【消息】发送成功" + msg);
        return new RspBase().data(msg);
    }
}
