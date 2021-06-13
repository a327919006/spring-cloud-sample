package com.cn.test.cloud.common.service;

import com.cn.test.cloud.common.model.dto.OrderDTO;
import com.cn.test.cloud.common.model.dto.RspBase;
import com.cn.test.cloud.common.model.po.Order;

/**
 * @author Chen Nan
 */
public interface OrderService extends BaseService<Order, String> {
    RspBase<String> createOrder(OrderDTO param);
}
