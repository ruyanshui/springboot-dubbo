package com.gaoxi.order.component.pay;

import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.enumeration.order.PayModeEnum;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.CommonSysException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.order.component.BaseComponent;
import com.gaoxi.req.order.OrderInsertReq;
import com.gaoxi.utils.EnumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 支付通用组件，会根据请求中的“支付类型"，选择对应组件
 */
@Component
public class CommonPayComponent extends BaseComponent{


    @Autowired
    private AlipayCompoent alipayCompoent;

    @Autowired
    private WechatPayCompoent wechatPayCompoent;

    @Autowired
    private UnionPayCompoent unionPayCompoent;

    @Override
    public void handle(OrderProcessContext orderProcessContext) {
        preHandle(orderProcessContext);
        // 处理支付请求
        doPay(orderProcessContext);
        afterHandle(orderProcessContext);
    }

    private void doPay(OrderProcessContext orderProcessContext) {
        // 获取支付方式
        PayModeEnum payModeEnum = getPayModeEnum(orderProcessContext);
        switch (payModeEnum) {
            case ALIPAY:
                alipayCompoent.handle(orderProcessContext);
                break;
            case WECHAT:
                wechatPayCompoent.handle(orderProcessContext);
                break;
            case UNIONPAY:
                unionPayCompoent.handle(orderProcessContext);
                break;
        }
    }

    private PayModeEnum getPayModeEnum(OrderProcessContext orderProcessContext) {
        // 获取OrderInsertReq
        OrderInsertReq orderInsertReq = (OrderInsertReq) orderProcessContext.getOrderProcessReq().getReqData();
        // 获取支付方式Code
        Integer payModeCode = orderInsertReq.getPayModeCode();
        if (payModeCode == null) {
            throw new CommonSysException(ExpCodeEnum.PAYMODE_NULL);
        }
        // 获取支付方式
        PayModeEnum payModeEnum = EnumUtil.codeOf(PayModeEnum.class, payModeCode);
        if (payModeEnum == null) {
            throw new CommonBizException(ExpCodeEnum.PAYMODECODE_ERROR);
        }
        return payModeEnum;
    }
}
