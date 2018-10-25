package com.gaoxi.order.component.pay;

import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.order.component.BaseComponent;
import org.springframework.stereotype.Component;

/**
 * 银联支付
 */
@Component
public class UnionPayCompoent extends BaseComponent{
    @Override
    public void handle(OrderProcessContext orderProcessContext) {
        preHandle(orderProcessContext);
        afterHandle(orderProcessContext);
    }
}
