package com.gaoxi.order.component.idempotent;

import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.enumeration.order.OrderStateEnum;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ConfirmDeliveryIdempotentComponent extends BaseIdempotencyComponent{
    @Override
    protected void setAllowStateList() {
        // 订单状态只有为"买家待收货"才允许确认发货
        this.allowStateList = Arrays.asList(OrderStateEnum.BUYER_PAID);
    }

    @Override
    public void handle(OrderProcessContext orderProcessContext) {

    }
}
