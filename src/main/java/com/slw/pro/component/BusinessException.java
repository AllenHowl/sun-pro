package com.slw.pro.component;

/**
 * @ClassName: BusinessException
 * @Description:
 * @Author: fan jin yang
 * @Date: 2020/11/27
 * @Version: 1.0.0
 **/
public class BusinessException extends RuntimeException{

    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }

    public BusinessException(Integer code, String message){
        this.errorCode = new ErrorCode(code,message);
    }

    @Override
    public String getMessage() {
        return String.format("code : %s ; msg : %s", this.errorCode.getCode(), this.errorCode.getMessage());
    }
}
