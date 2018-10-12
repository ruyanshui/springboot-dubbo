package com.gaoxi.req.order;

import com.gaoxi.enumeration.BaseEnum;

/**
 * 订单受理请求枚举
 */
public enum ProcessReqEnum implements BaseEnum {

    PlaceOrder(1, "PlaceOrderProcessor"),
    Pay(2, "PayProcessor"),
    CancelOrder(2, "CancelOrderProcessor"),
    ConfirmDelivery(4, "ConfirmDeliveryProcessor"),
    ConfirmReceive(5, "ConfirmReceiveProcessor");


    private int code;

    private String msg;

    ProcessReqEnum(int code, String msg) {
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
