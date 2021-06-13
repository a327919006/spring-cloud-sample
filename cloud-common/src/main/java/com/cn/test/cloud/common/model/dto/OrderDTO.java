package com.cn.test.cloud.common.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Chen Nan
 */
@Data
public class OrderDTO implements Serializable {
    private Long userId;
    private Long productId;
}
