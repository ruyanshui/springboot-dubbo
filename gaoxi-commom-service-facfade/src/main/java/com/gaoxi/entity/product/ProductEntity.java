package com.gaoxi.entity.product;

import com.gaoxi.entity.user.UserEntity;
import com.gaoxi.enumeration.product.ProdStateEnum;

import java.io.Serializable;
import java.util.List;

/**
 * 产品实体类
 */
public class ProductEntity implements Serializable{

    private static final long serialVersionUID = -7002927579743379042L;

    private String id;

    // 产品名称
    private String prodName;

    // 市场价，保留两位额小时，使用字符串存储，计算时转为数值型
    private String marketPrice;

    // 本店价
    private String shopPrice;

    // 库存
    private int stock;

    // 销量
    private int sales;

    // 产品重量
    private String weight;

    // 产品所属一级分类
    private CategoryEntity topCateEntity;

    // 产品所属二级分类
    private CategoryEntity subCateEntity;

    // 产品所属品牌
    private BrandEntity brandEntity;

    // 是否上架
    private ProdStateEnum prodStateEnum;

    // 产品图片
    private List<ProdImageEntity> prodImageList;

    // 产品详情
    private String content;

    // 产品所属企业信息
    private UserEntity companyEntity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(String shopPrice) {
        this.shopPrice = shopPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public CategoryEntity getTopCateEntity() {
        return topCateEntity;
    }

    public void setTopCateEntity(CategoryEntity topCateEntity) {
        this.topCateEntity = topCateEntity;
    }

    public CategoryEntity getSubCateEntity() {
        return subCateEntity;
    }

    public void setSubCateEntity(CategoryEntity subCateEntity) {
        this.subCateEntity = subCateEntity;
    }

    public BrandEntity getBrandEntity() {
        return brandEntity;
    }

    public void setBrandEntity(BrandEntity brandEntity) {
        this.brandEntity = brandEntity;
    }

    public ProdStateEnum getProdStateEnum() {
        return prodStateEnum;
    }

    public void setProdStateEnum(ProdStateEnum prodStateEnum) {
        this.prodStateEnum = prodStateEnum;
    }

    public List<ProdImageEntity> getProdImageList() {
        return prodImageList;
    }

    public void setProdImageList(List<ProdImageEntity> prodImageList) {
        this.prodImageList = prodImageList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getCompanyEntity() {
        return companyEntity;
    }

    public void setCompanyEntity(UserEntity companyEntity) {
        this.companyEntity = companyEntity;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id='" + id + '\'' +
                ", prodName='" + prodName + '\'' +
                ", marketPrice='" + marketPrice + '\'' +
                ", shopPrice='" + shopPrice + '\'' +
                ", stock=" + stock +
                ", sales=" + sales +
                ", weight='" + weight + '\'' +
                ", topCateEntity=" + topCateEntity +
                ", subCateEntity=" + subCateEntity +
                ", brandEntity=" + brandEntity +
                ", prodStateEnum=" + prodStateEnum +
                ", prodImageList=" + prodImageList +
                ", content='" + content + '\'' +
                ", companyEntity=" + companyEntity +
                '}';
    }
}
