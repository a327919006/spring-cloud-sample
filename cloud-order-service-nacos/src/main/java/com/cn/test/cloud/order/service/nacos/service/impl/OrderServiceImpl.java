package com.cn.test.cloud.order.service.nacos.service.impl;

import com.cn.test.cloud.common.exceptions.BusinessException;
import com.cn.test.cloud.common.model.dto.OrderDTO;
import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.common.model.po.Order;
import com.cn.test.cloud.common.service.OrderService;
import com.cn.test.cloud.dal.mapper.OrderMapper;
import com.cn.test.cloud.user.service.UserClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Chen Nan
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<OrderMapper, Order, String>
        implements OrderService {
    @Autowired
    private UserClient userClient;

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public RspBase<String> createOrder(OrderDTO param) {
        Order order = new Order();
        order.setUserId(param.getUserId());
        order.setProductId(param.getProductId());
        Double price = 10D;
        order.setPrice(price);

        mapper.insertSelective(order);

        RspBase<String> result = userClient.decrease(param.getUserId(), price);

        if (param.getProductId().equals(2L)) {
            throw new BusinessException("不合法的产品ID");
        }
        return result;
    }
}
