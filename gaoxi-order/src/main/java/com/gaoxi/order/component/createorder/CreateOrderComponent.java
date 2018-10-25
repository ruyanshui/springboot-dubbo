package com.gaoxi.order.component.createorder;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.context.OrderProcessContext;
import com.gaoxi.entity.order.OrderEntity;
import com.gaoxi.entity.order.OrderStateTimeEntity;
import com.gaoxi.entity.order.ProductOrderEntity;
import com.gaoxi.entity.order.ReceiptEntity;
import com.gaoxi.entity.product.ProductEntity;
import com.gaoxi.entity.user.LocationEntity;
import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.enumeration.order.OrderStateEnum;
import com.gaoxi.enumeration.order.PayModeEnum;
import com.gaoxi.facade.Productor.IProductorService;
import com.gaoxi.order.component.BaseComponent;
import com.gaoxi.order.dao.IOrderDao;

import com.gaoxi.req.order.OrderInsertReq;
import com.gaoxi.utils.EnumUtil;
import com.gaoxi.utils.KeyGenerator;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Component
public class CreateOrderComponent extends BaseComponent {

    @Autowired
    private IOrderDao orderDao;

    @Reference(version = "1.0.0")
    private IProductorService productorService;

    @Override
    public void handle(OrderProcessContext orderProcessContext) {
        preHandle(orderProcessContext);
        // 获取订单插入请求
        OrderInsertReq orderInsertReq = (OrderInsertReq) orderProcessContext.getOrderProcessReq().getReqData();
        // 创建订单
        createOrder(orderInsertReq);
    }



    private String calculateTotalPrice(OrderInsertReq orderInsertReq) {

        // 获取prodEntityCountMap
        Map<ProductEntity, Integer> productEntityCountMap = orderInsertReq.getProdEntityCountMap();
        // 计算订单总价
        BigDecimal orderTotalPrice = new BigDecimal("0");
        for (ProductEntity productEntity : productEntityCountMap.keySet()) {
            // 本店单价
            BigDecimal shopPrice = new BigDecimal(productEntity.getShopPrice());
            // 购买数量
            BigDecimal count = new BigDecimal(productEntityCountMap.get(productEntity));
            // 单品总价
            BigDecimal prodTotalPrice = shopPrice.multiply(count);
            // 订单总价
            orderTotalPrice = orderTotalPrice.add(prodTotalPrice);
        }
        // 保留两位小数&& 四舍五入
        return orderTotalPrice.setScale(2).toString();
    }

    /**
     * 构造UserEntity
     * @param orderInsertReq 订单创建请求
     * @return OrdersEntity
     */
    private OrderEntity buildUserEntity(OrderInsertReq orderInsertReq) {
        return null;
    }

    /**
     * 创建订单
     * @param orderInsertReq 订单创建请求
     */
    private void createOrder(OrderInsertReq orderInsertReq) {
        // 计算总价
        String orderTotalPrice = calculateTotalPrice(orderInsertReq);

        // 插入order表
        String orderId = insertOrder(orderInsertReq, orderTotalPrice);

        // 插入product_order表
        insertOrderProduct(orderInsertReq.getProdIdCountMap(), orderId);

        // 插入order_state_time表
        insertOrderStateTime(orderId);
    }

    private void insertOrderStateTime(String orderId) {
        OrderStateTimeEntity orderStateTimeEntity = new OrderStateTimeEntity();
        orderStateTimeEntity.setOrderId(orderId);
        orderStateTimeEntity.setTime(new Timestamp(System.currentTimeMillis()));
        orderStateTimeEntity.setOrderStateEnum(OrderStateEnum.INIT);

        orderDao.insertOrderStateTime(orderStateTimeEntity);
    }

    private void insertOrderProduct(Map<String, Integer> prodIdCountMap, String orderId) {
        List<ProductOrderEntity> productOrderEntityList = Lists.newArrayList();

        for (String prodId : prodIdCountMap.keySet()) {
            ProductOrderEntity productOrderEntity = new ProductOrderEntity();

            // 数量
            productOrderEntity.setCount(prodIdCountMap.get(prodId));

            // 产品ID
            ProductEntity productEntity = new ProductEntity();
            productEntity.setId(prodId);
            productOrderEntity.setProductEntity(productEntity);

            // 订单Id
            productOrderEntity.setOrderId(orderId);

            productOrderEntityList.add(productOrderEntity);
        }

        // 插入
        for (ProductOrderEntity productOrderEntity : productOrderEntityList) {
            orderDao.insertOrderProduct(productOrderEntity);
        }
    }

    private String insertOrder(OrderInsertReq orderInsertReq, String orderTotalPrice) {
        // 构造OrdersEntity
        OrderEntity ordersEntity = buildOrdersEntity(orderInsertReq, orderTotalPrice);

        // 插入
        orderDao.insertOrder(ordersEntity);

        return ordersEntity.getId();
    }

    private OrderEntity buildOrdersEntity(OrderInsertReq orderInsertReq, String orderTotalPrice) {
        OrderEntity ordersEntity = new OrderEntity();
        ordersEntity.setId(KeyGenerator.getKey());

        UserEntity buyer = new UserEntity();
        buyer.setId(orderInsertReq.getUserId());
        ordersEntity.setBuyer(buyer);

        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setId(orderInsertReq.getLocationId());
        ordersEntity.setLocationEntity(locationEntity);

        ReceiptEntity receiptEntity = new ReceiptEntity();
        receiptEntity.setId(orderInsertReq.getReceiptId());
        ordersEntity.setReceiptEntity(receiptEntity);

        ordersEntity.setPayModeEnum(EnumUtil.codeOf(PayModeEnum.class, orderInsertReq.getPayModeCode()));

        ordersEntity.setRemark(orderInsertReq.getRemark());

        ordersEntity.setCompany(orderInsertReq.getProdEntityCountMap().keySet().toArray(new ProductEntity[1])[0].getCompanyEntity());

        ordersEntity.setTotalPrice(orderTotalPrice);

        ordersEntity.setOrderStateEnum(OrderStateEnum.INIT);

        return ordersEntity;
    }
}
