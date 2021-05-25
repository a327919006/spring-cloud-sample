package com.cn.test.cloud.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 编码方式实现路由转发
 *
 * @author Chen Nan
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/order/**")
                        .filters(f -> {
                                    f.addResponseHeader("myHeader", "test01");
                                    f.addRequestHeader("myHeader", "test02");
                                    return f;
                                }

                        )
                        .uri("http://localhost:10091")
                )
                .build();
    }
}
