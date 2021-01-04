## 演示如何使用分布式锁  

- redis+lua
    - [X] 使用jedis实现分布式锁
- redission
    - RLock 比较常用
- zookeeper原生api
    - zookeeper在集群较多、写比较多的时候性能会急剧下降，毕竟ZAB协议是基于投票(quorum)的算法，因此对于节点非常多的情况下，如果使用ZK作为注册中心，当大量服务同时上下线时就可能出问题，因为写的过程中ZK不能对外提供服务。
- zookeeper curator
    - 注意直接拷贝curator官方提供的例子  https://github.com/apache/curator/blob/master/curator-examples/src/main/java/locking
    - 并未本地做测试，汇总到这里只是为了方便比较