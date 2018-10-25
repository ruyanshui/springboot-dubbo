package com.gaoxi.controller.order;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.gaoxi.entity.order.OrderEntity;
import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.exception.CommonBizException;
import com.gaoxi.exception.ExpCodeEnum;
import com.gaoxi.facade.order.IOrderService;
import com.gaoxi.req.order.OrderInsertReq;
import com.gaoxi.req.order.OrderQueryReq;
import com.gaoxi.rsp.Result;

import com.gaoxi.util.UserUtil;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class OrderControllerImpl implements IOrderController {

    @Reference(version = "1.0.0")
    private IOrderService orderService;

    @Autowired
    private UserUtil userUtil;


    @Override
    public Result<List<OrderEntity>> findOrdersForBuyer(OrderQueryReq orderQueryReq, HttpServletRequest httpServletRequest) {
        // 获取买家ID
        String buyerId = getUserId(httpServletRequest);
        // 查询
        List<OrderEntity> orderEntityList = orderService.findOrdersForBuyer(orderQueryReq, buyerId);
        // 查询成功
        return Result.newSuccessResult(orderEntityList);
    }

    /**
     * 获取用户Id
     * @param httpServletRequest
     * @return
     */
    private String getUserId(HttpServletRequest httpServletRequest) {
        UserEntity userEntity = userUtil.getUser(httpServletRequest);
        if (userEntity == null) {
            throw new CommonBizException(ExpCodeEnum.UNLOGIN);
        }

        return userEntity.getId();
    }

    @Override
    public Result<List<OrderEntity>> findOrdersForSeller(OrderQueryReq orderQueryReq, HttpServletRequest httpServletRequest) {
        // 获取买家ID
        String buyerId = getUserId(httpServletRequest);
        // 查询
        List<OrderEntity> orderEntityList = orderService.findOrdersForSeller(orderQueryReq, buyerId);

        return Result.newSuccessResult(orderEntityList);
    }

    @Override
    public Result<List<OrderEntity>> findOrdersForAdmin(OrderQueryReq orderQueryReq, HttpServletRequest httpServletRequest) {
        List<OrderEntity> orderEntityList = orderService.findOrdersForAdmin(orderQueryReq);
        return Result.newSuccessResult(orderEntityList);
    }

    @Override
    public Result<String> placeOrder(OrderInsertReq orderInsertReq, HttpServletRequest httpServletRequest) {
        // 将prodIdCountJson --> prodIdCountMap
        transferProdIdCountJson(orderInsertReq);
        // 获取买家Id
        String buyerId = getUserId(httpServletRequest);
        // 下单
        String html = orderService.placeOrder(orderInsertReq, buyerId);
        return Result.newSuccessResult(html);
    }

    private void transferProdIdCountJson(OrderInsertReq orderInsertReq) {
        if (orderInsertReq != null && StringUtils.isNotEmpty(orderInsertReq.getProdIdCountJson())) {
            try {
                Map<String, Long> prodIdCountMapPre = JSON.parse(orderInsertReq.getProdIdCountJson(), Map.class);
                Map<String, Integer> prodIdCountMap = Maps.newHashMap();
                if (prodIdCountMapPre.size() > 0) {
                    for (String key: prodIdCountMapPre.keySet()) {
                        prodIdCountMap.put(key, prodIdCountMapPre.get(key).intValue());
                    }
                }
                orderInsertReq.setProdIdCountMap(prodIdCountMap);
            } catch (ParseException e) {
                throw new CommonBizException(ExpCodeEnum.JSONERROR);
            }
        }
    }

    @Override
    public Result<String> pay(String orderId, HttpServletRequest httpServletRequest) {
        // 获取买家ID
        String buyerId = getUserId(httpServletRequest);

        // 支付
        String html = orderService.pay(orderId, buyerId);

        // 成功
        return Result.newSuccessResult(html);
    }

    @Override
    public Result cancelOrder(String orderId, HttpServletRequest httpServletRequest) {
        // 获取买家ID
        String buyerId = getUserId(httpServletRequest);

        // 取消订单
        orderService.cancelOrder(orderId, buyerId);

        // 成功
        return Result.newSuccessResult();
    }

    @Override
    public Result confirmDelivery(String orderId, String expressNo, HttpServletRequest httpServletRequest) {
        // 获取卖家ID
        String sellerId = getUserId(httpServletRequest);

        // 确认收货
        orderService.confirmDelivery(orderId, expressNo, sellerId);

        // 成功
        return Result.newSuccessResult();
    }

    @Override
    public Result confirmReceive(String orderId, HttpServletRequest httpServletRequest) {
        // 获取买家ID
        String buyerId = getUserId(httpServletRequest);

        // 确认收货
        orderService.confirmReceive(orderId, buyerId);

        // 成功
        return Result.newSuccessResult();
    }
}
