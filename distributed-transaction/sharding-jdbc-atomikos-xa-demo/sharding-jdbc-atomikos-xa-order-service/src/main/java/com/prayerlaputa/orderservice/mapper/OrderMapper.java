package com.prayerlaputa.orderservice.mapper;

import com.prayerlaputa.orderservice.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    /**
     * 插入订单记录
     *
     * @param order 订单
     * @return 影响记录数量
     */
    int saveOrder(Order order);

}
