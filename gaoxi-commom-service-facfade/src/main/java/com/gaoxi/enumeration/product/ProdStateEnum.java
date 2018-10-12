package com.gaoxi.enumeration.product;

import com.gaoxi.enumeration.BaseEnum;

/**
 * 产品状态的枚举类
 */
public enum ProdStateEnum implements BaseEnum {

    OPEN(1, "上架"),
    CLOSE(2, "下架");

    private int code;

    private String msg;

    ProdStateEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
