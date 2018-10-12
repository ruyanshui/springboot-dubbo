package com.gaoxi.entity.user;

import java.io.Serializable;

/**
 * 权限实体类
 */
public class PermssionEntity implements Serializable{

    private static final long serialVersionUID = -5602010203319064464L;

    private String id;

    // 权限名称
    private String permission;

    // 权限描述
    private String desc;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "PermssionEntity{" +
                "id='" + id + '\'' +
                ", permission='" + permission + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
