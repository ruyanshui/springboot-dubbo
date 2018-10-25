package com.gaoxi.order.component.idempotent;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.entity.order.OrderEntity;
import com.gaoxi.enumeration.order.OrderStateEnum;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.CommonSysException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.order.component.BaseComponent;
import com.gaoxi.order.dao.IOrderDao;
import com.gaoxi.req.order.OrderQueryReq;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 幂等性检查的父类
 */
public abstract class BaseIdempotencyComponent extends BaseComponent{

    // 当前订单的状态必须是如下状态之一
    protected List<OrderStateEnum> allowStateList;

    @Autowired
    private IOrderDao orderDao;

    /**
     * 幂等性检查的处理函数
     * @param orderProcessContext 订单受理上下文
     */
    public void handle(OrderProcessContext orderProcessContext) {

        setAllowStateList();

        preHandle(orderProcessContext);

        // 获取订单当前状态
        OrderStateEnum curOrderState = getOrderState(orderProcessContext);

        // 幂等性检查
        checkIdempotency(curOrderState, allowStateList);

        afterHandle(orderProcessContext);
    }

    private void checkIdempotency(OrderStateEnum curOrderState, List<OrderStateEnum> allowStateList) {
        if (CollectionUtils.isEmpty(allowStateList)) {
            throw new CommonSysException(ExpCodeEnum.AllowStateList_NULL);
        }
        for (OrderStateEnum orderStateEnum : allowStateList) {
            if (orderStateEnum == curOrderState) {
                // 幂等性检查通过
                return;
            }
        }
        // 幂等性检查不通过
        throw new CommonBizException(ExpCodeEnum.NO_REPEAT);
    }

    private OrderStateEnum getOrderState(OrderProcessContext orderProcessContext) {
        // 获取订单ID
        String orderId = orderProcessContext.getOrderProcessReq().getOrderId();
        if (StringUtils.isEmpty(orderId)) {
            throw new CommonBizException(ExpCodeEnum.PROCESSREQ_ORDERID_NULL);
        }

        // 查询订单
        OrderQueryReq orderQueryReq = new OrderQueryReq();
        orderQueryReq.setId(orderId);
        List<OrderEntity> ordersEntityList = orderDao.findOrders(orderQueryReq);

        // 订单不存在
        if (CollectionUtils.isEmpty(ordersEntityList)) {
            throw new CommonBizException(ExpCodeEnum.ORDER_NULL);
        }

        // 获取订单状态
        // TODO 更新订单状态时，要连带更新order表中的状态
        OrderStateEnum orderStateEnum = ordersEntityList.get(0).getOrderStateEnum();

        // 订单存在 & 订单状态不存在
        if (orderStateEnum == null) {
            throw new CommonBizException(ExpCodeEnum.ORDER_STATE_NULL);
        }

        // 返回订单状态
        return orderStateEnum;
    }

    /**
     * 设置订单允许的状态（子类必须重写这个函数）
     */
    protected abstract void setAllowStateList();
}
