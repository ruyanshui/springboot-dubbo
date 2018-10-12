package com.gaoxi.entity.order;

import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.enumeration.order.OrderStateEnum;
import com.gaoxi.enumeration.order.PayModeEnum;

import java.io.Serializable;
import java.util.List;

/**
 * 订单实体类
 */
public class OrderEntity implements Serializable{

    private static final long serialVersionUID = 8725721164007890927L;

    // 订单编号（主键）
    private String id;

    // 买家ID
    private UserEntity buyer;

    // 卖家详情
    private UserEntity company;

    // 产品-订单关系列表,订单中某一产品的数量
    private List<ProductOrderEntity> productOrderList;

    // 订单状态
    private OrderStateEnum orderStateEnum;

    // 订单到达各个状态的时间
    private List<OrderStateTimeEntity> orderStateTimeList;

    // 支付发昂视
    private PayModeEnum payModeEnum;
}
