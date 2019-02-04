package com.cn.test.cloud.order.service.consul.controller;

import com.cn.test.cloud.common.model.dto.RspBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/1/12.
 */
@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        log.info("【订单】开始获取");
        // cloud-user-service是user服务的虚拟IP，也是定义服务时的服务ID
        ResponseEntity<RspBase> result = restTemplate.getForEntity("http://cloud-user-service-consul/user/" + id, RspBase.class);
        log.info("【订单】获取成功");
        return result;
    }


}
