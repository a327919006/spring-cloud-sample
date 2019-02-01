package com.cn.test.cloud.order.service.service;

import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.common.model.po.User;
import com.cn.test.cloud.order.service.config.CustomFeignConfig;
import feign.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * <p>Title:自定义Feign配置</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/1/13.
 */
//@FeignClient(value = "cloud-user-service", configuration = CustomFeignConfig.class)
public interface UserFeignService {

    @RequestLine("GET /user/{id}")
    RspBase get(@Param("id") String id);
}
