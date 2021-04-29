package com.cn.test.cloud.order.service.nacos.aop;

import brave.Tracer;
import com.alibaba.fastjson.JSONObject;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * 自定义Prometheus埋点
 * 当业务发生异常时，上报业务数据：接口地址、链路ID等等（如果需要可以增加请求参数、时间等）
 *
 * @author Chen Nan
 */
@Slf4j
@Aspect
@Component
public class PrometheusAspect {

    @Resource
    private Tracer tracer;

    private Counter counter;

    public PrometheusAspect(CollectorRegistry collectorRegistry) {
        counter = Counter.build("sample_error_request", "Request error")
                .labelNames("requestURI", "traceId", "error", "time", "param")
                .register(collectorRegistry);
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.Mapping)")
    public void prometheus() {
    }

    @AfterThrowing(pointcut = "prometheus()", throwing = "e")
    public void doAfterThrowing(Throwable e) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();
        String param = "";
        if (parameterMap != null) {
            param = JSONObject.toJSONString(parameterMap);
        }

        String requestURI = request.getRequestURI();
        // 两种方式获取TraceId，结果一样
//        String traceId = MDC.get("traceId");
        String traceId = String.valueOf(tracer.currentSpan().context().traceIdString());
        String error = e.getMessage();
        String time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        counter.labels(requestURI, traceId, error, time, param).inc();
    }

//    @AfterReturning(pointcut = "prometheus()", returning = "ret")
//    public void doAfterThrowing(Object ret) {
//        log.info("ret={}", ret);
//        if (ret instanceof RspBase) {
//            RspBase rsp = (RspBase) ret;
//            if (0 != rsp.getCode()) {
//                RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//                if (requestAttributes == null) {
//                    return;
//                }
//                HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//
//                String requestURI = request.getRequestURI();
//                String traceId = MDC.get("traceId");
//                log.info("requestURI={}", requestURI);
//                log.info("traceId={}", traceId);
//                counter.labels(requestURI, traceId, rsp.getMsg()).inc();
//            }
//        }
//    }
}
