package com.cn.test.cloud.order.service.service.fallback;

import com.cn.test.cloud.common.model.Constants;
import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.common.model.po.User;
import com.cn.test.cloud.order.service.service.UserService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>Description:
 * 和UserServiceFallback的区别就是能获取到异常的信息
 * 如果信息没用，直接使用UserServiceFallback即可
 * </p>
 *
 * @author Chen Nan
 * @date 2019/1/15.
 */
@Component
@Slf4j
public class UserServiceFallbackFactory implements FallbackFactory<UserService> {

    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
            @Override
            public RspBase get(String id) {
                log.info("获取信息异常：{}", throwable.getMessage());
                return Constants.RSP_FALLBACK;
            }

            @Override
            public RspBase findList(String name, Integer age) {
                return Constants.RSP_FALLBACK;
            }

            @Override
            public RspBase add(User user) {
                return Constants.RSP_FALLBACK;
            }
        };
    }
}
