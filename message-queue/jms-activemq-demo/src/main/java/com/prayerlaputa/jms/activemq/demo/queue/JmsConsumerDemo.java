package com.prayerlaputa.jms.activemq.demo.queue;

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
public class JmsConsumerDemo {

    @JmsListener(destination = JmsConfigConst.JMS_ACTIVE_MQ_QUEUE)
    public void receiveMsg(String msg) {
        log.info("<<<<<===== 收到消息：{}.", msg);
    }
}
