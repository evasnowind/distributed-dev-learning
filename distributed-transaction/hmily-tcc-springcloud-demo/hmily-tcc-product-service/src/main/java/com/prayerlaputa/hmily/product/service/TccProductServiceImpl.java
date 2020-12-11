package com.prayerlaputa.hmily.product.service;

import com.prayerlaputa.hmily.common.dto.TccProductReduceStockDTO;
import com.prayerlaputa.hmily.product.mapper.TccProductDao;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenglong.yu
 * created on 2020/12/10
 */
@Slf4j
@Service
public class TccProductServiceImpl implements TccProductService {

    @Autowired
    private TccProductDao productDao;

    /**
     * 开启事物
     *
     * @param productDto
     * @throws Exception
     */
    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public Boolean reduceStock(TccProductReduceStockDTO productDto) throws Exception {

        log.info("==========try扣减库存decrease===========");

        Long productId = productDto.getProductId();
        Integer amount = productDto.getAmount();

        // 检查库存
        checkStock(productId, amount);

        log.info("[reduceStock] 开始扣减 {} 库存", productId);
        // 扣减库存
        int updateCount = productDao.reduceStock(productId, amount);
        // 扣除失败
        if (updateCount == 0) {
            log.warn("[reduceStock] 扣除 {} 库存失败", productId);
            throw new Exception("库存不足");
        }
        // 扣除成功
        log.info("[reduceStock] 扣除 {} 库存成功", productId);
        return Boolean.TRUE;
    }


    public Boolean confirm(TccProductReduceStockDTO productDto) {
        /*
        正式环境下应该有一个锁定字段保存已锁定数量，然后在confirm阶段
        将锁定数量正式扣掉。此处为了方便，在Try阶段直接扣减商品数量，
        那么在confirm阶段直接返回true即可。但在cancel阶段需要将数量回复。
         */
        log.info("==========confirm库存确认方法===========");
        return Boolean.TRUE;
    }

    public Boolean cancel(TccProductReduceStockDTO productDto) {
        log.info("==========cancel库存取消方法===========");
        int newAmount = -productDto.getAmount();
        return productDao.reduceStock(productDto.getProductId(), newAmount) > 0;
    }


    private void checkStock(Long productId, Integer requiredAmount) throws Exception {
        log.info("[checkStock] 检查 {} 库存", productId);
        Integer stock = productDao.getStock(productId);
        if (stock < requiredAmount) {
            log.warn("[checkStock] {} 库存不足，当前库存: {}", productId, stock);
            throw new Exception("库存不足");
        }
    }

    @Override
    public Integer getStock(Long productId) {
        return productDao.getStock(productId);
    }
}
