package com.bluefox.spring.bootbase.controller;


import com.bluefox.spring.bootbase.bean.ResponseData;
import com.bluefox.spring.bootbase.config.MyConfig;
import com.bluefox.spring.bootbase.config.TomcatConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("spring/rest/hello")
public class HelloController {

    @Autowired
    private Environment environment;

    @Autowired
    private MyConfig myConfig;

    @Autowired
    private  ResponseData responseData;

   @Value("${server.port}")
    private  String  port;

   @Value("${server.tomcat.uri-encoding}")
   private String code;

    @RequestMapping("/world")
    public String world(){
        return "Hello My World";
    }

    @RequestMapping("/fileConfig")
    public TomcatConfig readFileConfig(){
        //通过environment获取
        return this.creatTomcatConfig(environment.getProperty("server.port"),environment.getProperty("server.tomcat.uri-encoding"));
    }

    @RequestMapping("/injectConfig")
    public TomcatConfig readInjectConfig(){
        //通过value注解注入
        return this.creatTomcatConfig(this.port,this.code);
    }

    @RequestMapping("/myConfig")
    public MyConfig readMyConfig(){
        //自定义配置
        return  myConfig;
    }

    private TomcatConfig creatTomcatConfig(String port,String code){
        //controller里方法出现的异常才可以自定义返回信息,请求服务时候的404异常不经过controller无法处理
        //System.err.println(0/0);
        TomcatConfig tomcatConfig = new TomcatConfig();
        tomcatConfig.setPort(port);
        tomcatConfig.setCode(code);
        responseData.saveLog("我是参数");
        return tomcatConfig;
    }

}
