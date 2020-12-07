# 批量新建数据库、数据库表脚本

参考  [Shell 脚本批量创建数据库表](https://www.cnblogs.com/leeyongbard/p/10022404.html)

我自己写的批量创建订单表分表的脚本：
```shell script
#!/bin/bash
#批量新建数据表
for y in {0..15};do
mysql -uroot -P3308 --socket=/data/mysql/mysql_3308/mysql.sock -p'1qaz@WSX' -e "create database if not exists $1; use $1; create table $2_$y(
id bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键，记录唯一标识',
member_id bigint(20) NOT NULL COMMENT '用户id',
coupon_id bigint(20) DEFAULT NULL COMMENT '优惠券id',
  order_sn varchar(64) DEFAULT NULL COMMENT '订单编号',
  create_time datetime DEFAULT NULL COMMENT '提交时间',
  member_username varchar(64) DEFAULT NULL COMMENT '用户帐号',
  total_amount decimal(10,2) DEFAULT NULL COMMENT '订单总金额',
  pay_amount decimal(10,2) DEFAULT NULL COMMENT '应付金额（实际支付金额）',
  freight_amount decimal(10,2) DEFAULT NULL COMMENT '运费金额',
  promotion_amount decimal(10,2) DEFAULT NULL COMMENT '促销优化金额（促销价、满减、阶梯价）',
  integration_amount decimal(10,2) DEFAULT NULL COMMENT '积分抵扣金额',
  coupon_amount decimal(10,2) DEFAULT NULL COMMENT '优惠券抵扣金额',
  discount_amount decimal(10,2) DEFAULT NULL COMMENT '管理员后台调整订单使用的折扣金额',
  pay_type int(1) DEFAULT NULL COMMENT '支付方式：0->未支付；1->支付宝；2->微信',
  source_type int(1) DEFAULT NULL COMMENT '订单来源：0->PC订单；1->app订单',
  status int(1) DEFAULT NULL COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单',
  order_type int(1) DEFAULT NULL COMMENT '订单类型：0->正常订单；1->秒杀订单',
  delivery_company varchar(64) DEFAULT NULL COMMENT '物流公司(配送方式)',
  delivery_sn varchar(64) DEFAULT NULL COMMENT '物流单号',
  auto_confirm_day int(11) DEFAULT NULL COMMENT '自动确认时间（天）',
  integration int(11) DEFAULT NULL COMMENT '可以获得的积分',
  growth int(11) DEFAULT NULL COMMENT '可以活动的成长值',
  promotion_info varchar(100) DEFAULT NULL COMMENT '活动信息',
  bill_type int(1) DEFAULT NULL COMMENT '发票类型：0->不开发票；1->电子发票；2->纸质发票',
  bill_header varchar(200) DEFAULT NULL COMMENT '发票抬头',
  bill_content varchar(200) DEFAULT NULL COMMENT '发票内容',
  bill_receiver_phone varchar(32) DEFAULT NULL COMMENT '收票人电话',
  bill_receiver_email varchar(64) DEFAULT NULL COMMENT '收票人邮箱',
  receiver_name varchar(100) NOT NULL COMMENT '收货人姓名',
  receiver_phone varchar(32) NOT NULL COMMENT '收货人电话',
  receiver_post_code varchar(32) DEFAULT NULL COMMENT '收货人邮编',
  receiver_province varchar(32) DEFAULT NULL COMMENT '省份/直辖市',
  receiver_city varchar(32) DEFAULT NULL COMMENT '城市',
  receiver_region varchar(32) DEFAULT NULL COMMENT '区',
  receiver_detail_address varchar(200) DEFAULT NULL COMMENT '详细地址',
  note varchar(500) DEFAULT NULL COMMENT '订单备注',
  confirm_status int(1) DEFAULT NULL COMMENT '确认收货状态：0->未确认；1->已确认',
  delete_status int(1) NOT NULL DEFAULT '0' COMMENT '删除状态：0->未删除；1->已删除',
  use_integration int(11) DEFAULT NULL COMMENT '下单时使用的积分',
  payment_time datetime DEFAULT NULL COMMENT '支付时间',
  delivery_time datetime DEFAULT NULL COMMENT '发货时间',
  receive_time datetime DEFAULT NULL COMMENT '确认收货时间',
  comment_time datetime DEFAULT NULL COMMENT '评价时间',
  modify_time datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';"
done

```

执行参数如下：
```shell script
sh batch_create_db.sh order_0 oms_order
```

4个参数分别是：
- 脚本名
- 数据库名，若没有将自动创建
- 数据库表前缀

后续再优化一点的话可以将库、表的数量都作为前缀传进去，今天就不折腾了。