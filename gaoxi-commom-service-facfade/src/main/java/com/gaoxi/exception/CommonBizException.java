package com.gaoxi.exception;

import java.io.Serializable;

public class CommonBizException extends RuntimeException implements Serializable{

    private static final long serialVersionUID = 8431959082924642368L;

    private ExpCodeEnum codeEnum;

    public CommonBizException(String message, ExpCodeEnum codeEnum) {
        super(message);
        this.codeEnum = codeEnum;
    }

    public CommonBizException(ExpCodeEnum codeEnum) {
        this.codeEnum = codeEnum;
    }

    public CommonBizException() { }

    public ExpCodeEnum getCodeEnum() {
        return codeEnum;
    }

    public void setCodeEnum(ExpCodeEnum codeEnum) {
        this.codeEnum = codeEnum;
    }
}
