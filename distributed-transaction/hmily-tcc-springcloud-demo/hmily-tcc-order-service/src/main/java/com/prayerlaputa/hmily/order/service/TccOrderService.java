package com.prayerlaputa.hmily.order.service;

import com.prayerlaputa.hmily.common.enums.OrderStatusEnum;

/**
 * @author chenglong.yu
 * created on 2020/12/10
 */
public interface TccOrderService {

    /**
     * 创建订单
     *
     * @param userId 用户编号
     * @param productId 产品编号
     * @param price 价格
     * @return 订单编号
     */
    Integer createOrder(Long userId, Long productId, Integer price);

    /**
     * 更新订单状态
     *
     * @param orderId
     * @param status
     * @return
     */
    Integer updateOrderStatus(Integer orderId, Integer status);
}
