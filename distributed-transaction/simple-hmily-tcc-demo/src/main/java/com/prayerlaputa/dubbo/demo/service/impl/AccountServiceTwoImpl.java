package com.prayerlaputa.dubbo.demo.service.impl;

import com.prayerlaputa.dubbo.demo.entity.Account;
import com.prayerlaputa.dubbo.demo.mapper.AccountMapper;
import com.prayerlaputa.dubbo.demo.service.AccountServiceTwo;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenglong.yu
 * created on 2020/12/18
 */
@Slf4j
@Service("accountServiceTwo")
public class AccountServiceTwoImpl implements AccountServiceTwo {

    @Autowired
    private AccountMapper accountMapper;


    /**
     * 用户2 美元账号增加1元，人民币账号减少7元
     * @return
     */
    @HmilyTCC(confirmMethod = "confirmTwo", cancelMethod = "cancelTwo")
    @Override
    public boolean exchange() {
        log.info("============service two exchange try 执行确认付款接口===============");
        Account account = new Account();
        account.setId(2L);
        account.setUsWallet(1L);
        account.setCnWallet(-7L);

        boolean hasSucceed = accountMapper.exchange(account);
        log.info("exchange two result={}, cur account={}.", hasSucceed, accountMapper.select(account));
        return hasSucceed;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean confirmTwo() {
        log.info("============service two exchange confirm 执行确认付款接口===============");
        return Boolean.TRUE;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean cancelTwo() {
        log.info("============service two exchange cancel 执行取消付款接口===============");
        Account account = new Account();
        account.setId(1L);
        account.setUsWallet(-1L);
        account.setCnWallet(7L);
        boolean isSuccess = accountMapper.exchange(account);
        log.info("service two exchange result={}, cur account={}.", isSuccess, accountMapper.select(account));
        return isSuccess;
    }
}
