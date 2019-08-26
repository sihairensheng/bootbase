package com.bluefox.spring.bootbase.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//自定义配置类
@ConfigurationProperties(prefix = "com.bluefox")
@Component
public class MyConfig {

    private String port;

    private  String code;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
