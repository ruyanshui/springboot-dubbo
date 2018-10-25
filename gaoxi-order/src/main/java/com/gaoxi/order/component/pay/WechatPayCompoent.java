package com.gaoxi.order.component.pay;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.order.component.BaseComponent;
import org.springframework.stereotype.Component;

@Component
public class WechatPayCompoent extends BaseComponent{
    @Override
    public void handle(OrderProcessContext orderProcessContext) {
        preHandle(orderProcessContext);
        afterHandle(orderProcessContext);
    }
}
