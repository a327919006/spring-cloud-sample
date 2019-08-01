package com.cn.test.cloud.gateway.zuul.filters;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.cn.test.cloud.common.model.Constants;
import com.cn.test.cloud.common.model.dto.RspBase;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/1/20.
 */
@Component
@Slf4j
public class AuthFilter extends ZuulFilter {
    private static final AntPathMatcher MATCHER = new AntPathMatcher();

    /**
     * 拦截器类型
     *
     * @return 拦截器类型
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 此过滤器顺序，越小越靠前
     *
     * @return filter顺序
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 本次请求是否需要拦截处理
     * 也可以改成哪些不需要拦截
     *
     * @return 是否需要拦截
     */
    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String requestURI = request.getRequestURI();
        log.info("【AuthFilter】requestURI = " + requestURI);

        String pattern = "/order/**";
        if (MATCHER.match(pattern, requestURI)) {
            // 只拦截/order请求
            log.info("【AuthFilter】需要拦截");
            return true;
        }
        return false;
    }

    /**
     * 拦截后的操作
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        String accessToken = request.getHeader("accessToken");
        // 对请求头accessToken进行校验，如果无效，直接返回错误信息
        if (invalidToken(accessToken)) {
            context.setSendZuulResponse(false); // 过滤该请求，不对其进行路由
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            context.setResponseBody(JSONUtil.toJsonStr(new RspBase(Constants.CODE_FAILURE, "INVALID TOKEN")));
        }
        return null;
    }

    /**
     * 校验token是否有效
     *
     * @param accessToken accessToken
     * @return true token无效  false token有效
     */
    private boolean invalidToken(String accessToken) {
        if (StrUtil.isBlank(accessToken)) {
            return true;
        }

        // 去redis校验token是否有效
        if (accessToken.contains("1")) {
            return false;
        }
        return true;
    }
}
