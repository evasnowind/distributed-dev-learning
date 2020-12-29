package com.prayerlaputa.distributedlock.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

/**
 * @author chenglong.yu
 * created on 2020/12/29
 */
public class JedisDistributedTool {

    private static final String LOCK_SUCCESS = "OK";
    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 获取分布式锁
     *
     * @param jedis
     * @param lockKey
     * @param requestId
     * @param expireTime
     * @return
     */
    public static boolean getDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        /*
        旧版本jedis写法是
         String result = jedis.set(lockKey, requestId, "nx", "px", expireTime)
        新版本jedis需要使用SetParams
         */
        SetParams setParams = new SetParams();
        setParams.nx();
        setParams.px(expireTime);

        /*
        在redis中将lockKey的值设置为requestId，以便区分到底是
        哪个应用设置的分布式锁。
         */
        String result = jedis.set(lockKey, requestId, setParams);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }

        return false;
    }

    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {
        String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(luaScript, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }

        return false;
    }
}
