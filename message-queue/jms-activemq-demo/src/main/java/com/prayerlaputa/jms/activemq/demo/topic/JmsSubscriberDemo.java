package com.prayerlaputa.jms.activemq.demo.topic;

import com.prayerlaputa.jms.activemq.demo.config.JmsConfigConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @author chenglong.yu
 * created on 2021/1/11
 */
@Slf4j
@Service
public class JmsSubscriberDemo {

    @JmsListener(destination = JmsConfigConst.JMS_ACTIVE_MQ_TOPIC, containerFactory = "myJmsContainerFactory")
    public void subscribe(String msg) {
        log.info("=====<<<<<<< 收到订阅的消息：{}.", msg);
    }
}
