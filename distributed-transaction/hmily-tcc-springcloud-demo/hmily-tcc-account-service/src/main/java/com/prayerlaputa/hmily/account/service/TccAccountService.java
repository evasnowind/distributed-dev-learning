package com.prayerlaputa.hmily.account.service;

/**
 * @author chenglong.yu
 * created on 2020/12/9
 */
public interface TccAccountService {

    Integer getBalance(Long userId);

    /**
     * 扣除余额
     *
     * @param userId 用户编号
     * @param price  扣减金额
     * @throws Exception 失败时抛出异常
     */
    void reduceBalance(Long userId, Integer price) throws Exception;
}
