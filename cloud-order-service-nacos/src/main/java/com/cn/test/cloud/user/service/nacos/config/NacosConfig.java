package com.cn.test.cloud.user.service.nacos.config;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

/**
 * @author Chen Nan
 */
@Configuration
public class NacosConfig {

    /**
     * 使用nacos提供的负载均衡策略，默认为Ribbon的轮询策略
     * 支持在nacos控制台修改实例权重
     */
    @Bean
    @Scope("prototype")
    public IRule nacosRule() {
        return new NacosRule();
    }
}
