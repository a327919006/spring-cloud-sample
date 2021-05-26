package com.cn.test.cloud.gateway.test;

import java.time.ZonedDateTime;

/**
 * 用于配置predicates， After、Before、Between
 * @author Chen Nan
 */
public class ZoneTimeTest {

    public static void main(String[] args) {
        ZonedDateTime time = ZonedDateTime.now();
        // 2021-05-26T10:23:13.880+08:00[Asia/Shanghai]
        System.out.println(time);
    }
}
