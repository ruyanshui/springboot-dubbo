package com.gaoxi.entity.order;

import com.gaoxi.entity.user.UserEntity;

import java.io.Serializable;

/**
 * 订单实体类
 */
public class OrderEntity implements Serializable{

    private static final long serialVersionUID = 8725721164007890927L;

    // 订单编号（主键）
    private String id;

    // 买家ID
    private UserEntity buyer;
}
