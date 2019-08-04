package com.cn.test.cloud.rocketmq.producer.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author Chen Nan
 */
public interface ProducerConfig {

    @Output("output1")
    MessageChannel output1();

    @Output("output2")
    MessageChannel output2();
}
