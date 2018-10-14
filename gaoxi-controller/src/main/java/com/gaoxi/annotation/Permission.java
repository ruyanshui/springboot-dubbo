package com.gaoxi.annotation;

import java.lang.annotation.*;

/**
 * 用在Controller层的接口的方法上，表示访问该接口所需要的权限
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {

    public String value();
}
