package com.gaoxi.req.order;

import com.gaoxi.req.AbsReq;

/**
 * 订单更新请求
 */
public class OrderUpdateReq extends AbsReq {

    // 订单Id
    private String id;

    // 订单状态
    private Integer orderStateCode;

    // 物流单号
    private String expressNo;

    // 支付方式
    private Integer payModeCode;

    // 订单总金额
    private String totalPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getOrderStateCode() {
        return orderStateCode;
    }

    public void setOrderStateCode(Integer orderStateCode) {
        this.orderStateCode = orderStateCode;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public Integer getPayModeCode() {
        return payModeCode;
    }

    public void setPayModeCode(Integer payModeCode) {
        this.payModeCode = payModeCode;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderUpdateReq{" + "id='" + id + '\'' + ", orderStateCode=" + orderStateCode + ", expressNo='" + expressNo + '\'' + ", payModeCode=" + payModeCode + ", totalPrice='" + totalPrice + '\'' + '}';
    }
}
