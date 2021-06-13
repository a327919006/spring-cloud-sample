package com.cn.test.cloud.common.model.po;

import cn.hutool.json.JSONUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/1/12.
 */
@Getter
@Setter
@Table(name = "t_user")
public class User implements Serializable {
    @Id
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;
    @Column(name = "account")
    private Double account;

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
