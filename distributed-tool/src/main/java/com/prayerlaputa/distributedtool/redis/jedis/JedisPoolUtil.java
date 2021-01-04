package com.prayerlaputa.distributedtool.redis.jedis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author chenglong.yu
 * created on 2020/12/29
 */
public class JedisPoolUtil {

    private JedisPoolUtil() {}

    private static volatile JedisPool jedisPool = null;

    private static final String LOCAL_REDIS_HOST = "127.0.0.1";
    private static final int LOCAL_REDIS_PORT = 6379;

    public static JedisPool getJedisPoolInstance() {
        if (null == jedisPool) {
            synchronized (JedisPoolUtil.class) {
                if (null == jedisPool) {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(1000);
                    poolConfig.setMaxIdle(32);
                    poolConfig.setMinIdle(8);
                    poolConfig.setTestOnBorrow(true);
                    poolConfig.setMaxWaitMillis(1000 * 10);
                    jedisPool = new JedisPool(poolConfig, LOCAL_REDIS_HOST, LOCAL_REDIS_PORT);
                }
            }
        }

        return jedisPool;
    }
}
