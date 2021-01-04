package com.prayerlaputa.distributedtool;

import com.prayerlaputa.distributedtool.redis.jedis.JedisDistributedTool;
import com.prayerlaputa.distributedtool.redis.jedis.JedisPoolUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * @author chenglong.yu
 * created on 2020/12/29
 */
public class TestDistributedLock {

    private static final String LOCK_KEY = "test";

    @Test
    public void testJedisLock() {
        try(Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource()) {
            String requestId = String.valueOf(UUID.randomUUID());
            JedisDistributedTool.getDistributedLock(jedis, LOCK_KEY, requestId, 100000);
            //do business logic
            System.out.println("do something......");
            JedisDistributedTool.releaseDistributedLock(jedis, LOCK_KEY, requestId);
        }
    }
}
