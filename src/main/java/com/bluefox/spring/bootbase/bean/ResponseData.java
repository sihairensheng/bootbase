package com.bluefox.spring.bootbase.bean;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

//自定义的异常消息实体
@Component
public class ResponseData {

    //调用状态
    private Boolean status = true;

    //调用状态消息
    private String message;

    //调用编码
    private Integer code = 200;

    //接口返回的数据
    private Object data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Async
    public void saveLog(String paramter) {
        System.err.println(Thread.currentThread().getName()+"保存日志测试方法");
        //异步方法里面的异常才通过线程池来处理
        throw new IndexOutOfBoundsException("数组越界了");
    }

}
