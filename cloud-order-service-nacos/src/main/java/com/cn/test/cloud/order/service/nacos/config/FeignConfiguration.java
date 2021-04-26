package com.cn.test.cloud.order.service.nacos.config;

import com.cn.test.cloud.common.model.Constants;
import feign.RequestInterceptor;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于让Feign透传请求参数中的Header
 *
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
            String version = request.getHeader(Constants.HEADER_VERSION);
            if (StringUtils.isNotEmpty(version)) {
                requestTemplate.header(Constants.HEADER_VERSION, version);
            }
        };
    }
}
