package com.prayerlaputa.hmily.order.service;

import com.prayerlaputa.hmily.common.dto.TccAccountReduceBalanceDTO;
import com.prayerlaputa.hmily.common.dto.TccProductReduceStockDTO;
import com.prayerlaputa.hmily.common.enums.OrderStatusEnum;
import com.prayerlaputa.hmily.order.client.AccountClient;
import com.prayerlaputa.hmily.order.client.ProductClient;
import com.prayerlaputa.hmily.common.entity.OrderDO;
import com.prayerlaputa.hmily.order.mapper.TccOrderDao;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author chenglong.yu
 * created on 2020/12/11
 */
@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private TccOrderDao tccOrderDao;

    @Autowired
    private ProductClient productClient;
    @Autowired
    private AccountClient accountClient;

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public void makePayment(OrderDO order) {
        updateOrderStatus(order, OrderStatusEnum.NOT_PAY);

        accountClient.reduceBalance(buildAccountDto(order));
        productClient.reduceStock(buildProductDto(order));
    }


    private TccAccountReduceBalanceDTO buildAccountDto(OrderDO order) {
        TccAccountReduceBalanceDTO dto = new TccAccountReduceBalanceDTO();
        dto.setUserId(order.getUserId());
        dto.setPrice(order.getPayAmount());
        return dto;
    }

    private TccProductReduceStockDTO buildProductDto(OrderDO order) {
        TccProductReduceStockDTO dto = new TccProductReduceStockDTO();
        dto.setProductId(order.getProductId());
        dto.setAmount(order.getPayAmount());
        return dto;
    }

    public void confirmOrderStatus(OrderDO order) {
        updateOrderStatus(order, OrderStatusEnum.PAID_SUCCESS);
        log.info("=========进行订单confirm操作完成================");
    }

    public void cancelOrderStatus(OrderDO order) {
        updateOrderStatus(order, OrderStatusEnum.PAID_FAIL);
        log.info("=========进行订单cancel操作完成================");
    }


    public void updateOrderStatus(OrderDO order, OrderStatusEnum orderStatusEnum) {
        order.setStatus(orderStatusEnum.getCode());
        tccOrderDao.updateStatus(order);
    }
}
