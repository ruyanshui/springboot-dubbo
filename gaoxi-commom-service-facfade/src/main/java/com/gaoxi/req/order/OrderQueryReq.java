package com.gaoxi.req.order;

import com.gaoxi.req.QueryReq;

/**
 * 订单信息查询请求
 */
public class OrderQueryReq extends QueryReq {

    private String id;

    // 买家Id,买家只能查自己的订单
    private String buyerId;

    // 买家用户名
    private String buyerName;

    // 买家手机号
    private String buyerPhone;

    // 买家邮箱
    private String buyerMail;

    // 卖家Id,卖家只能查询自己的订单
    private String sellerId;

    // 卖家的企业名称,模糊查询
    private String sellerCompanyName;

    // 卖家手机
    private String sellerPhone;

    // 卖家邮箱
    private String sellerMail;

    // 订单状态
    private Integer orderStateCode;

    // 下次起始时间
    private String placeOrderStartTime;

    // 下单结束时间
    private String placeOrderEndTime;

    // 收件人姓名，模糊查询
    private String reciptentName;

    // 收件人手机
    private String receiptentPhone;

    // 收货地址
    private String recipientLocaton;

    // 支付方式
    private Integer payMOdeCode;

    // 物流单号
    private String expressNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerMail() {
        return buyerMail;
    }

    public void setBuyerMail(String buyerMail) {
        this.buyerMail = buyerMail;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerCompanyName() {
        return sellerCompanyName;
    }

    public void setSellerCompanyName(String sellerCompanyName) {
        this.sellerCompanyName = sellerCompanyName;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public String getSellerMail() {
        return sellerMail;
    }

    public void setSellerMail(String sellerMail) {
        this.sellerMail = sellerMail;
    }

    public Integer getOrderStateCode() {
        return orderStateCode;
    }

    public void setOrderStateCode(Integer orderStateCode) {
        this.orderStateCode = orderStateCode;
    }

    public String getPlaceOrderStartTime() {
        return placeOrderStartTime;
    }

    public void setPlaceOrderStartTime(String placeOrderStartTime) {
        this.placeOrderStartTime = placeOrderStartTime;
    }

    public String getPlaceOrderEndTime() {
        return placeOrderEndTime;
    }

    public void setPlaceOrderEndTime(String placeOrderEndTime) {
        this.placeOrderEndTime = placeOrderEndTime;
    }

    public String getReciptentName() {
        return reciptentName;
    }

    public void setReciptentName(String reciptentName) {
        this.reciptentName = reciptentName;
    }

    public String getReceiptentPhone() {
        return receiptentPhone;
    }

    public void setReceiptentPhone(String receiptentPhone) {
        this.receiptentPhone = receiptentPhone;
    }

    public String getRecipientLocaton() {
        return recipientLocaton;
    }

    public void setRecipientLocaton(String recipientLocaton) {
        this.recipientLocaton = recipientLocaton;
    }

    public Integer getPayMOdeCode() {
        return payMOdeCode;
    }

    public void setPayMOdeCode(Integer payMOdeCode) {
        this.payMOdeCode = payMOdeCode;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    @Override
    public String toString() {
        return "OrderQueryReq{" + "id='" + id + '\'' + ", buyerId='" + buyerId + '\'' + ", buyerName='" + buyerName + '\'' + ", buyerPhone='" + buyerPhone + '\'' + ", buyerMail='" + buyerMail + '\'' + ", sellerId='" + sellerId + '\'' + ", sellerCompanyName='" + sellerCompanyName + '\'' + ", sellerPhone='" + sellerPhone + '\'' + ", sellerMail='" + sellerMail + '\'' + ", orderStateCode=" + orderStateCode + ", placeOrderStartTime='" + placeOrderStartTime + '\'' + ", placeOrderEndTime='" + placeOrderEndTime + '\'' + ", reciptentName='" + reciptentName + '\'' + ", receiptentPhone='" + receiptentPhone + '\'' + ", recipientLocaton='" + recipientLocaton + '\'' + ", payMOdeCode=" + payMOdeCode + ", expressNo='" + expressNo + '\'' + '}';
    }
}
