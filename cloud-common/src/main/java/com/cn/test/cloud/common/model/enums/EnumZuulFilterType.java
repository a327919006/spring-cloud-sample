package com.cn.test.cloud.common.model.enums;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/1/20.
 */
public enum EnumZuulFilterType {
    PRE("pre"), // 前置过滤器，用于身份验证、调用记录等
    ROUTING("routing"), // 路由处理过滤器，可用于构建发送给微服务的请求
    POST("post"), // 后置过滤器，用于为响应添加头部信息、收集统计信息和指标、处理响应结果等
    ERROR("error"); // 异常过滤器，

    private String value;

    EnumZuulFilterType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
