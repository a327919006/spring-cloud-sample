package com.cn.test.cloud.common.model.dto;

import com.cn.test.cloud.common.model.Constants;
import lombok.*;

import java.io.Serializable;

/**
 * <p>Title: RspBase</p>
 * <p>Description: Http操作结果对象</p>
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RspBase<T> implements Serializable {
    private int code = Constants.CODE_SUCCESS;
    private String msg;
    private T data;

    private RspBase(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private RspBase(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> RspBase<T> data(T data) {
        return data(Constants.CODE_SUCCESS, Constants.MSG_CMS_SUCCESS, data);
    }

    public static <T> RspBase<T> data(int code, T data) {
        return data(code, Constants.MSG_CMS_SUCCESS, data);
    }

    public static <T> RspBase<T> data(int code, String msg, T data) {
        return new RspBase<>(code, msg, data);
    }

    public static <T> RspBase<T> fail(String msg) {
        return fail(Constants.CODE_FAILURE, msg);
    }

    public static <T> RspBase<T> fail(int code, String msg) {
        return new RspBase<>(code, msg);
    }
}
