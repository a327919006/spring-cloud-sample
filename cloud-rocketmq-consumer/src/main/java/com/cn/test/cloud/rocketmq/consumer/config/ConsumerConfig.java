package com.cn.test.cloud.rocketmq.consumer.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author Chen Nan
 */
public interface ConsumerConfig {
    @Input("input1")
    SubscribableChannel input1();

    @Input("input2")
    SubscribableChannel input2();
}
