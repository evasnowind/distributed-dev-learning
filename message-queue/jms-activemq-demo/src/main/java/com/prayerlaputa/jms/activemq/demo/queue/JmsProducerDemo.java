package com.prayerlaputa.jms.activemq.demo.queue;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author chenglong.yu
 * created on 2021/1/11
 */
@Service
@Slf4j
public class JmsProducerDemo {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMsg(String destinationName, String msg) {
        log.info("=======>>>>>> 发送queue消息：{}.", msg);
        Destination destination = new ActiveMQQueue(destinationName);
        jmsMessagingTemplate.convertAndSend(destination, msg);
    }
}
