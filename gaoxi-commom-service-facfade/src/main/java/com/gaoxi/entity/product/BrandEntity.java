package com.gaoxi.entity.product;

import com.gaoxi.entity.user.UserEntity;

import java.io.Serializable;

/**
 * 产品品牌的实体类
 */
public class BrandEntity implements Serializable{

    private static final long serialVersionUID = -2535981474939226540L;

    private String id;

    // 产品品牌名称
    private String brand;

    // 品牌logo url
    private String brandLogoUrl;

    // 品牌所属企业
    private UserEntity companyEntity;

    // 品牌排序，权值越高，排名越前
    private int sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrandLogoUrl() {
        return brandLogoUrl;
    }

    public void setBrandLogoUrl(String brandLogoUrl) {
        this.brandLogoUrl = brandLogoUrl;
    }

    public UserEntity getCompanyEntity() {
        return companyEntity;
    }

    public void setCompanyEntity(UserEntity companyEntity) {
        this.companyEntity = companyEntity;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "BrandEntity{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", brandLogoUrl='" + brandLogoUrl + '\'' +
                ", companyEntity=" + companyEntity +
                ", sort=" + sort +
                '}';
    }
}
