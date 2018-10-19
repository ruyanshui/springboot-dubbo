package com.gaoxi.entity.user;

import java.io.Serializable;

/**
 * 菜单实体类
 */
public class MenuEntity implements Serializable{

    private static final long serialVersionUID = -4805109635444438710L;

    private String id;

    private String menuName;

    // 菜单对应页面的url
    private String url;

    // 父菜单的ID
    private String parentId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "MenuEntity{" +
                "id='" + id + '\'' +
                ", menuName='" + menuName + '\'' +
                ", url='" + url + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}