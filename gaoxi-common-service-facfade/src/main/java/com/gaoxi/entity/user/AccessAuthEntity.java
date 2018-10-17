package com.gaoxi.entity.user;

import com.gaoxi.enumeration.HttpMethodEnum;

import java.io.Serializable;

/**
 * 接口访问权限的实体类
 */
public class AccessAuthEntity implements Serializable {

    private static final long serialVersionUID = -6472200944713160118L;

    /// 请求url
    private String url;

    // 接口方法名
    private String methodName;

    // http 请求方式
    private HttpMethodEnum httpMethodEnum;

    // 当前接口是否需要登录
    private String permission;

    public AccessAuthEntity(String url, String methodName, HttpMethodEnum httpMethodEnum, String permission) {
        this.url = url;
        this.methodName = methodName;
        this.httpMethodEnum = httpMethodEnum;
        this.permission = permission;
    }

    public AccessAuthEntity() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public HttpMethodEnum getHttpMethodEnum() {
        return httpMethodEnum;
    }

    public void setHttpMethodEnum(HttpMethodEnum httpMethodEnum) {
        this.httpMethodEnum = httpMethodEnum;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "AccessAuthEntity{" + "url='" + url + '\'' + ", methodName='" + methodName + '\'' + ", httpMethodEnum=" + httpMethodEnum + ", permission='" + permission + '\'' + '}';
    }
}

