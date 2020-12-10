package com.prayerlaputa.hmily.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author chenglong.yu
 * created on 2020/12/10
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients
@EnableTransactionManagement
public class SpringCloudHmilyOrderApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SpringCloudHmilyOrderApplication.class, args);
    }
}
