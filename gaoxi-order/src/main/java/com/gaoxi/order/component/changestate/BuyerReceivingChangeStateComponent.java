package com.gaoxi.order.component.changestate;

import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.entity.order.OrderEntity;
import com.gaoxi.enumeration.order.OrderStateEnum;
import com.gaoxi.order.dao.IOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 买家待收货状态
 */
@Component
public class BuyerReceivingChangeStateComponent extends BaseChangeStateComponent{

    @Autowired
    private IOrderDao orderDao;


    protected void setTargetOrderState(OrderProcessContext orderState) {
        super.preHandle(orderState);
        // 插入物流单号
        insertExpressNo(orderState);
    }

    private void insertExpressNo(OrderProcessContext orderState) {
        // 获取物流单号
        String expressNo = (String) orderState.getOrderProcessReq().getReqData();
        // 订单ID
        String orderId = orderState.getOrderProcessReq().getOrderId();
        // 构造插入请求
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderId);
        orderEntity.setExpressNo(expressNo);
        // 插入物流单号
        orderDao.updateOrder(orderEntity);
    }

    @Override
    public void handle(OrderProcessContext orderProcessContext) {

    }

    @Override
    protected void setTargetOrderState() {
        this.targetOrderState = OrderStateEnum.BUYER_RECEIVING;
    }
}
