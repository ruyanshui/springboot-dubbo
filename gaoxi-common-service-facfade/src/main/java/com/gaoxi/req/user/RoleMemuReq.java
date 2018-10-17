package com.gaoxi.req.user;

import com.gaoxi.req.AbsReq;

import java.util.List;

/**
 * 角色对应菜单订单修改请求
 */
public class RoleMemuReq extends AbsReq {

    /** 角色ID */
    private String roleId;

    /**菜单ID列表*/
    private List<String> menuIdList;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<String> menuIdList) {
        this.menuIdList = menuIdList;
    }

    @Override
    public String toString() {
        return "RoleMemuReq{" + "roleId='" + roleId + '\'' + ", menuIdList=" + menuIdList + '}';
    }
}
