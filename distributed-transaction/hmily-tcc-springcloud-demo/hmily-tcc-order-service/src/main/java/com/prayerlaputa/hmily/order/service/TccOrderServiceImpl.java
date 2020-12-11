package com.prayerlaputa.hmily.order.service;

import com.prayerlaputa.hmily.common.dto.TccAccountReduceBalanceDTO;
import com.prayerlaputa.hmily.common.dto.TccProductReduceStockDTO;
import com.prayerlaputa.hmily.common.enums.OrderStatusEnum;
import com.prayerlaputa.hmily.order.client.AccountClient;
import com.prayerlaputa.hmily.order.client.ProductClient;
import com.prayerlaputa.hmily.common.entity.OrderDO;
import com.prayerlaputa.hmily.order.mapper.TccOrderDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author chenglong.yu
 * created on 2020/12/10
 */
@Slf4j
@Service
public class TccOrderServiceImpl implements TccOrderService {

    @Autowired
    private ProductClient productClient;
    @Autowired
    private AccountClient accountClient;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private TccOrderDao orderDao;

    @Override
    public Integer createOrder(Long userId, Long productId, Integer price) {
        // 购买数量，暂时设置为 1。
        Integer amount = 1;
        final Integer payment = amount * price;
        final OrderDO order = saveOrder(userId, productId, payment);

        long start = System.currentTimeMillis();
        //分布式事务
        paymentService.makePayment(order);

        log.info("hmily tcc 分布式事务耗时：{}.", (System.currentTimeMillis()  - start));
        // 返回订单编号
        return order.getId();
    }

    @Override
    public Integer updateOrderStatus(Integer orderId, Integer status) {
        OrderDO order = new OrderDO();
        order.setId(orderId);
        order.setStatus(status);
        return orderDao.updateStatus(order);
    }

    public OrderDO saveOrder(Long userId, Long productId, Integer payment) {
        // 保存订单
        OrderDO order = new OrderDO().setUserId(userId)
                .setProductId(productId)
                .setPayAmount(payment)
                .setStatus(OrderStatusEnum.NOT_PAY.getCode());

        orderDao.saveOrder(order);
        log.info("[createOrder] 保存订单: {}", order.getId());

        return order;
    }

//    private void reduceStock(Long productId, Integer amount) throws IOException {
//        TccProductReduceStockDTO stockDTO = new TccProductReduceStockDTO();
//        stockDTO.setProductId(productId);
//        stockDTO.setAmount(amount);
//        productClient.reduceStock(stockDTO);
//    }
//
//    private void reduceBalance(Long userId, Integer price) throws IOException {
//        TccAccountReduceBalanceDTO accountDto = new TccAccountReduceBalanceDTO();
//        accountDto.setPrice(price);
//        accountDto.setUserId(userId);
//        accountClient.reduceBalance(accountDto);
//    }
}
