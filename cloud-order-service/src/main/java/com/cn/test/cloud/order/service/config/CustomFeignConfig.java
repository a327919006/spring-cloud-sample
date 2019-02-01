package com.cn.test.cloud.order.service.config;

import com.cn.test.cloud.common.anotation.ExtExcludeComponentScan;
import feign.Contract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/1/12.
 */
//@Configuration
@ExtExcludeComponentScan
@Slf4j
public class CustomFeignConfig {

    @Bean
    public Contract feignContract() {
        log.info("【Feign】使用默认Contract");
        return new feign.Contract.Default();
    }
}
