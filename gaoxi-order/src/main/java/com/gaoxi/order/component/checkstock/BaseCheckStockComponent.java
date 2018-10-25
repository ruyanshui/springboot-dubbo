package com.gaoxi.order.component.checkstock;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.entity.product.ProductEntity;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.facade.Productor.IProductorService;
import com.gaoxi.order.component.BaseComponent;
import com.gaoxi.req.order.OrderInsertReq;

import java.util.Map;

/**
 * 库存校验组件
 */
public class BaseCheckStockComponent extends BaseComponent{

    @Reference(version = "1.0.0")
    private IProductorService productorService;

    @Override
    public void handle(OrderProcessContext orderProcessContext) {
        preHandle(orderProcessContext);
        // 获取产品Entity的Map
        OrderInsertReq orderInsertReq = (OrderInsertReq) orderProcessContext.getOrderProcessReq().getReqData();
        Map<ProductEntity,Integer> prodEntityCountMap = orderInsertReq.getProdEntityCountMap();
        // 检查库存
        checkStock(prodEntityCountMap);

        afterHandle(orderProcessContext);
    }

    private void checkStock(Map<ProductEntity, Integer> prodEntityCountMap) {
        for (ProductEntity productEntity : prodEntityCountMap.keySet()) {
            // 获取购买量
            Integer count = prodEntityCountMap.get(productEntity);
            // 校验
            if (productEntity.getStock() < count) {
                throw new CommonBizException(ExpCodeEnum.STOCK_LOW);
            }
        }
    }
}
