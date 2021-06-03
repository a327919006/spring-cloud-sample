package com.cn.test.cloud.order.service.nacos.aop;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cn.test.cloud.common.model.Constants;
import com.cn.test.cloud.common.model.dto.RspBase;

/**
 * 自定义接口被限流时的处理方法
 * 可定义多个方法，在SentinelResource注解中指定调用的方法
 *
 * @author Chen Nan
 */
public class SentinelBlockHandler {

    /**
     * 方法必须是static
     * 请求、响应需与原方法保持一致，并在最后增加一个参数BlockException
     */
    public static RspBase rateLimit(String id, String myHeader, BlockException exception) {
        return RspBase.fail(Constants.CODE_FAILURE, "接口繁忙，请稍后重试");
    }

    public static RspBase rateLimit(BlockException exception) {
        return RspBase.fail(Constants.CODE_FAILURE, "接口繁忙，请稍后重试2");
    }
}
