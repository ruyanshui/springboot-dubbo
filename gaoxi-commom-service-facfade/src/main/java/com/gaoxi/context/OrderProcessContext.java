package com.gaoxi.context;

import com.gaoxi.req.order.OrderProcessReq;

import java.io.Serializable;

/**
 * 订单受理上下文
 * @param <T>
 */
public class OrderProcessContext<T> implements Serializable {

    private static final long serialVersionUID = 7563860757430654037L;

    // 是否终止下面的流程
    private boolean isStop = false;

    // 订单受理请求
    private OrderProcessReq orderProcessReq;

    // 订单受理结果
    private T orderProcessRsq;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    public OrderProcessReq getOrderProcessReq() {
        return orderProcessReq;
    }

    public void setOrderProcessReq(OrderProcessReq orderProcessReq) {
        this.orderProcessReq = orderProcessReq;
    }

    public T getOrderProcessRsq() {
        return orderProcessRsq;
    }

    public void setOrderProcessRsq(T orderProcessRsq) {
        this.orderProcessRsq = orderProcessRsq;
    }

    @Override
    public String toString() {
        return "OrderProcessContext{" +
                "isStop=" + isStop +
                ", orderProcessReq=" + orderProcessReq +
                ", orderProcessRsq=" + orderProcessRsq +
                '}';
    }

}