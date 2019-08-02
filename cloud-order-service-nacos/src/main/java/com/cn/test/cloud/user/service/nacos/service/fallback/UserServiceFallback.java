package com.cn.test.cloud.user.service.nacos.service.fallback;

import com.cn.test.cloud.common.model.Constants;
import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.user.service.nacos.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @author Chen Nan
 */
@Component
public class UserServiceFallback implements UserService {
    @Override
    public RspBase get(String id) {
        return Constants.RSP_FALLBACK;
    }
}
