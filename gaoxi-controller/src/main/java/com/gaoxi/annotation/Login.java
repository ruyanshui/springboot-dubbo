package com.gaoxi.annotation;

import java.lang.annotation.*;

/**
 * 用在Controller层的接口上，表示该接口是否需要登录，只能用在方法上
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {

    // 是否需要登录,默认为true
    public boolean value() default true;
}
