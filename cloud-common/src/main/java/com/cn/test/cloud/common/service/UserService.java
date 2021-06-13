package com.cn.test.cloud.common.service;

import com.cn.test.cloud.common.model.po.User;

/**
 * @author Chen Nan
 */
public interface UserService extends BaseService<User, String> {
    void decrease(Long userId, Double price);
}
