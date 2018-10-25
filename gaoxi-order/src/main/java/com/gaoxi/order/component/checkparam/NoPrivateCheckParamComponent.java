package com.gaoxi.order.component.checkparam;

import com.gaoxi.context.OrderProcessContext;
import org.springframework.stereotype.Component;

@Component
public class NoPrivateCheckParamComponent extends BaseCheckParamComponent{
    @Override
    protected void privateParamCheck(OrderProcessContext orderProcessContext) {

    }

    @Override
    public void handle(OrderProcessContext orderProcessContext) {

    }
}
