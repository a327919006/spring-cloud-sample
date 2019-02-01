package com.cn.test.cloud.order.service.service.fallback;

import com.cn.test.cloud.common.model.Constants;
import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.common.model.po.User;
import com.cn.test.cloud.order.service.service.UserService;
import org.springframework.stereotype.Component;

/**
 * <p>Description:
 * 服务降级
 * </p>
 *
 * @author Chen Nan
 * @date 2019/1/15.
 */
@Component
public class UserServiceFallback implements UserService {

    @Override
    public RspBase get(String id) {
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
}
