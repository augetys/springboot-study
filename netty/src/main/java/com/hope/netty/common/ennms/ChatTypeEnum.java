package com.hope.netty.common.ennms;

/**
 * Created by lijin on  2022/4/24
 */
public enum ChatTypeEnum {
    REGISTER("0","注册"),

    SINGLE("1","单聊"),

    GROUP("2","群聊"),

    ;

    String code;

    String desc;

    ChatTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
