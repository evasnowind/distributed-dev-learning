# distributed-dev-learning

本项目主要给出一些常见分布式技术的demo，供学习、参考。

各个模块用途如下：

| 技术           | 模块                                                         | 状态  | 备注                                             |
| -------------- | ------------------------------------------------------------ | ----- | ------------------------------------------------ |
| 一致性哈希算法 | consistent-hash                                              | done  |                                                  |
| 分布式锁       | distributed-lock                                             | doing |                                                  |
| 分布式事务     | distributed-transaction                                      | done  |                                                  |
| 一致性算法     | distributed-consensus-algorithm                              | doing |                                                  |
| 领导者选举     | leader-election                                              | doing |                                                  |
| 数据库数据分片 | db-data-sharding                                             | done  |                                                  |
| nginx应用      |                                                              | to do |                                                  |
| netty使用      | [使用netty仿写微信IM](https://github.com/evasnowind/netty-im) | done  |                                                  |
| RPC            | [rpc-learning](https://github.com/evasnowind/rpc-learning)<br/>[rpcfx](https://github.com/evasnowind/rpcfx) | done  | 如何自己动手<br/>写一个RPC                       |
| MQ相关         | [mq-learning](https://github.com/evasnowind/mq-learning)     | done  | 消息队列高手课<br/>作业汇总，常见MQ<br/>使用demo |
| 分布式链路追踪 | monitor-by-java-agent                                        | done  | 如何自己动手<br/>写一个分布式<br/>链路追踪框架   |
| 优雅停机       | [Springboot 优雅停止服务的几种方法](https://www.cnblogs.com/huangqingshi/p/11370291.html)   <br/>代码[shutdown demo](https://github.com/evasnowind/shutdowndemo)<br/>[如何做到优雅停机思维导图](docs/如何做到优雅停机.xmind) | done  |                                                  |
| 断路器         | circuit-breaker                                              | done  | 常见断路器<br/>的使用                            |
| 网关           | api-gateway                                                  | done  | 常见网关的<br/>使用                              |
| 分布式缓存     |                                                              |       |                                                  |
|                |                                                              |       |                                                  |



### 其他

在考虑中，可能涉及如下主题：

- 分布式缓存相关
  - 比如redis的高级应用、最佳实践、配置等？
- 高可用
  - 比如如何给mysql配置HA？
- nginx使用
  - 如何使用脚本做限流、负载均衡等
- ......