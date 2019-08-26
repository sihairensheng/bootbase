package com.bluefox.spring.bootbase.aop;

import com.bluefox.spring.bootbase.bean.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/*
 * 拦截处理controller中的异常
 * */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseData defaultErrorHandler(HttpServletRequest httpServletRequest, Exception exception) throws Exception {
        logger.error("", exception);
        //ResponseData是自定义异常接受数据的实体
        ResponseData responseData = new ResponseData();
        responseData.setMessage(exception.getMessage());
        if (exception instanceof NoHandlerFoundException) {
            responseData.setCode(404);
        } else {
            responseData.setCode(500);
        }
        responseData.setData(null);
        responseData.setStatus(false);
        return responseData;
    }
}
