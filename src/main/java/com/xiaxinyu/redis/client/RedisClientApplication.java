package com.xiaxinyu.redis.client;

import com.xiaxinyu.redis.client.listener.StartUpListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application Entry
 *
 * @author XIAXINYU3
 * @date 2019.8.30
 */
@SpringBootApplication
public class RedisClientApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(RedisClientApplication.class);
        springApplication.addListeners(new StartUpListener());
        springApplication.run(args);
    }
}