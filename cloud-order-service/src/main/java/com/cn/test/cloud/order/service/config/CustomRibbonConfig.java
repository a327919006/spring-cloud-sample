package com.cn.test.cloud.order.service.config;

import com.cn.test.cloud.common.anotation.ExtExcludeComponentScan;
//import com.netflix.loadbalancer.IRule;
//import com.netflix.loadbalancer.RandomRule;
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
public class CustomRibbonConfig {

//    @Bean
//    public IRule ribbonRule() {
//        log.info("【RibbonClient】使用随机Rule");
//        return new RandomRule();
//    }
}
