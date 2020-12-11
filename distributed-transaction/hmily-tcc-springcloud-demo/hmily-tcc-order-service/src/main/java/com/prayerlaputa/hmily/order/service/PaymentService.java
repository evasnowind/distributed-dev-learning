package com.prayerlaputa.hmily.order.service;

import com.prayerlaputa.hmily.common.entity.OrderDO;

/**
 * @author chenglong.yu
 * created on 2020/12/11
 */
public interface PaymentService {

    void makePayment(OrderDO order);
}
