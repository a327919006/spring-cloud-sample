package com.cn.test.cloud.order.service.nacos.service.fallback;

import com.cn.test.cloud.common.model.Constants;
import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.common.model.po.User;
import com.cn.test.cloud.order.service.nacos.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @author Chen Nan
 */
@Component
public class UserServiceFallback implements UserService {
    @Override
    public RspBase<User> get(String id) {
        return RspBase.fail(Constants.MSG_FALLBACK);
    }
}
