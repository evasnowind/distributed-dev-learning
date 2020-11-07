# sharding-jdbc
给出常见场景下如何使用apache sharding-jdbc进行分库、分表。

代码主要来自于https://github.com/yinjihuan/sharding-jdbc，重新整理了下，作为自己的一个代码库。

## 项目说明
- [x] sharding-read-write-springboot 读写分离
- [x] sharding-split-vertically-springboot 垂直拆分（不同表在不同库中）
- [x] sharding-split-vertically-read-write-springboot 垂直拆分（不同表在不同库中）+ 读写分离
- [x] sharding-table-spring 不分库，只分表
- [x] sharding-table-read-write-springboot 不分库，只分表 + 读写分离
- [x] sharding-db-table-springboot 分库分表
- [x] sharding-db-table-read-write-springboot 分库分表 + 读写分离
- [x] sharding-db-table-read-write-range-group-springboot 分库分表+读写分离案例(范围分表+取模=无限扩容)
- [ ] 分库分表+读写分离+分页（待定）

## 参考资料

jdbc相关演示主要来自于：https://github.com/yinjihuan/sharding-jdbc