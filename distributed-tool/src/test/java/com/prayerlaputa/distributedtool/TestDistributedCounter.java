package com.prayerlaputa.distributedtool;

import com.prayerlaputa.distributedtool.redis.jedis.JedisDistributedTool;
import com.prayerlaputa.distributedtool.redis.jedis.JedisPoolUtil;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * @author chenglong.yu
 * created on 2021/1/4
 */
public class TestDistributedCounter {

    private static final String COUNTER_KEY = "distributedStockCounter";

    @Before
    public void init() {
        try (Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource()) {
            boolean res = JedisDistributedTool.setCounter(jedis, COUNTER_KEY, 0);
            System.out.println("init counter=0(operation res = " + res + ").");
        }
    }

    private static final int THREAD_REPEAT_NUM = 20;

    public static void incrBatch() {
        System.out.println("incrBatch, cur thread=" + Thread.currentThread().getName());
        try (Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource()) {
            for (int i = 0; i < THREAD_REPEAT_NUM; i++) {
                long curVal = JedisDistributedTool.incrementAndGetCounter(jedis, COUNTER_KEY);
                System.out.println("thread " + Thread.currentThread().getName() + " cur counter=" + curVal);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("incrBatch finish.");
    }

    public static void decrBatch() {
        System.out.println("decrBatch, cur thread=" + Thread.currentThread().getName());
        try (Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource()) {
            for (int i = 0; i < THREAD_REPEAT_NUM; i++) {
                long curVal = JedisDistributedTool.decrementAndGetCounter(jedis, COUNTER_KEY);
                System.out.println("thread " + Thread.currentThread().getName() + " cur counter=" + curVal);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("decrBatch finish.");
    }

    @Test
    public void testIncrStock() throws InterruptedException {

        Thread t1 = new Thread(TestDistributedCounter::incrBatch, "t1");
        Thread t2 = new Thread(TestDistributedCounter::incrBatch, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }

    public void setCounterVal() {
        try(Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource()) {
            long res = JedisDistributedTool.addAndGetCounter(jedis, COUNTER_KEY, 100);
            System.out.println("setCounterVal res = " + res);
        }

    }

    public void getCounterVal() {
        try(Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource()) {
            long res = JedisDistributedTool.getCounter(jedis, COUNTER_KEY);
            System.out.println("getCounterVal res = " + res);
        }
    }

    @Test
    public void testDecrStock() throws InterruptedException {
        setCounterVal();

        Thread t1 = new Thread(TestDistributedCounter::decrBatch, "t1");
        Thread t2 = new Thread(TestDistributedCounter::decrBatch, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        getCounterVal();
    }
}
