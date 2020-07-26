package com.xiaxinyu.redis.client;

import com.xiaxinyu.redis.client.api.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisLockTest {

    @Autowired
    RedisLock redisLock;

    @Test
    public void test_lock1_tryLock() {
        String localKey = "summer";
        String requestId = String.format("%s-%s", localKey, "001");
        redisLock.tryLock(localKey, requestId, 100, TimeUnit.SECONDS);
    }

    @Test
    public void test_lock2_getLock() {
        String localKey = "summer";
        String key = redisLock.getLock(localKey);
        log.info("key={}", key);
        Assert.assertNotNull(key);
    }

    @Test
    public void test_lock3_releaseLock() {
        String localKey = "summer";
        String requestId = String.format("%s-%s", localKey, "001");
        redisLock.releaseLock(localKey, requestId);
    }

    @Test
    public void test_lock4_tryLock_with_5_threads() throws InterruptedException {
        String localKey = "summer";
        String requestId = String.format("%s-%s", localKey, "001");

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                Thread thread = Thread.currentThread();
                boolean flag = redisLock.tryLock(localKey, requestId, 100, TimeUnit.SECONDS);
                log.info("ThreadName={}, tryLock={}", thread.getName(), flag);
            });
        }

        Thread.sleep(100000);
    }
}
