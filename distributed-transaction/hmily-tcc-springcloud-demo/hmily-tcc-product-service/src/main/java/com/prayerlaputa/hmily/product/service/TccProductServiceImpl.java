package com.prayerlaputa.hmily.product.service;

import com.prayerlaputa.hmily.product.mapper.TccProductDao;
import lombok.extern.slf4j.Slf4j;
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
     * @param productId 商品 ID
     * @param amount    扣减数量
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reduceStock(Long productId, Integer amount) throws Exception {

        // 检查库存
        checkStock(productId, amount);

        log.info("[reduceStock] 开始扣减 {} 库存", productId);
        // 扣减库存
        int updateCount = productDao.reduceStock(productId, amount);
        // 扣除成功
        if (updateCount == 0) {
            log.warn("[reduceStock] 扣除 {} 库存失败", productId);
            throw new Exception("库存不足");
        }
        // 扣除失败
        log.info("[reduceStock] 扣除 {} 库存成功", productId);
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
