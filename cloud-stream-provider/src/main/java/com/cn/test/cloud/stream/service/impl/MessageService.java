package com.cn.test.cloud.stream.service.impl;

import cn.hutool.core.util.IdUtil;
import com.cn.test.cloud.stream.service.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

/**
 * @author Chen Nan
 */
@Slf4j
@EnableBinding(Source.class)
public class MessageService implements IMessageService {

    @Resource
    private MessageChannel output;

    @Override
    public String send(String msg) {
        String id = IdUtil.simpleUUID();
        String payload = msg + ":" + id;
        output.send(MessageBuilder.withPayload(payload).build());
        return payload;
    }
}
