package com.prayerlaputa.dubbo.demo.consumer;

import com.prayerlaputa.dubbo.demo.service.TransactionService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chenglong.yu
 * created on 2020/12/19
 */
@SpringBootApplication
@MapperScan("com.prayerlaputa.dubbo.demo.mapper")
public class DubboConsumerApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }

    @Autowired
    private TransactionService transactionService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        transactionService.exchange();
    }
}
