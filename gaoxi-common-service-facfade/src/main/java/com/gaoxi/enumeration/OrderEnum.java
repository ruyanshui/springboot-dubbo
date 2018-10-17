package com.gaoxi.enumeration;

/**
 * 排序规则枚举
 */
public enum OrderEnum {

    DESC(1, "DESC"),
    ASC(2, "ASC");

    private int code;

    private String msg;

    OrderEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
