package com.gaoxi.entity.order;

import com.gaoxi.enumeration.order.ReceiptTypeEnum;

import java.io.Serializable;

/**
 *
 * 发票信息实体类
 */
public class ReceiptEntity implements Serializable{

    private static final long serialVersionUID = -869727830245688397L;

    private String id;

    // 发票类型
    private ReceiptTypeEnum receiptTypeEnum;

    // 发票抬头
    private String taxPayerNo;

    // 纳税人识别号
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ReceiptTypeEnum getReceiptTypeEnum() {
        return receiptTypeEnum;
    }

    public void setReceiptTypeEnum(ReceiptTypeEnum receiptTypeEnum) {
        this.receiptTypeEnum = receiptTypeEnum;
    }

    public String getTaxPayerNo() {
        return taxPayerNo;
    }

    public void setTaxPayerNo(String taxPayerNo) {
        this.taxPayerNo = taxPayerNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ReceiptEntity{" + "id='" + id + '\'' + ", receiptTypeEnum=" + receiptTypeEnum + ", taxPayerNo='" + taxPayerNo + '\'' + ", content='" + content + '\'' + '}';
    }
}
