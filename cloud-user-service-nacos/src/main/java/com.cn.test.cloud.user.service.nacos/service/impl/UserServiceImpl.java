package com.cn.test.cloud.user.service.nacos.service.impl;

import com.cn.test.cloud.common.exceptions.BusinessException;
import com.cn.test.cloud.common.model.po.User;
import com.cn.test.cloud.common.service.UserService;
import com.cn.test.cloud.dal.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author Chen Nan
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User, String>
        implements UserService {
    @Override
    public void decrease(Long userId, Double price) {
        if (10 < price) {
            throw new BusinessException("账户余额不足");
        }
        mapper.decrease(userId, price);
    }
}
