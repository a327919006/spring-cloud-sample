package com.cn.test.cloud.user.service.fallback;

import com.cn.test.cloud.common.model.Constants;
import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.common.model.po.User;
import com.cn.test.cloud.user.service.UserClient;
import org.springframework.stereotype.Component;

/**
 * @author Chen Nan
 */
@Component
public class UserClientFallback implements UserClient {

    @Override
    public RspBase<User> get(String id) {
        return RspBase.fail(Constants.MSG_FALLBACK);
    }

    @Override
    public RspBase<User> getByUser(User user) {
        return RspBase.fail(Constants.MSG_FALLBACK);
    }

    @Override
    public RspBase<String> decrease(Long userId, Double price) {
        return RspBase.fail(Constants.MSG_FALLBACK);
    }
}
