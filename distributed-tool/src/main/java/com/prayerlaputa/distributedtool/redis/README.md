## 程序说明  

### 使用jedis实现分布式锁

1. 程序说明

参见`JedisDistributedTool`中下面两个方法：
- `boolean getDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime)`
    - 加锁，返回是否加锁成功
    - 使用redis中 set命令+nx、px参数的方式，保证加锁的原子性
- `boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId)`
    - 解锁，返回是否解锁成功
    - 使用redis + lua脚本的方式，保证解锁操作中 get、del两个操作一起执行的原子性

测试代码参见`com.prayerlaputa.distributedlock.TestDistributedLock`

2. 参考资料
[慢谈 Redis 实现分布式锁 以及 Redisson 源码解析](https://www.jianshu.com/p/91b9f7569300)

3. 知识点复习：
- jedis释放
    - 从JedisPool中获取后，释放Jedis对象时，可以利用try-with-resources的写法，看源码可以看到Jedis实现了Closable接口
    - 注意Closable接口的使用
   
### 使用jedis一个分布式计数器  

1. 程序说明

参见`JedisDistributedTool`中如下接口：
- `long incrementAndGetCounter(Jedis jedis, String counterKey)
    - 自增1，并返回自增后的结果
- `long getCounter(Jedis jedis, String counterKey)`
    - 返回当前计数器结果
- `long addAndGetCounter(Jedis jedis, String counterKey, long delta)`
    - 计数器添加
- `boolean setCounter(Jedis jedis, String counterKey, long val)`
    - 设置计数器值为val
- `long decrementAndGetCounter(Jedis jedis, String counterKey)`
    - 计数器减1，并返回操作后的值

考虑到只是要实现一个基于redis的分布式计数器，简单实现了下，只调用了redis的命令，没有复杂操作，因此上面几个方法都是原子操作。  
若要加上超时、比较等复杂操作，就需要用lua保证原子性了。

测试代码参见`com.prayerlaputa.distributedlock.TestDistributedCounter`

2. 遇到的问题

一开始使用jedis时，本想多线程复用一个jedis实例，结果会报错，遇到如下错误：
```text
redis.clients.jedis.exceptions.JedisDataException: redis.clients.jedis.exceptions.JedisDataException: ERR Protocol error: expected '$', got ' '
```
不同线程使用不同jedis实例后即可解决该问题。
