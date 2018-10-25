package com.gaoxi.order.component.datatransfer;

import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.order.component.BaseComponent;

/**
 * 数据转化组件的父类
 */
public abstract class BaseDataTransferComponent extends BaseComponent{

    public void handle(OrderProcessContext orderProcessContext) {
        preHandle(orderProcessContext);
        transfer(orderProcessContext);
        afterHandle(orderProcessContext);
    }

    protected abstract void transfer(OrderProcessContext orderProcessContext);
}
