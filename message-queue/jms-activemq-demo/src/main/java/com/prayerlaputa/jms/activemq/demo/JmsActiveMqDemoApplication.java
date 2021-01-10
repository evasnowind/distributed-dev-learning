package com.prayerlaputa.jms.activemq.demo;

import com.prayerlaputa.jms.activemq.demo.config.JmsConfigConst;
import com.prayerlaputa.jms.activemq.demo.queue.JmsProducerDemo;
import com.prayerlaputa.jms.activemq.demo.topic.JmsPublisherDemo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

import javax.annotation.Resource;

/**
 * @author chenglong.yu
 * created on 2021/1/11
 */
@SpringBootApplication
@EnableJms
@Slf4j
public class JmsActiveMqDemoApplication implements ApplicationRunner {


    public static void main(String[] args) {
        SpringApplication.run(JmsActiveMqDemoApplication.class, args);
    }

    @Resource
    private JmsProducerDemo jmsProducerDemo;
    @Resource
    private JmsPublisherDemo jmsPublisherDemo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //测试PTP模型，按queue发送消息
        for (int i = 0; i < 10; i++) {
            jmsProducerDemo.sendMsg(JmsConfigConst.JMS_ACTIVE_MQ_QUEUE, "queue msg" + i);
        }

        log.info("---------------");
        log.info("---------------");
        log.info("---------------");
        //测试发布订阅模型，按topic发送消息
        for (int i = 10; i < 20; i++) {
            jmsPublisherDemo.publish(JmsConfigConst.JMS_ACTIVE_MQ_TOPIC, "topic msg" + i);
        }
    }
}
