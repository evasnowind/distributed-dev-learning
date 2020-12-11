package com.prayerlaputa.hmily.account.service;

import com.prayerlaputa.hmily.common.dto.TccAccountReduceBalanceDTO;

/**
 * @author chenglong.yu
 * created on 2020/12/9
 */
public interface TccAccountService {

    Integer getBalance(Long userId);

    /**
     * 扣除余额
     *
     * @param accountDto
     * @return
     */
    Boolean reduceBalance(TccAccountReduceBalanceDTO accountDto) throws Exception;
}
