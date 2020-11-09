# sharding-db-table-springboot

分库、分表demo。

水平拆分。两个库，每个有3个表。
以localhost:8084/add接口为例：插入100个数据，每个库50个，分散在3张表中。
