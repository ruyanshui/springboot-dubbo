package com.gaoxi.entity.user;

import java.io.Serializable;

/**
 * 角色实体类
 */
public class RoleEntity implements Serializable{

    private static final long serialVersionUID = 2048224636682990966L;

    private String id;

    // 角色名称
    private String name;

    // 角色描述
    private String desc;

    // 该角色能访问的菜单
    private List<MenuEntity> menuList;
}
