package com.cn.test.cloud.order.service.config;

import com.netflix.discovery.util.StringUtil;
import feign.RequestInterceptor;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于让Feign透传请求参数中的Header
 * @author Chen Nan
 */
@Component
public class FeignConfiguration {
    @Bean
    public RequestInterceptor headerInterceptor() {
        return requestTemplate -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            if (attributes == null) {
                return;
            }
            HttpServletRequest request = attributes.getRequest();
            String token = request.getHeader("cnte-version");
            if (!StringUtils.isEmpty(token)) {
                requestTemplate.header("cnte-version", token);
            }
        };
    }
}
