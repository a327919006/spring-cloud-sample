package com.cn.test.cloud.rocketmq.consumer;

import com.cn.test.cloud.rocketmq.consumer.config.ConsumerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author Chen Nan
 */
@SpringBootApplication
@EnableBinding({ConsumerConfig.class})
@Slf4j
public class RocketMQConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMQConsumerApplication.class, args);
    }
}
