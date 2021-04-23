package com.cn.test.cloud.order.service.nacos.config.loadbalancer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Chen Nan
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "gray")
public class GrayProperties {
    private Map<String, Integer> weight;
}
