package com.prayerlaputa.jms.activemq.demo.topic;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author chenglong.yu
 * created on 2021/1/11
 */
@Slf4j
@Service
public class JmsPublisherDemo {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;


    public void publish(String destinationName, String msg) {
        Destination destination = new ActiveMQTopic(destinationName);
        log.info("======>>>>> 发送topic消息：{}.", msg);
        jmsMessagingTemplate.convertAndSend(destination, msg);
    }
}
