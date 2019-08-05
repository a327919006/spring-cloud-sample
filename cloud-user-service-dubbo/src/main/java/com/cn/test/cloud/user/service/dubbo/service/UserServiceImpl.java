package com.cn.test.cloud.user.service.dubbo.service;

import cn.hutool.core.util.IdUtil;
import com.cn.test.cloud.common.model.po.User;
import com.cn.test.cloud.common.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author Chen Nan
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public User insert(User user) {
        log.info("【用户】添加开始:{}", user);
        user.setId(IdUtil.simpleUUID());
        log.info("【用户】添加成功:{}", user);
        return user;
    }
}
