package com.xiaxinyu.redis.client.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * Startup Listener
 *
 * @author XIAXINYU3
 * @date 2019.8.30
 */
@Slf4j
public class StartUpListener implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("RedisClient's application context is ready");
    }
}
