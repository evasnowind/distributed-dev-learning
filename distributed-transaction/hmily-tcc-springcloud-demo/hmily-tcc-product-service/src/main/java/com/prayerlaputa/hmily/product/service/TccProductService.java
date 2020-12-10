package com.prayerlaputa.hmily.product.service;

/**
 * @author chenglong.yu
 * created on 2020/12/10
 */
public interface TccProductService {


    /**
     * 扣减库存
     *
     * @param productId 商品 ID
     * @param amount    扣减数量
     * @throws Exception 扣减失败时抛出异常
     */
    void reduceStock(Long productId, Integer amount) throws Exception;

    /**
     * 获取库存
     *
     * @param productId
     * @return
     */
    Integer getStock(Long productId);
}
