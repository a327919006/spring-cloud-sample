package com.cn.test.cloud.order.service.service.fallback;

import com.cn.test.cloud.common.model.Constants;
import com.cn.test.cloud.common.model.dto.ByteArrayDto;
import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.common.model.po.User;
import com.cn.test.cloud.order.service.service.UserService;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>Description:
 * 服务降级
 * </p>
 *
 * @author Chen Nan
 * @date 2019/1/15.
 */
@Slf4j
@Component
public class UserServiceFallback implements UserService {

    @Override
    public RspBase<User> get(String id) {
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
        return RspBase.fail(Constants.MSG_FALLBACK);
    }

    @Override
    public Response getStreamData(String data) {
        log.error("stream error");
        return null;
    }
}
