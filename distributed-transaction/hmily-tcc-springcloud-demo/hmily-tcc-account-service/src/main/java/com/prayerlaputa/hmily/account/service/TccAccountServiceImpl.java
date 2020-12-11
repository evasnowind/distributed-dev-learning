package com.prayerlaputa.hmily.account.service;

import com.prayerlaputa.hmily.account.mapper.TccAccountDao;
import com.prayerlaputa.hmily.common.dto.TccAccountReduceBalanceDTO;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenglong.yu
 * created on 2020/12/9
 */
@Slf4j
@Service
public class TccAccountServiceImpl implements TccAccountService {


    @Autowired
    private TccAccountDao accountDao;


    @Override
    public Integer getBalance(Long userId) {
        return accountDao.getBalance(userId);
    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public Boolean reduceBalance(TccAccountReduceBalanceDTO accountDto) throws Exception {
        log.info("[reduceBalance] ============执行try付款接口===============");

        Long userId = accountDto.getUserId();
        Integer price = accountDto.getPrice();

        // 检查余额
        checkBalance(userId, price);

        log.info("[reduceBalance] 开始扣减用户 {} 余额", userId);
        // 扣除余额
        int updateCount = accountDao.reduceBalance(price);

        // 扣除成功
        if (updateCount == 0) {
            log.warn("[reduceBalance] 扣除用户 {} 余额失败", userId);
            throw new Exception("余额不足");
        }
        log.info("[reduceBalance] 扣除用户 {} 余额成功", userId);

        return Boolean.TRUE;
    }

    public boolean confirm(TccAccountReduceBalanceDTO accountDto) {
        /*
        本来的话应该是账户余额表中设置一个锁定金额字段，扣减时先锁定金额，等confirm阶段将锁定金额直接扣减。
        此处简化为只有一个账户余额字段，那么确认操作时，直接返回true即可。若失败、调用cancel时需要将账户
        余额反向加回来。
         */
        log.info("[reduceBalance] ============执行confirm 付款接口===============");
        return Boolean.TRUE;
    }

    public boolean cancel(TccAccountReduceBalanceDTO accountDto) {
        log.info("[reduceBalance] ============执行cancel 付款接口===============");
        int cancelPrice = -accountDto.getPrice();
        return accountDao.reduceBalance(cancelPrice) > 0;
    }

    private void checkBalance(Long userId, Integer price) throws Exception {
        log.info("[checkBalance] 检查用户 {} 余额", userId);
        Integer balance = accountDao.getBalance(userId);
        if (balance < price) {
            log.warn("[checkBalance] 用户 {} 余额不足，当前余额:{}", userId, balance);
            throw new Exception("余额不足");
        }
    }
}
