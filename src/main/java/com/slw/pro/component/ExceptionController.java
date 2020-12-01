package com.slw.pro.component;

import com.slw.pro.component.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: ExceptionController
 * @Description:
 * @Author: fan jin yang
 * @Date: 2020/11/27
 * @Version: 1.0.0
 **/
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Object businessExceptionHandler(BusinessException businessException){
        return businessException.getMessage();
    }
}
