package com.prayerlaputa.hmily.order.controller;

import com.prayerlaputa.hmily.order.service.TccOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenglong.yu
 * created on 2020/12/10
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class TccOrderController {

    @Autowired
    private TccOrderService orderService;

    @PostMapping("/create")
    public Integer createOrder(@RequestParam("userId") Long userId,
                               @RequestParam("productId") Long productId,
                               @RequestParam("price") Integer price) {
        long start = System.currentTimeMillis();
        log.info("[createOrder] 收到下单请求,用户:{}, 商品:{}, 价格:{}", userId, productId, price);
        Integer res = orderService.createOrder(userId, productId, price);
        log.info("[createOrder] 耗时：{}", (System.currentTimeMillis() - start));
        return res;
    }

    @GetMapping("/update/status")
    public Integer updateOrderStatus(Integer orderId, Integer status) {
        return orderService.updateOrderStatus(orderId, status);
    }
}
