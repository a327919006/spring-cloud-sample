package com.cn.test.cloud.user.service.nacos.aop;

import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 当调用方使用feign并且配置了fallback降级时，即使下游异常，也会进降级方法，导致事务不会回滚
 * 此处通过AOP的方式，当下游异常时，由下游触发回滚
 *
 * @author Chen Nan
 */
@Slf4j
@Aspect
@Component
public class SeataExceptionAspect {

    @AfterThrowing(throwing = "e", pointcut = "execution(* com.cn.test.cloud.user.service.nacos.service.impl.*.*(..))")
    public void doRecoveryActions(Throwable e) throws TransactionException {
        if (!StringUtils.isBlank(RootContext.getXID())) {
            log.info("发生异常，事务回滚");
            GlobalTransactionContext.reload(RootContext.getXID()).rollback();
        }
    }
}
