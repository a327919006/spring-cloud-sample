package com.cn.test.cloud.order.service.nacos.controller;

import cn.hutool.json.JSONUtil;
import com.cn.test.cloud.common.model.Constants;
import com.cn.test.cloud.common.model.dto.RspBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理接口地址出错，返回json字符串
 *
 * @author Chen Nan
 */
//@RestController
public class NotFoundController implements ErrorController {

    /**
     * 默认错误
     */
    private static final String path_default = "/error";

    public String getErrorPath() {
        return path_default;
    }

    /**
     * JSON格式错误信息
     *
     * @return
     */
    @RequestMapping(value = path_default)
    public ResponseEntity error() {
        Map<String, Object> rsp = new HashMap<>();
        rsp.put("code", "404");
        rsp.put("msg", "Not Found");

        return new ResponseEntity<>(JSONUtil.toJsonStr(rsp), HttpStatus.OK);
    }
}
