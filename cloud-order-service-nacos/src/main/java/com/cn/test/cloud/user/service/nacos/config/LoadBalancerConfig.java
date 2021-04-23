package com.cn.test.cloud.order.service.nacos.config;

import com.cn.test.cloud.order.service.nacos.config.loadbalancer.GrayLoadBalancerRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chen Nan
 */
@Configuration
public class LoadBalancerConfig {

    @Bean
    public IRule myRule() {
        return new GrayLoadBalancerRule();
    }
}
