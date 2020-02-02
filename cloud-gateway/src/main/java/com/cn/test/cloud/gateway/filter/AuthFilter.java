package com.cn.test.cloud.gateway.filter;

import cn.hutool.json.JSONUtil;
import com.cn.test.cloud.common.model.dto.RspBase;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Chen Nan
 */
@Component
@Order(0)
public class AuthFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String token = request.getHeaders().getFirst("token");
        if (validToken(token)) {
            return chain.filter(exchange);
        }

        ServerHttpResponse response = exchange.getResponse();

        RspBase rspBase = new RspBase();
        rspBase.setMsg("请先登录");

        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONUtil.toJsonStr(rspBase).getBytes());
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return response.writeWith(Mono.just(dataBuffer));
    }

    /**
     * 校验token是否有效
     *
     * @param accessToken accessToken
     * @return true token有效  false token无效
     */
    private boolean validToken(String accessToken) {
        if (StringUtils.isBlank(accessToken)) {
            return false;
        }

        // 根据业务规则校验token是否有效
        return accessToken.contains("1");
    }
}
