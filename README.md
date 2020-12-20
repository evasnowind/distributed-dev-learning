# distributed-dev-learning

本项目主要给出一些常见分布式技术的demo，供学习、参考。

各个模块用途如下：

| 技术           | 模块                                                         | 状态  |
| -------------- | ------------------------------------------------------------ | ----- |
| 一致性哈希算法 | consistent-hash                                              | done  |
| 分布式锁       | distributed-lock                                             | to do |
| 分布式事务     | distributed-transaction                                      | done  |
| 一致性算法     | distributed-consensus-algorithm                              | to do |
| 数据库数据分片 | db-data-sharding                                             | done  |
| nginx应用      |                                                              |       |
| netty使用      | [使用netty仿写微信IM](https://github.com/evasnowind/netty-im) | done  |
| RPC            | [rpc-learning](https://github.com/evasnowind/rpc-learning)   | done  |
| MQ相关         |                                                              |       |
| 分布式链路追踪 | monitor-by-java-agent                                        | done  |
| 优雅停机       | [Springboot 优雅停止服务的几种方法](https://www.cnblogs.com/huangqingshi/p/11370291.html)   <br/>代码[shutdown demo](https://github.com/evasnowind/shutdowndemo)<br/>[如何做到优雅停机思维导图](docs/如何做到优雅停机.xmind) | done  |



### 其他

在考虑中，可能涉及如下主题：

- 分布式缓存相关
  - 比如redis的高级应用、最佳实践、配置等？
- 高可用
  - 比如如何给mysql配置HA？
- nginx使用
  - 如何使用脚本做限流、负载均衡等