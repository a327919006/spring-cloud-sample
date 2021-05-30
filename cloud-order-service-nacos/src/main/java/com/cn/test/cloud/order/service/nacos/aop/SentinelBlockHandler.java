package com.cn.test.cloud.order.service.nacos.aop;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cn.test.cloud.common.model.Constants;
import com.cn.test.cloud.common.model.dto.RspBase;

/**
 * 自定义接口被限流时的响应信息
 * 可定义多个方法，在SentinelResource注解中指定调用的方法
 *
 * @author Chen Nan
 */
public class SentinelBlockHandler {

    public static RspBase rateLimit(BlockException exception) {
        return new RspBase(Constants.CODE_FAILURE, "接口繁忙，请稍后重试");
    }

    public static RspBase rateLimit2(BlockException exception) {
        return new RspBase(Constants.CODE_FAILURE, "接口繁忙，请稍后重试2");
    }
}
