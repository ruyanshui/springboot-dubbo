package com.gaoxi.entity.product;

import java.io.Serializable;

/**
 * 产品图片的实体类
 */
public class ProdImageEntity implements Serializable{

    private static final long serialVersionUID = 1684386865479167871L;

    private String id;

    // 图片的URL
    private String imageURL;

    // 图片所属产品的ID
    private String productId;

    @Override
    public String toString() {
        return "ProdImageEntity{" +
                "id='" + id + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", productId='" + productId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
