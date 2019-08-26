package com.bluefox.spring.bootbase;

import com.bluefox.spring.bootbase.config.RandomProt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class BootBaseApplication {

    public static void main(String[] args) {
        //启动自动生成端口参数设置
        new RandomProt(args);
        SpringApplication.run(BootBaseApplication.class, args);
    }

}
