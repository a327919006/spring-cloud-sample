package com.cn.test.cloud.rocketmq.producer.controller;

import cn.hutool.core.util.IdUtil;
import com.cn.test.cloud.common.model.po.User;
import com.cn.test.cloud.rocketmq.producer.config.ProducerConfig;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chen Nan
 */
@RestController
@RequestMapping("/producer")
public class ProducerController {

    @Autowired
    private ProducerConfig producerConfig;

    @GetMapping("/output1/{msg}")
    public boolean send1(@PathVariable String msg) {
        Message<String> message = MessageBuilder.withPayload(msg).build();
        return producerConfig.output1().send(message);
    }

    @GetMapping("/output2/{msg}")
    public boolean send2(@PathVariable String msg) {
        User user = new User();
        user.setId(IdUtil.simpleUUID());
        user.setName(msg);
        user.setAge(RandomUtils.nextInt(10,30));

        Message<User> message = MessageBuilder.withPayload(user).build();
        return producerConfig.output2().send(message);
    }
}
