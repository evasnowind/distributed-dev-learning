package com.prayerlaputa.dubbo.demo.provider.service;

import com.prayerlaputa.dubbo.demo.entity.Account;
import com.prayerlaputa.dubbo.demo.mapper.AccountMapper;
import com.prayerlaputa.dubbo.demo.service.AccountServiceOne;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenglong.yu
 * created on 2020/12/18
 */
@Slf4j
@Service(version = "${dubbo.provider.AccountServiceOne.version}")
public class ProviderServiceImpl implements AccountServiceOne {

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 用户1 扣除美元账号1美元，人民币账号增加7元
     * @return
     */
    @HmilyTCC(confirmMethod = "confirmOne", cancelMethod = "cancelOne")
    @Override
    public boolean exchange() {
        log.info("============service one exchange try 执行确认付款接口===============");
        Account account = new Account();
        account.setId(1L);
        account.setUsWallet(-1L);
        account.setCnWallet(7L);

        boolean hasSucceed = accountMapper.exchange(account);
        log.info("exchange one result={}, cur account={}.", hasSucceed, accountMapper.select(account));
        return hasSucceed;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean confirmOne() {
        log.info("============service one exchange confirm 执行确认付款接口===============");
        return Boolean.TRUE;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean cancelOne() {
        log.info("============service one exchange cancel 执行取消付款接口===============");
        Account account = new Account();
        account.setId(1L);
        account.setUsWallet(1L);
        account.setCnWallet(-7L);
        boolean isSuccess = accountMapper.exchange(account);
        log.info("service one exchange result={}, cur account={}.", isSuccess, accountMapper.select(account));
        return isSuccess;
    }
}
