package com.cn.test.cloud.rocketmq.producer;

import com.cn.test.cloud.rocketmq.producer.config.ProducerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author Chen Nan
 */
@SpringBootApplication
@EnableBinding({ProducerConfig.class})
public class RocketMQProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMQProducerApplication.class, args);
    }
}
