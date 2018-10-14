package com.gaoxi.entity.user;

import java.io.Serializable;

/**
 * 地址信息
 */
public class LocationEntity implements Serializable{

    private static final long serialVersionUID = -8575391133044715761L;

    private String id;

    // 详细地址
    private String location;

    // 收货人姓名
    private String name;

    // 收货人手机号
    private String phone;

    // 邮编
    private String postCode;

    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCodel) {
        this.postCode = postCodel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LocationEntity{" + "id='" + id + '\'' + ", location='" + location + '\'' + ", name='" + name + '\'' + ", phone='" + phone + '\'' + ", postCodel='" + postCode + '\'' + ", userId='" + userId + '\'' + '}';
    }
}
