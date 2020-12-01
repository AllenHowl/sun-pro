package com.slw.pro.component;

import lombok.Data;

/**
 * @ClassName: ErrorCode
 * @Description:
 * @Author: fan jin yang
 * @Date: 2020/11/27
 * @Version: 1.0.0
 **/
@Data
public class ErrorCode {

    private Integer code;

    private String message;

    public ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
