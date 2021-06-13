package com.cn.test.cloud.user.service.dubbo.service;

import cn.hutool.core.util.IdUtil;
import com.cn.test.cloud.common.model.po.User;
import com.cn.test.cloud.common.service.UserService;
import com.cn.test.cloud.dal.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author Chen Nan
 */
@DubboService
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User, String>
        implements UserService {

    @Override
    public int insert(User user) {
        log.info("【用户】添加开始:{}", user);
        user.setId(IdUtil.simpleUUID());
        log.info("【用户】添加成功:{}", user);
        return 1;
    }

    @Override
    public void decrease(Long userId, Double price) {

    }
}
