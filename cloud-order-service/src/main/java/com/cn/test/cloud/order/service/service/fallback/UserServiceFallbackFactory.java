package com.cn.test.cloud.order.service.service.fallback;

import com.cn.test.cloud.common.model.Constants;
import com.cn.test.cloud.common.model.dto.ByteArrayDto;
import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.common.model.po.User;
import com.cn.test.cloud.order.service.service.UserService;
import feign.Response;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>Description:
 * 和UserServiceFallback的区别就是能获取到异常的信息
 * 如果信息没用，直接使用UserServiceFallback即可
 * </p>
 *
 * @author Chen Nan
 * @date 2019/1/15.
 */
@Slf4j
@Component
public class UserServiceFallbackFactory implements FallbackFactory<UserService> {

    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
            @Override
            public RspBase<User> get(String id) {
                log.info("获取信息异常：{}", throwable.getMessage());
                return RspBase.fail(Constants.MSG_FALLBACK);
            }

            @Override
            public RspBase<List<User>> findList(String name, Integer age) {
                return RspBase.fail(Constants.MSG_FALLBACK);
            }

            @Override
            public RspBase<User> add(User user) {
                return RspBase.fail(Constants.MSG_FALLBACK);
            }

            @Override
            public RspBase<ByteArrayDto> getByteData(String data) {
                log.info("获取信息异常：{}", throwable.getMessage());
                return RspBase.fail(Constants.MSG_FALLBACK);
            }

            @Override
            public Response getStreamData(String data) {
                log.error("stream error");
                return null;
            }
        };
    }
}
