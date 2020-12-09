package com.prayerlaputa.hmily.account;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author chenglong.yu
 * created on 2020/12/9
 */
@EnableFeignClients
@EnableTransactionManagement
public class SpringCloudHmilyAccountApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SpringCloudHmilyAccountApplication.class, args);
    }
}
