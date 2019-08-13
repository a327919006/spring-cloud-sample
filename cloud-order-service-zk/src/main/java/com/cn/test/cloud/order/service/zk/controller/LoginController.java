package com.cn.test.cloud.order.service.zk.controller;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/1/12.
 */
@RestController
@RequestMapping("login")
@Slf4j
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("code")
    public String code(@RequestParam String code) {
        log.info("授权回调,code={}", code);

        String url = "http://localhost:10094/oauth/token";

        Map<String, Object> param = new HashMap<>();
        param.put("grant_type", "authorization_code");
        param.put("code", code);

        String authorization = "Basic " + Base64Encoder.encode("client:123456");

        HttpResponse execute = HttpRequest.post(url)
                .header("Authorization", authorization)
                .form(param)
                .execute();
        int status = execute.getStatus();
        log.info("status={}", status);
        log.info("body={}", execute.body());
        return execute.body();
    }

}
