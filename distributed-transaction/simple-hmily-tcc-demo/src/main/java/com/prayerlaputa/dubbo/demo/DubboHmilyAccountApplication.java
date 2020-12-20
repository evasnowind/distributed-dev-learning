package com.prayerlaputa.dubbo.demo;

import com.prayerlaputa.dubbo.demo.service.TransactionService;
import org.dromara.hmily.spring.annotation.RefererAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author chenglong.yu
 * created on 2020/12/9
 */
@SpringBootApplication
public class DubboHmilyAccountApplication implements ApplicationRunner {


    @Autowired
    private TransactionService transactionService;

    public static void main(final String[] args) {
        SpringApplication.run(DubboHmilyAccountApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        transactionService.exchange();
    }


    @Bean
    public BeanPostProcessor refererAnnotationBeanPostProcessor() {
        return new RefererAnnotationBeanPostProcessor();
    }
}
