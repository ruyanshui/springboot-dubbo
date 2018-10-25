package com.gaoxi.order.component.idempotent;

import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.enumeration.order.OrderStateEnum;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class PayIdempotentComponent extends BaseIdempotencyComponent{
    @Override
    protected void setAllowStateList() {
        // 订单为INIT才允许支付
        this.allowStateList = Arrays.asList(OrderStateEnum.INIT);
    }

    @Override
    public void handle(OrderProcessContext orderProcessContext) {

    }
}
