package com.prayerlaputa.hmily.product.service;

import com.prayerlaputa.hmily.common.dto.TccProductReduceStockDTO;

/**
 * @author chenglong.yu
 * created on 2020/12/10
 */
public interface TccProductService {


    /**
     * 扣减库存
     *
     * @param productDto
     * @throws Exception 扣减失败时抛出异常
     */
    Boolean reduceStock(TccProductReduceStockDTO productDto) throws Exception;

    /**
     * 获取库存
     *
     * @param productId
     * @return
     */
    Integer getStock(Long productId);
}
