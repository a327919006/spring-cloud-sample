package com.cn.test.cloud.order.service.nacos.service.impl;

import com.cn.test.cloud.common.exceptions.BusinessException;
import com.cn.test.cloud.common.model.dto.OrderDTO;
import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.common.model.po.Order;
import com.cn.test.cloud.common.service.OrderService;
import com.cn.test.cloud.dal.mapper.OrderMapper;
import com.cn.test.cloud.user.service.UserClient;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Chen Nan
 */
@Slf4j
@Service
public class OrderServiceImpl extends BaseServiceImpl<OrderMapper, Order, String>
        implements OrderService {
    @Autowired
    private UserClient userClient;

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public RspBase<String> createOrder(OrderDTO param) {
        Order order = new Order();
        order.setId(System.currentTimeMillis());
        order.setUserId(param.getUserId());
        order.setProductId(param.getProductId());
        order.setStatus(0);

        Long productId = param.getProductId();
        double price = 10D;
        if (productId.equals(3L)) {
            price = 100D;
        }
        order.setPrice(price);

        log.info("开始创建订单");
        mapper.insertSelective(order);
        log.info("创建订单成功");

        log.info("开始调用扣款");
        RspBase<String> result = userClient.decrease(param.getUserId(), price);
        log.info("调用扣款成功");

        if (productId.equals(2L)) {
            throw new BusinessException("不合法的产品ID");
        }

        log.info("开始更新订单状态");
        Order temp = new Order();
        temp.setId(order.getId());
        temp.setStatus(1);
        mapper.updateByPrimaryKeySelective(temp);
        log.info("更新订单状态成功");
        return result;
    }
}
