package com.cn.test.cloud.rocketmq.consumer.config;

import com.cn.test.cloud.common.model.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * @author Chen Nan
 */
@Configuration
@Slf4j
public class ConsumerHandler {

    @StreamListener("input1")
    public void receiveInput1(String receiveMsg) {
        log.info("【input1 receive】: " + receiveMsg);
    }

    @StreamListener("input2")
    public void receiveInput2(@Payload User foo) {
        log.info("【input2 receive】: " + foo);
    }
}
