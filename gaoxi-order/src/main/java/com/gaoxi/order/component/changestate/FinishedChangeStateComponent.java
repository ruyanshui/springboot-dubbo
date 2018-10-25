package com.gaoxi.order.component.changestate;

import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.enumeration.order.OrderStateEnum;
import org.springframework.stereotype.Component;

@Component
public class FinishedChangeStateComponent extends BaseChangeStateComponent{
    @Override
    protected void setTargetOrderState() {
        this.targetOrderState = OrderStateEnum.FINISHED;
    }

    @Override
    public void handle(OrderProcessContext orderProcessContext) {

    }
}
