package com.bluefox.spring.bootbase.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

//线程池配置类
@Configuration
public class AsyncTaskExecutePool implements AsyncConfigurer {

    private Logger logger = LoggerFactory.getLogger(AsyncTaskExecutePool.class);

    @Autowired
    private TaskThreadPoolConfig taskThreadPoolConfig;

    @Override
    public Executor getAsyncExecutor() {
        //设置线程池参数
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(taskThreadPoolConfig.getCorePoolSize());
        threadPoolTaskExecutor.setMaxPoolSize(taskThreadPoolConfig.getMaxPoolSize());
        threadPoolTaskExecutor.setKeepAliveSeconds(taskThreadPoolConfig.getKeepAliveSeconds());
        threadPoolTaskExecutor.setQueueCapacity(taskThreadPoolConfig.getQueueCapacity());
        threadPoolTaskExecutor.setThreadNamePrefix(taskThreadPoolConfig.getThreadNamePrefix());
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncUncaughtExceptionHandler();
    }

    class MyAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler{

        //处理异步调用方法中的异常
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            logger.error("Exception message:"+throwable.getMessage());
            logger.error("Method name:"+method);
            for (Object param:objects) {
                System.err.println("Paramter value:"+param);
            }
        }
    }

}
