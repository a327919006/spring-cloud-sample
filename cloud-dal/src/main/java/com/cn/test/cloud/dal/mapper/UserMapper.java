package com.cn.test.cloud.dal.mapper;

import com.cn.test.cloud.common.model.po.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Chen Nan
 */
public interface UserMapper extends Mapper<User> {
    void decrease(@Param("userId") Long userId, @Param("price") Double price);
}