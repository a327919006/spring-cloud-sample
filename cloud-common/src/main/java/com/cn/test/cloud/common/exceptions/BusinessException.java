package com.cn.test.cloud.common.exceptions;

import com.cn.test.cloud.common.model.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 业务异常
 *
 * @author Chen Nan
 * @date 2019/3/2.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BusinessException extends RuntimeException {
    private int code = Constants.CODE_FAILURE;
    private String msg;

    public BusinessException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BusinessException(String msg, Exception e) {
        super(msg, e);
        this.msg = msg;
    }
}
