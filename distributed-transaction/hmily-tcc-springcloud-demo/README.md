# 使用Hmily进行TCC分布式事务的demo   

目前demo主要参考hmily的TCC如何使用，当然具体还要参考官方文档、官方demo.

本项目主要是为了学习、实践一下如何在业务开发中使用TCC。

可以看到TCC模式对于业务代码侵入很大、增加了不少负担。 

目前还遇到的问题：
- 一次创建订单的请求，在调用减库存、减余额操作时，会产生两次调用，这个有待进一步重现，目前感觉可能与feign自动重试有关，有待进一步试验，可以参考这个 https://blog.csdn.net/sinat_36454672/article/details/106112922
- 各种错误场景的试验、是否正常回滚。这个比较花时间，最近时间紧，先留待后续试验吧。
