package com.gaoxi.controller.order;

import com.gaoxi.annotation.Login;
import com.gaoxi.entity.order.OrderEntity;
import com.gaoxi.req.order.OrderInsertReq;
import com.gaoxi.req.order.OrderQueryReq;
import com.gaoxi.rsp.Result;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IOrderController {

    /**
     * 订单查询，供买家查询
     * @param orderQueryReq
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/buyerOrders")
    @Login
    Result<List<OrderEntity>> findOrdersForBuyer(OrderQueryReq orderQueryReq, HttpServletRequest httpServletRequest);

    /**
     * 订单查询（供卖家查询)
     * @param orderQueryReq
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/sellerOrders")
    @Login
    Result<List<OrderEntity>> findOrdersForSeller(OrderQueryReq orderQueryReq, HttpServletRequest httpServletRequest);

    /**
     * 订单查询（供管理员查询)
     * @param orderQueryReq
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/adminOrders")
    @Login
    Result<List<OrderEntity>> findOrdersForAdmin(OrderQueryReq orderQueryReq, HttpServletRequest httpServletRequest);

    /**
     * 下单（包含支付）
     * @param orderInsertReq
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/placeOrder")
    @Login
    Result<String> placeOrder(OrderInsertReq orderInsertReq, HttpServletRequest httpServletRequest);

    /**
     * 支付（仅供状态为“待支付"的订单使用）
     * @param orderId
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/pay")
    @Login
    Result<String> pay(String orderId, HttpServletRequest httpServletRequest);

    /**
     * 取消订单（仅供状态为"待支付"的订单使用）
     * @param orderId
     * @param httpServletRequest
     * @return
     */
    @DeleteMapping("order")
    @Login
    Result cancelOrder(String orderId, HttpServletRequest httpServletRequest);

    /**
     * 卖家确认发货
     * @param orderId
     * @param httpServletRequest
     * @return
     */
    @PutMapping("/delivery")
    @Login
    Result confirmDelivery(String orderId, String expressNo,HttpServletRequest httpServletRequest);

    /**
     * 买家确认收货
     * @param orderId
     * @param httpServletRequest
     * @return
     */
    @PutMapping
    @Login
    Result confirmReceive(String orderId, HttpServletRequest httpServletRequest);

}
