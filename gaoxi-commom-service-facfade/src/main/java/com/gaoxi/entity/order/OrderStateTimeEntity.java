package com.gaoxi.entity.order;

import com.gaoxi.enumeration.order.OrderStateEnum;

import java.io.Serializable;
import java.sql.Timestamp;

/*
订单到达各个状态的时间
 */
public class OrderStateTimeEntity implements Serializable {

    private static final long serialVersionUID = -9115607930337395297L;

    private String orderId;

    private OrderStateEnum orderStateEnum;

    private Timestamp time;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderStateEnum getOrderStateEnum() {
        return orderStateEnum;
    }

    public void setOrderStateEnum(OrderStateEnum orderStateEnum) {
        this.orderStateEnum = orderStateEnum;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "OrderStateTimeEntity{" +
                "orderId='" + orderId + '\'' +
                ", orderStateEnum=" + orderStateEnum +
                ", time=" + time +
                '}';
    }
}
