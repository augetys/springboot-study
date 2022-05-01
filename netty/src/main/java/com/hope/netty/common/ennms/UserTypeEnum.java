package com.hope.netty.common.ennms;

/**
 * Created by lijin on  2022/4/30
 */
public enum  UserTypeEnum {
    USER(0,"用户"),

    CALL(1,"客服"),


            ;

    Integer code;

    String desc;

    UserTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
