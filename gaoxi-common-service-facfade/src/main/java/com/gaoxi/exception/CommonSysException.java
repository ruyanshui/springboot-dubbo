package com.gaoxi.exception;

import java.io.Serializable;

/**
 * 通用系统异常
 */
public class CommonSysException extends RuntimeException implements Serializable{

    private static final long serialVersionUID = -5225274106307724083L;

    private ExpCodeEnum codeEnum;

    public CommonSysException(ExpCodeEnum codeEnum) {
        super(codeEnum.getMessage());
        this.codeEnum = codeEnum;
    }

    public CommonSysException() {

    }
}
