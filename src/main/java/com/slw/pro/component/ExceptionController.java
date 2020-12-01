package com.slw.pro.component;

import com.slw.pro.component.BusinessException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ExceptionController {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object businessExceptionHandler(Exception e){
        if (e instanceof BusinessException) {
            return e.getMessage();
        }
        log.error(" e = ", e);
        return "服务繁忙！";
    }
}
