package com.cn.test.cloud.order.service.nacos.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Chen Nan
 */
@Slf4j
@Aspect
@Component
public class PrometheusAspect {

    @Pointcut("execution(public * com.cn.test.cloud.order.service.nacos.controller..*.*(..))")
    public void prometheus() {
    }

    @After("prometheus()")
    public void doAfter(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String requestURI = request.getRequestURI();
        log.info("requestURI={}", requestURI);
        log.info("traceID={}", MDC.get("traceId"));
    }

    @AfterReturning(pointcut = "prometheus()", returning = "rsp")
    public void doAfterThrowing(Object rsp) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String requestURI = request.getRequestURI();
        log.info("requestURI={}", requestURI);
        log.info("traceID={}", MDC.get("traceId"));
    }
}
