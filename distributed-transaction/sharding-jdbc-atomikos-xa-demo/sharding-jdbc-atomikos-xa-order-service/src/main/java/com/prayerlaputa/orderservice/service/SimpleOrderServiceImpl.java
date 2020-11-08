package com.prayerlaputa.orderservice.service;

import com.alibaba.fastjson.JSONObject;
import com.prayerlaputa.orderservice.mapper.OrderMapper;
import com.prayerlaputa.orderservice.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class SimpleOrderServiceImpl implements SimpleOrderService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderMapper orderMapper;

//    @Transactional
//    @ShardingTransactionType(TransactionType.XA)
    @Override
    public Integer createOrder(Long userId, Long productId, Integer price) throws Exception {
        Integer amount = 1; // 购买数量，暂时设置为 1。

        // 扣减库存
        this.reduceStock(productId, amount);

        // 扣减余额
        this.reduceBalance(userId, price);

        // 保存订单
        Order order = new Order();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setPayAmount(amount * price);
        order.setAddTime(new Date());
        orderMapper.saveOrder(order);
        logger.info("[createOrder] 保存订单: {}", order.getId());

        // 返回订单编号
        return order.getId();
    }

    private void reduceStock(Long productId, Integer amount) throws IOException {
        // 参数拼接
        JSONObject params = new JSONObject().fluentPut("productId", String.valueOf(productId))
                .fluentPut("amount", String.valueOf(amount));
        // 执行调用
//        HttpResponse response = DefaultHttpExecutor.getInstance().executePost("http://127.0.0.1:28082", "/product/reduce-stock",
//                params, HttpResponse.class);
//        // 解析结果
//        Boolean success = Boolean.valueOf(EntityUtils.toString(response.getEntity()));
//        if (!success) {
//            throw new RuntimeException("扣除库存失败");
//        }
    }

    private void reduceBalance(Long userId, Integer price) throws IOException {
        // 参数拼接
        JSONObject params = new JSONObject().fluentPut("userId", String.valueOf(userId))
                .fluentPut("price", String.valueOf(price));
        // 执行调用
//        HttpResponse response = DefaultHttpExecutor.getInstance().executePost("http://127.0.0.1:28083", "/account/reduce-balance",
//                params, HttpResponse.class);
        // 解析结果
//        Boolean success = Boolean.valueOf(EntityUtils.toString(response.getEntity()));
//        if (!success) {
//            throw new RuntimeException("扣除余额失败");
//        }
    }

}
