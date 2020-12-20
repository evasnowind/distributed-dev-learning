package com.prayerlaputa.dubbo.demo.service.impl;

import com.prayerlaputa.dubbo.demo.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenglong.yu
 * created on 2020/12/18
 */
@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountServiceOneImpl accountServiceOne;

    @Autowired
    private AccountServiceTwoImpl accountServiceTwo;

    @Override
    @HmilyTCC(confirmMethod = "confirmStatus", cancelMethod = "cancelStatus")
    public void exchange() {
        log.info("--------------- start tcc transaction --------------");
        accountServiceOne.exchange();
        accountServiceTwo.exchange();
        log.info("--------------- end tcc transaction --------------");
    }

    public void confirmStatus() {
        log.info("=========进行global confirm操作完成================");
    }

    public void cancelStatus() {
        log.info("=========进行global cancel操作完成================");
    }
}
