package com.gaoxi.facade.order;

import com.gaoxi.entity.order.OrderEntity;
import com.gaoxi.req.order.OrderInsertReq;
import com.gaoxi.req.order.OrderQueryReq;

import java.util.List;
public interface IOrderService {

        List<OrderEntity> findOrdersForBuyer(OrderQueryReq orderQueryReq, String buyerId);

        List<OrderEntity> findOrdersForSeller(OrderQueryReq orderQueryReq, String sellerId);

        List<OrderEntity> findOrdersForAdmin(OrderQueryReq orderQueryReq);

        String placeOrder(OrderInsertReq orderInsertReq, String buyerId);

        String pay(String orderId, String userId);

        void cancelOrder(String orderId, String userId);

        void confirmDelivery(String orderId, String expressNo, String userId);

        void confirmReceive(String orderId, String userId);

}
