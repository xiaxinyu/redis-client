package com.xiaxinyu.redis.client;

import com.xiaxinyu.redis.client.api.RedisAPI;
import com.xiaxinyu.redis.client.model.Request;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisAPITest {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisAPI redisApi;

    @Test
    public void test_OpsForValue() {
        Request request = Request.builder().id("001").content("I love Summer").build();

        ValueOperations<String, Request> operations = redisTemplate.opsForValue();
        operations.set("001", request);

        boolean exists = redisTemplate.hasKey(request.getId());
        Assert.assertTrue(exists);

        Request req = (Request) redisTemplate.opsForValue().get(request.getId());

        Assert.assertTrue(req.equals(request));
    }

    @Test
    public void test_expireAt() throws InterruptedException {
        redisApi.expireKey("001", 10, TimeUnit.SECONDS);

        Thread.sleep(12000);

        boolean exists = redisApi.hasKey("001");

        Assert.assertFalse(exists);
    }
}
