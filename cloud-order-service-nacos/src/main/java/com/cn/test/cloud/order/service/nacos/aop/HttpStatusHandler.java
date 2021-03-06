package com.cn.test.cloud.order.service.nacos.aop;

import com.cn.test.cloud.common.model.dto.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Http返回状态处理
 *
 * @author Chen Nan
 */
public class HttpStatusHandler {

    /**
     * 返回错误信息
     */
    public ResponseEntity error(String msg) {
        return error(HttpStatus.BAD_REQUEST, msg);
    }

    /**
     * 返回指定状态错误信息
     */
    public ResponseEntity error(HttpStatus status, String msg) {
        return error(status, null, msg);
    }

    /**
     * 返回指定状态、指定code、错误信息
     */
    public ResponseEntity error(HttpStatus status, Integer code, String msg) {
        return new ResponseEntity<>(new Error(code, msg), status);
    }
}
